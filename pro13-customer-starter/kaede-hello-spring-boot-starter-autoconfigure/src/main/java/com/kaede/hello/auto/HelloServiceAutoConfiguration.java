package com.kaede.hello.auto;

import com.kaede.hello.property.HelloProperties;
import com.kaede.hello.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kaede
 * @create 2022-08-31 11:49
 */

@Configuration
//将HelloProperties和配置文件进行绑定并放入容器中
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {

    @ConditionalOnMissingBean(HelloService.class)
    @Bean
    public HelloService helloService() {
        HelloService helloService = new HelloService();
        return helloService;
    }

}
