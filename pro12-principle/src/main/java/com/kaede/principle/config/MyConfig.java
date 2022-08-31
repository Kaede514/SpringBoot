package com.kaede.principle.config;

import com.kaede.principle.bean.Color;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author kaede
 * @create 2022-08-31 9:39
 */

@Configuration
public class MyConfig {

    @Profile("online")
    @Bean
    public Color red() {
        return new Color("red");
    }

    @Profile("test")
    @Bean
    public Color blue() {
        return new Color("blue");
    }

}
