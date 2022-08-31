package com.kaede.boot.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kaede
 * @create 2022-08-11 19:10
 */

@RestController
public class HelloController {

    //返回的是aaa，而不是图片
    @RequestMapping("/lunasama.jpg")
    public String hello() {
        return "aaa";
    }

}
