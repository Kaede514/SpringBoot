package com.kaede.principle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * 1、Profile功能
 * 为了方便多环境适配，SpringBoot简化了profile功能
 *    1）application-profile功能
 *       指定环境配置文件的命名规则为application-<name>.yaml，
 *       默认配置文件是application.yaml，任何时候都会加载，激活
 *       特定环境：
 *       - 配置文件激活：spring.profiles.active=<name>
 *       - 命令行激活：java -jar xxx.jar --spring.profiles.active=test --server.port=8888
 *       默认配置和激活配置同时生效，若存在同名配置项，则以激活配置优先，
 *       命令行的优先级最高，可以在部署之前直接更改配置
 *    2）@Profile条件装配功能
 *       使用@Profile(xx)指定该类或该方式在xx环境下生效
 *    3）profile分组
 *
 * 2、外部化配置
 *    1）外部配置源
 *       常用：Java属性文件、YAML文件、环境变量、命令行参数
 *    2）配置文件查找位置
 *       - classpath根路径
 *       - classpath根路径下的config目录
 *       - 打包后jar包所在的目录
 *       - 打包后jar包所在的目录中的config目录
 *       - /config的一级子目录下
 *       下面的配置文件会覆盖上面的配置文件的同名配置
 *    3）配置文件的加载顺序
 *       - 当前jar包内部的application.properties和application.yml
 *  　   - 当前jar包内部的application-{profile}.properties和application-{profile}.yml
 *  　   - 引用的外部jar包的application.properties和application.yml
 *  　   - 引用的外部jar包的application-{profile}.properties和application-{profile}.yml
 *    指定环境优先，外部优先，后面的可以覆盖前面的同名配置项
 *
 * 3、自定义starter
 *    1）starter启动原理
 *      - starter点进去是pom文件，starter-pom中引入autoconfigure包
 *      - autoconfigure包配置使用META-INF/spring.factories中EnableAutoConfiguration
 *        的值，使得项目启动加载指定的自动配置类
 *    2）自定义starter
 *       - 创建自动配置包项目
 *       - 在xxProperties中绑定配置文件前缀
 *       - xxAutoConfiguration标注@Configuration，@EnableConfigurationProperties
 *         将配置类和配置文件进行绑定并放入容器中，内部编写的组件可以使用条件判断方便之后
 *         在应用项目中自己注入组件
 *       - 在resources建立META-INF/spring.factories里面配置需要放入容器中的类
 *       - 然后进行install
 *       - 创建启动器项目，引入自动配置包依赖，然后进行install
 *       - 最后在应用项目中引入启动器依赖即可使用
 *
 * 4、SpringBoot原理
 * SpringBoot启动过程
 *    1）创建SpringApplication
 *       1、保存一些信息
 *       2、判断当前项目类型（一般是Servlet）
 *       3、获取所有初始启动引导器（在spring.properties中找Bootstrapper）
 *       4、获取初始化器（在spring.properties中找ApplicationContextInitializer）
 *       5、获取应用监听器（在spring.properties中找ApplicationListener）
 *    2）运行SpringApplication
 *       1、创建StopWatch（记录应用的启动时间）
 *       2、创建引导上下文（createBootstrapContext）
 *          - 获取到所有之前保存的初始引导启动器（bootstrappers），分别执行intitialize
 *            方法来完成对引导启动器上下文的环境设置
 *       3、让当前进行进入headless模式
 *       4、获取所有的运行时监听器（RunListener）[方便所有listener进行事件感知]
 *          - 在spring.properties中找SpringApplicationRunListener
 *       5、遍历所有的SpringApplicationRunListener调用starting方法
 *          - 相当于通知所有listener项目正在starting
 *       5、保存命令行参数（ApplicationArguments）
 *       6、调用prepareEnvironment准备环境
 *          - 返回或创建一个新的基础环境信息
 *          - 配置环境信息对象
 *            - 读取所有的配置源的配置属性值
 *          - 绑定环境信息
 *          - 监听器调用environmentPrepared，通知所有的监听器当前环境准备完成
 *       7、创建IOC容器（createApplicationContext）
 *          - 根据当前项目类型（Servlet）创建容器
 *          - 当前会创建AnnotationConfigServletWebServerApplicationContext
 *       8、准备IOC容器的基本信息（prepareContext）
 *          - 保存环境信息
 *          - IOC容器的后置处理流程
 *          - 应用初始化器（applyInitializers）
 *            - 遍历所有的ApplicationContextInitializer，调用initialize来对IOC容器进行初始化扩展
 *            - 遍历所有的listener，调用contextPrepared，通知所有监听器IOC上下文环境准备完成
 *          - 所有监听器调用contextLoaded，通知所有监听器IOC容器加载完成
 *       9、刷新IOC容器
 *          - 创建容器中的所有组件
 *       10、容器刷新完成后的工作（afterRefresh）
 *       11、所有监听器调用started，通知所有监听器当前项目已启动
 *       12、调用所有的runner（callRunners）
 *           - 获取容器中的ApplicationRunner
 *           - 获取容器中的CommandLineRunner
 *           - 合并所有runner并且按照@Order进行排序
 *           - 遍历所有runner，调用run方法
 *       13、如果以上有异常，则调用所有listener的failed方法
 *       14、调用所有监听器的running方法，通知所有监听器项目正在运行
 *       15、running若有问题，则调用所有listener的failed方法，通知所有监听器failed
 *
 */

@SpringBootApplication
public class Pro12PrincipleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Pro12PrincipleApplication.class, args);
        //获取环境变量
        ConfigurableEnvironment environment = run.getEnvironment();
        //获取系统的环境变量
        Map<String, Object> systemEnvironment = environment.getSystemEnvironment();
        //获取系统的属性
        Map<String, Object> systemProperties = environment.getSystemProperties();
        System.out.println(systemEnvironment);
        System.out.println("================");
        System.out.println(systemProperties);
    }

}
