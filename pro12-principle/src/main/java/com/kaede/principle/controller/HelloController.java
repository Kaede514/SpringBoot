package com.kaede.principle.controller;

import com.kaede.principle.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kaede
 * @create 2022-08-31 8:53
 */

@RestController
public class HelloController {

    //可以指定获取不到时的默认值@Value("${person.name:unknow}")
    @Value("${person.name}")
    private String name;

    @Autowired
    private Person person;

    @GetMapping("/")
    public String hello() {
        return "hello " + name + "\n" + person + " " + person.getClass();
    }

    @Value("${MAVEN_HOME}")
    private String mavenHome;

    @Value("${os.name}")
    private String osName;

    @GetMapping("/msg")
    public String getMsg() {
        return mavenHome + " " + osName;
    }

}
