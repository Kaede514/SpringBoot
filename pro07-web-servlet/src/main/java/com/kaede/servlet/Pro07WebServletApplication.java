package com.kaede.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * web原生组件注入：
 * 方式1、主程序类中标注@ServletComponentScan自动扫描包下的servlet组件，servlet上
 *       标注@WebServlet(映射路径)（若有Spring拦截器，则会直接响应，不会经过拦截器）
 *       @WebFilter(拦截路径)拦截器、@WebListener监听器
 * 方式2、使用RegistrationBean（推荐）
 *       创建之后，容器中存在的servlet
 *       1、MyServlet  -->  /my, /my2
 *       2、DispatcherServlet  -->  /
 * 拓展：DispatcherServlet如何注册进来
 * 容器中自动配置了DispatcherServlet，属性绑定到WebMvcProperties，对应的配置文件配置项
 * 是spring.mvc，通过ServletRegistrationBean<DispatcherServlet>把DispatcherServlet
 * 配置进来，默认映射的是/路径
 * Tomcat-Servlet：多个Servlet都能处理到同一层路径，精确优先原则
 * A:/my/*, B:/my/1，则发送/my1到B处理，发送/my/2到A处理
 * 请求  -->  DispatcherServlet /  -->  Spring流程
 *           MyServlet /my/*,/my2  -->  Tomcat处理
 *
 * 嵌入式Servlet容器
 * 1、切换嵌入式Servlet容器
 *    1）默认支持的WebServlet
 *       - Tomcat、Jetty、Undertow
 *       - ServletWebServerApplicationContext容器启动寻找
 *         ServletWebServerFactory并引导创建服务器
 *    2）切换服务器
 *       排除tomcat场景，导入其他服务器场景
 *    3）原理
 *       - SpringBoot应用启动发现当前是web应用（web场景包中导入了tomcat）
 *       - web应用会创建一个web版的IOC容器ServletWebServerApplicationContext，
 *         该容器在启动时会寻找ServletWebServerFactory（生产Servlet的web服务器）
 *       - SpringBoot底层默认有很多的WebServer工厂，如TomcatServletWebServerFactory、
 *         JettyServletWebServerFactory、UndertowServletWebServerFactory
 *       - 底层会有一个自动配置类ServletWebServerFactoryAutoConfiguration，导入了
 *         ServletWebServerFactoryConfiguration配置类，该配置类根据条件判断系统中导入的
 *         web服务器的包（web场景默认导入tomcat的包），容器中就会有相应的ServletWebServerFactory，
 *         该工厂最后创建出相应的服务器（拥有初始化方法，其中 --> this.tomcat.start()）并启动
 *       内嵌服务器，实际上就是手动调用启动服务器的代码（服务器的核心jar包存在）
 * 2、定制Servlet容器
 *    1）修改配置文件下的server.xxx（推荐）
 *    2）自定义ConfigurableServletWebServerFactory
 *    3）实现WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>
 *       把配置文件的值和ServletWebServerFactory进行绑定
 *       xxxCustomizer：定制化器，可以改变xxx的默认规则
 *    Filter和Interceptor
 *    1、Filter是Servlet定义的原生组件，好处：脱离Spring应用也能使用
 *    2、Interceptor是Spring定义的接口，好处：可以使用Spring的自动装配等功能
 *
 *
 * 定制化原理
 * 1、定制化的常见方式
 *    1）修改配置文件（推荐）
 *    2）编写自定义的配置类xxxConfiguration + @Bean替换、增加容器中的默认组件
 *    3）web应用实现WebMvcConfigurer即可定制化web功能（重要） + @Bean给容器中再扩展一些功能
 *      （也可在配置类中使用@Bean将WebMvcConfigurer放入容器中）
 *      （再加上@EnableWebMvc会全面接管SpringMVC，SpringMVC的自动配置会全部失效）
 *      原理：1）WebMvcAutoConfiguration是默认的SpringMVC的自动配置功能类（静态资源...）
 *           2）一旦使用@EnableWebMvc，会@Import(DelegatingWebMvcConfiguration.class)
 *           3）DelegatingWebMvcConfiguration的作用（只保证SpringMVC最基本的使用）
 *              - 获取系统中所有的WebMvcConfigurer，所有功能的定制都由这些一起生效
 *              - 自动配置了一些非常底层的组件，这些组件所依赖的组件都从容器中获取
 *           4）WebMvcConfiguration里的配置要生效必须@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)，
 *             但DelegatingWebMvcConfiguration继承了WebMvcConfigurationSupport，
 *             故@EnableWebMvc会导致WebMvcConfiguration失效
 *    4）xxxCustomizer
 * ps：将组件放入容器中：1）写一个类使用@Component 2）配置类中使用@Bean
 *
 * 2、原理分析流程
 * 场景starter -> xxxAutoConfiguration -> 导入xxx组件 -> 绑定xxxProperties -> 绑定配置文件项
 *
 */

@ServletComponentScan(basePackages = "com.kaede.servlet")
@SpringBootApplication
public class Pro07WebServletApplication {

    public static void main(String[] args) {
        SpringApplication.run(Pro07WebServletApplication.class, args);
    }

}
