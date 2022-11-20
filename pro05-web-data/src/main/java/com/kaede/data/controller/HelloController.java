package com.kaede.data.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author kaede
 * @create 2022-08-11 19:10
 */

@RestController
public class HelloController {
    @GetMapping("/user")
    public String getUser(){
        return "GET-张三";
    }

    @PostMapping("/user")
    public String saveUser(){
        return "POST-张三";
    }

    @PutMapping("/user")
    public String putUser(){
        return "PUT-张三";
    }

    @DeleteMapping("/user")
    public String deleteUser(){
        return "DELETE-张三";
    }
}
