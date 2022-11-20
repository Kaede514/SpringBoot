package com.kaede.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringMVC自动配置概览：
 * 1、内容协商视图解析器和BeanName视图解析器
 * 2、静态资源
 * 3、自动注册 Converter，GenericConverter，Formatter（转化日期格式等）
 * 4、支持 HttpMessageConverters
 * 5、自动注册 MessageCodesResolver（国际化用）
 * 6、静态index.html页支持
 * 7、自定义Favicon
 * 8、自动使用 ConfigurableWebBindingInitializer，（DataBinder负责将请求数据绑定到JavaBean上）
 *
 * 简单功能分析
 * 1、静态资源访问
 *    1）只要静态资源放在类路径下/static、/resources、/public或/META-INF/resources
 *      用当前项目根路径 + 静态资源名就能访问到
 *      原理：静态映射/**，请求进来先找controller，若controller不能处理，则交给
 *           静态资源处理器（即先DispatcherServlet再DefaultServlet）处理，找不到报404
 *      改变默认的静态资源路径：配置文件中spring:web:resources:static-locations中配置
 *    2）静态资源访问前缀（默认无前缀）（常用，方便拦截器根据指定前缀进行放行）
 *      访问路径 = 当前项目 + 配置文件中spring:mvc:static-path-pattern的前缀 + 静态资源名
 *    3）支持webjars
 * 2、欢迎页支持（可直接通过localhost:8080/访问）
 *    静态资源路径下放入index.html（可以配置静态资源的路径，但配置静态资源的访问前缀会失效）
 * 3、静态资源配置原理
 *   SpringBoot启动默认加载 xxxAutoConfiguration类（自动配置类）
 *   SpringMVC功能的自动配置类WebMvcAutoConfiguration
 *   配置文件的相关属性和xxx进行了绑定。WebMvcProperties==spring.mvc、ResourceProperties==spring.resources
 *   ps：1、一个配置类如果只有一个有参构造器，通过代码可知配置类中有参构造器所有参数的值都会从容器中确定
 *      2、资源处理的默认规则：配置spring:web:resources:add-mappings:false，可以禁用所有静态资源路径映射
 *
 */

@SpringBootApplication
public class Pro04WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(Pro04WebApplication.class, args);
    }

}
