package com.kaede.servlet.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author kaede
 * @create 2022-08-29 9:39
 */

@Configuration(proxyBeanMethods = false)
public class WebConfig implements WebMvcConfigurer{

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setPort(8888);
        return factory;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //访问/aa下的所有请求都会到classpath:/static/下进行匹配
        registry.addResourceHandler("/aa/**").addResourceLocations("classpath:/static/");
    }

}
