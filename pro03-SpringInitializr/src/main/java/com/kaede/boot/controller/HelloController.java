package com.kaede.boot.controller;

import com.kaede.boot.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kaede
 * @create 2022-08-11 16:56
 */

@RestController
public class HelloController {

    @Autowired
    private Person person;

    @RequestMapping("/person")
    public Person person() {
        String userName = person.getUserName();
        System.out.println(userName);
        return person;
    }

}
