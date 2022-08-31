package com.kaede.hellotest.controller;

import com.kaede.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kaede
 * @create 2022-08-31 12:07
 */

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String sayHello() {
        String hello = helloService.sayHello("kaede");
        return hello;
    }

}
