package com.kaede.principle.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author kaede
 * @create 2022-08-31 9:31
 */

@Data
@Profile("online")
@Component
@ConfigurationProperties("person")
public class Boss implements Person {

    private String name;
    private Integer age;

}
