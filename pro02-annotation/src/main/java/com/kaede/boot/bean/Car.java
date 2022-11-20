package com.kaede.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author kaede
 * @create 2022-08-10 17:16
 *
 * 让JavaBean和配置文件里的属性绑定：
 * 使用@ConfigurationProperties(prefix = "mycar")将类中的属性和配置
 * 文件中以prefix为前缀的属性绑定，然后用名字匹配赋值，为了使之生效，需把
 * 该类放入容器中，只有在容器中的组件才会拥有SpringBoot提供的强大功能
 *
 * 方式1、@Component标记为组件放入IOC容器，让IOC管理分配
 * 方式2：在所需该类的配置类中使用@EnableConfigurationProperties(class)开启某类的属性配置功能，
 *       并将该类自动注册到容器中（适用于引用的第三方包里的，一般都配置了@ConfigurationProperties）
 * 方式3：@Import(class)，和方式二差不多
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Component
@ConfigurationProperties(prefix = "mycar")
public class Car {

    private String brand;
    private Integer price;

}
