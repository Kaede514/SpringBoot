package com.kaede.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author kaede
 * @create 2022-08-09 20:33
 *
 * 主程序类，主配置类
 *
 * SpringBoot优势：
 * 1、可以创建独立的Spring应用
 * 2、内嵌web服务器（如tomat、jetty等）
 * 3、提供自动starter依赖，简化构建配置
 * 4、自动配置Spring（整合mvc、mybatis配置）以及第三方功能（redis、mysql等）
 * 4、提供生产级别的监控、健康检查及外部化配置
 * 5、无代码生成、无需编写xml（基于Spring的自动装配和条件注入等功能实现）
 *
 * @SpringBootApplication注解告诉SpringBoot这是一个SpringBoot应用，
 * 只需在主程序类中运行main方法即可运行整个项目（内置tomcat服务器）
 *
 * SpringBoot为了简化起见，将所有的配置都可以抽取在一个配置文件中，配置文件名
 * 固定为application.properties，在其中可以修改设置（官方文档中有配置及默认值）
 *
 * SpringBoot开发流程：
 * 1、创建maven工程
 * 2、引入依赖
 * 3、创建主程序类
 * 4、编写业务（和以前相同）
 * 5、测试（运行主程序类的main方法即可）
 * 6、简化配置（application.properties）
 * 7、简化部署（引入插件，把项目打成jar包，直接在目标服务器执行即可）
 *
 * SpringBoot自动配置：
 * 自动配好tomcat：1）引入tomcat依赖（web场景的starter中引入了）
 *               2）配置tomcat
 * 自动配好SpringMVC：1）引入SpringMVC全套组件（web场景的starter中引入了）
 *                  2）自动配好SpringMVC常用组件/功能
 * 自动配置好web中的常见功能，如字符编码解析器、视图解析器、文件上传解析器、json...
 *                  1）自动配置好了所有web开发的常见场景
 * 默认的包结构：主程序所在的包及下面的所有子包里的所有组件都会被默认扫描到，无需以前
 *             的包扫描配置，也可以手动规定要扫描的包：
 *             1、@SpringBootApplication(scanBasePackages="com.kaede")
 *             2、或者使用@ComponentScan("com.kaede")指定扫描路径
 * 各种配置都拥有默认值，如tomcat有默认端口8080
 *             1）默认配置最终都是映射到某一个类上
 *             2）配置文件中的值最终会绑定到某个类上，这个类会在容器中创建对象
 * 按需加载所有自动配置项：1）只会开启所引入场景的自动配置
 *                      2）SpringBoot所有的自动配置功能都在spring-boot-starter
 *                        所依赖的spring-boot-autoconfigure包中
 */

//告诉SpringBoot这是一个SpringBoot应用
//@SpringBootApplication等同于以下三个注解
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("com.kaede")
public class MainApplication {

    public static void main(String[] args) {
        //1、返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        //2、查看容器里的组件
        /*
            控制台打印的组件中发现中有dispatcherServlet，浏览器中访问/hello
            发现中文没有乱码，说明也配置好了字符编码拦截器，组件中也确实有
            characterEncodingFilter...
         */
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

    }

}
