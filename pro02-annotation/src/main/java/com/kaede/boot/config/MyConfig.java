package com.kaede.boot.config;

import ch.qos.logback.core.db.DBHelper;
import com.kaede.boot.bean.Car;
import com.kaede.boot.bean.Pet;
import com.kaede.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @author kaede
 * @create 2022-08-10 15:19
 *
 * 1、配置类里可以使用@Bean标注在方法上给容器注册组件，以方法名作为组件的id，
 *    返回类型就是组件类型，返回的值就是组件在容器中保存的实例（默认为单例）
 *
 * 2、配置类本身也是组件
 *
 * 3、@Configuration中的proxyBeanMethods属性（默认为true）：代理bean的方法
 *      Full(proxyBeanMethods = true)：全模式，SpringBoot每次都会检查组件是否存在，若不存在则
 *                                     创建对象到IOC容器，若存在则直接从IOC容器中获取
 *      Lite(proxyBeanMethods = false)：轻量级模式，SpringBoot会跳过组件是否存在的检查，每次调
 *                                      用都会产生一个新的对象（用于解决组件依赖场景），启动快
 *
 *      最佳实战：配置类组件之间无依赖关系用Lite模式加速容器启动过程，减少判断
 *               配置类组件之间有依赖关系，方法被调用会得到之前的单实例组件，用Full模式来保证容器中的
 *               某个组件所依赖的组件就是容器中的组件
 *
 * 4、@Import({})给容器中自动创建出{}中类型的组件，默认组件的名字为全类名
 *
 * 5、条件注解（常用的）
 * 标注上方法上表示，满足某条件时方法作为组件生效；标注在类上表示，满足某条件时类中的方法作为组件生效
 * @ConditionalOnBean(name/class)：在容器中有某个组件
 * @ConditionalOnMissingBean(name/class)：在容器中不包含某个组件
 * @ConditionalOnClass(class)：当前类路径下存在对应的类（java、resources目录下）
 * @ConditionalOnMissingClass(class)：当前类路径下不存在对应的类
 *
 * 6、@ImportResource("classpath:beans.xml")可以导入Spring的配置文件，使之生效
 *
 */

@ConditionalOnMissingBean(name = "cat02")
@Import({User.class, DBHelper.class})
@Configuration(proxyBeanMethods = true)  //告诉SpringBoot这是一个配置类，放入容器中成为组件
@ImportResource("classpath:beans.xml")
//1、开启Car属性绑定功能
//2、把Car这个组件自动注册到容器中
@EnableConfigurationProperties({Car.class})
public class MyConfig {

    /**
     * 默认情况下外部无论对配置类中这个组件的注册方法调用多少次，获取的都是之前注册到容器中的单实例对象
     */
    //@ConditionalOnBean(name = "cat02")
    @Bean   //给容器添加组件，以方法名作为组件的id，返回类型就是组件类型，返回的值就是组件在容器中保存的实例
    public User user01() {
        User zhangsan = new User("zhangsan", 18);
        //user01组件依赖了cat01  组件
        zhangsan.setPet(cat());
        return zhangsan;
    }

    @Bean("cat01")    //可以在标签里自定义组件名
    public Pet cat() {
        return new Pet("tomcat");
    }

}
