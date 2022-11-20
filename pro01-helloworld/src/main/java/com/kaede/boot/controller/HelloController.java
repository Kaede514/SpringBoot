package com.kaede.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kaede
 * @create 2022-08-09 20:35
 */

//@ResponseBody
//@Controller
@RestController //@Controller和@ResponseBody合体
public class HelloController {

    @RequestMapping("/hello")
    public String  handle01() {
        return "hello, SpringBoot! 你好";
    }

}
