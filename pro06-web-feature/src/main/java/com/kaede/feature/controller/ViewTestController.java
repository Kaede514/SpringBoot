package com.kaede.feature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author kaede
 * @create 2022-08-14 8:56
 */

@Controller
public class ViewTestController {

    @GetMapping("/kaede")
    public String kaede(Model model) {
        //Model中的数据会被放在请求域中
        model.addAttribute("msg", "hehe");
        model.addAttribute("link", "http://www.baidu.com");
        return "success";
    }

}
