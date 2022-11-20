package com.kaede.boot;

import ch.qos.logback.core.db.DBHelper;
import com.kaede.boot.bean.Pet;
import com.kaede.boot.bean.User;
import com.kaede.boot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author kaede
 * @create 2022-08-09 20:33
 *
 * 以前用Spring XML或者注解配置的方式将组件添加到容器中，现在只需创建
 * 一个类，标注@Configuration注解，告诉SpringBoot这是一个配置类（等同
 * 于以前的配置文件）
 *
 * SpringBoot自动配置原理：
 * 1、@SpringBootApplcation
 *     @SpringBootApplication就相当于@SpringBootConfiguration、@EnableAutoConfiguration
 *     和@ComponentScan注解，因为内含这三个注解
 *     1、@SpringBootConfiguration：内含@Configuration，代表当前是一个配置类
 *     2、@ComponentScan：指定扫描哪些
 *     3、@EnableAutoConfiguration：内含@AutoConfigurationPackage和@Import
 *        1）@AutoConfigurationPackage：将指定包下的所有组件导入进来（MainApplication所在包下）
 *        2）@Import：给容器中批量导入组件
 * 2、按需开启自动配置项
 *    启动时加载所有场景的自动配置，按照条件装配规则（条件注解），最终会按需配置
 * 3、修改默认配置
 *    SpringBoot默认会在底层配好所有的组件，但如果用户自己配置了，则以用户的优先，
 *    如使用@ConditionalOnMissingBean等判断用户是否配置了
 *    总结：
 *    1、SpringBoot先加载所有的自动配置类xxAutoConfiguration
 *    2、每个自动配置类按照条件注解进行判断生效，默认都会绑定配置文件指定的值，
 *       通过@EnableConfigurationProperties(xxProperties.class)获取，
 *       xxProperties又通过@ConfigurationProperties(prefix="")和配置文件进行了绑定
 *    3、生效的配置类就会给容器中装配很多组件，如CharacterEncodingFilter
 *    4、只要容器中有这些组件，相应的功能就有了
 *    5、只要用户自己配置了，就以用户的优先，可以定制化配置：
 *       方法1）直接使用@Bean替换底层组件
 *       方法2）查看组件获取的是配置文件的哪个值，然后修改对应的值即可（常用）
 *             xxAutoConfiguration --> 组件 --> 组件从@EnableConfigurationProperties(xxProperties.class)
 *             里获取值 --> xxProperties通过@ConfigurationProperties(prefix="")和配置文件进行绑定 -->
 *             在application.properties中通过前缀修改配置
 *             （xxAutoConfiguration在包org.springframework.boot.autoconfigure下）
 *
 * 最佳实践：
 * 1、引入场景依赖
 * 2、查看自动配置了哪些（选做）
 *    方法1）自己分析，引入场景对应的自动配置一般都生效了
 *    方法2）在application.properties中设置debug=true开启自动配置报告，会打印出生效和
 *          未生效的自动配置类，Negative（不生效），Positive（生效）
 * 3、是否需要修改
 *    (1)  方法1）参照文档修改配置项
 *         方法2）自己分析xxProperties绑定了配置文件的哪些
 *    (2)  自定义加入或替换组件，如@Bean、@Component...
 *    (3)  自定义器xxCustomizer
 *
 * 开发小技巧
 * 1、lombok：简化javabean开发
 *    1）引入lombok依赖（starter-web中未引入依赖，在start-parent中
 *       的dependencies中搜索到复制过来）
 *    2）搜索安装lombok插件（IDEA已自动捆绑安装）
 *    3）JavaBean中只需成员变量，无需get()、set()、toString()等方法，
 *       使用@Data注解，编译时会自动加上（不含构造器方法）
 *       @AllArgsConstructor：注在类上，提供类的全参构造器
 *       @NoArgsConstructor：注在类上，提供类的无参构造器
 *       需要定制的情况也可以自己写
 *       @Slf4j：标注在类上，提供对应的Logger对象，变量名为log，打印日志
 * 2、devtools：热部署
 *    自动重启，再项目中做改变后只需Ctrl F9重新编译一下，之后devtools就会
 *    把项目重新加载，不需要手动重启服务器（项目大了以后重启服务器耗时会比较
 *    长），但是资源占用大，性能一般的电脑慎用（这是重启，JRebel才是重加载）
 * 3、Spring Initializr：项目初始化向导
 *    选择需要的开发场景，会自动进行依赖引入、创建项目结构和生成主配置类
 *
 */

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        //返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        //查看容器里的组件
        /*String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }*/

        //从容器中获取组件，发现组件默认是单实例的
        //Pet cat01 = run.getBean("cat01", Pet.class);
        //Pet cat02 = run.getBean("cat01", Pet.class);
        //System.out.println("cat01 == cat02：" + (cat01 == cat02));

        /*
            @Configuration中的proxyBeanMethods，若为
            true  com.kaede.boot.config.MyConfig$$EnhancerBySpringCGLIB$$e8095533@bf71cec
            false  com.kaede.boot.config.MyConfig@1da6ee17
        */
        //MyConfig myConfig = run.getBean(MyConfig.class);
        //System.out.println(myConfig);

        /*
            若@Configuration(proxyBeanMethods = true)，则为代理对象调用方法，SpringBoot
            总会检查这个组件是否在容器中有，若有则不会创建新对象，保持组件单实例，结果为true
            若@Configuration(proxyBeanMethods = false)，则结果为false
        */
        //User user01 = myConfig.user01();
        //User user02 = myConfig.user01();
        //System.out.println("user01 == user02：" + (user01 == user02));

        //当user01组件依赖cat01组件时
        //User user01 = run.getBean("user01", User.class);
        //Pet cat01 = run.getBean("cat01", Pet.class);
        //若proxyBeanMethods = false，则为false
        //System.out.println("user01.getPet() == cat01：" + (user01.getPet() == cat01));

        //获取组件
        //String[] beanNamesForType = run.getBeanNamesForType(User.class);
        //for (String s : beanNamesForType) {
        //    System.out.println(s);
        //}

        //DBHelper helper = run.getBean(DBHelper.class);
        //System.out.println("helper = " + helper);

        //报错，设置@ConditionalOnBean(name = "cat01")后不满足条件，故未加入IOC容器
        //User user = myConfig.user01();
        //System.out.println("user = " + user);

        /*boolean user01 = run.containsBean("user01");
        System.out.println("user01 = " + user01);
        boolean cat01 = run.containsBean("cat01");
        System.out.println("cat01 = " + cat01);*/

        //Object xmlUser = run.getBean("xmlUser");
        //System.out.println("xmlUser = " + xmlUser);
        //Object xmlCat = run.getBean("xmlCat");
        //System.out.println("xmlCat = " + xmlCat);
    }

}
