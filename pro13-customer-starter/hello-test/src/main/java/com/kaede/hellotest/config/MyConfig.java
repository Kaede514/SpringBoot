package com.kaede.hellotest.config;

import com.kaede.hello.property.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kaede
 * @create 2022-08-31 12:14
 */

@Configuration
public class MyConfig {

    @Bean
    public HelloService helloService() {
        HelloService helloService = new HelloService();
        return helloService;
    }

}
