package com.kaede.feature.controller;

import com.kaede.feature.exception.MyException;
import com.kaede.feature.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

/**
 * @author kaede
 * @create 2022-08-28 11:23
 */

@Controller
public class TableController {

    @GetMapping("/testerror")
    public String testError(Model model) {
        int i = 10 / 0;
        return "admin_main";
    }

    @GetMapping("/ex")
    public String ex() {
        if(true) {
            throw new MyException();
        }
        return "admin_main";
    }

}
