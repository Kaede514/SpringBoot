package com.kaede.boot.controller;

import com.kaede.boot.bean.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kaede
 * @create 2022-08-09 20:35
 */

@Slf4j
@RestController
public class HelloController {

    @Autowired
    private Car car;

    @RequestMapping("/car")
    public Car car() {
        return car;
    }

    @RequestMapping("/hello")
    public String hello(String name) {
        log.info("请求成功发送...");
        return "hello, " + name;
    }

}
