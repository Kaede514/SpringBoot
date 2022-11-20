package com.kaede.boot.config;

import ch.qos.logback.core.db.DBHelper;
import com.kaede.boot.bean.Car;
import com.kaede.boot.bean.Pet;
import com.kaede.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @author kaede
 * @create 2022-08-10 15:19
 */

//@ConditionalOnBean(name = "cat02")
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
    //@ConditionalOnBean(name = "cat01")
    @Bean
    public User user01() {
        User zhangsan = new User("zhangsan", 18);
        //user01组件依赖了cat01组件
        zhangsan.setPet(cat());
        return zhangsan;
    }

    @Bean("cat01")    //可以在标签里自定义组件名
    public Pet cat() {
        return new Pet("tomcat");
    }

}
