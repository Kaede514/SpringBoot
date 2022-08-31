package com.kaede.database.controller;

import com.kaede.database.mapper.UserMapper;
import com.kaede.database.pojo.User;
import com.kaede.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kaede
 * @create 2022-08-29 13:34
 */

@Controller
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/sql")
    public String queryFormDB() {
        Integer i = jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
        return i.toString();
    }

    @ResponseBody
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

}
