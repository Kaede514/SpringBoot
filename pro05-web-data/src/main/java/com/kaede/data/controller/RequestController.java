package com.kaede.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kaede
 * @create 2022-08-12 15:38
 *
 * @RequestAttribute：取出请求域中（服务器向服务器转发）的值（一次请求）
 */

@Controller
public class RequestController {

    @GetMapping("/goTo")
    public String goToPage(HttpServletRequest request) {
        request.setAttribute("msg", "this is a message");
        request.setAttribute("code", 200);
        return "forward:/success";
    }

    @ResponseBody
    @GetMapping("/success")
    public Map<String,Object> success(@RequestAttribute(value = "msg", required = false) String msg,
                                      @RequestAttribute(value = "code", required = false) Integer code,
                                      HttpServletRequest request) {
        //Object msg1 = request.getAttribute("msg");
        Map<String, Object> map = new HashMap<>();
        //map.put("msg", msg);
        //map.put("code", code);
        //map.put("msg1", msg1);
        map.put("hello", request.getAttribute("hello"));
        map.put("world", request.getAttribute("world"));
        map.put("msg", request.getAttribute("msg"));
        return map;
    }

    @GetMapping("/params")
    public String testParam(Map<String,Object> map,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        map.put("hello", "world666");
        model.addAttribute("world", "hello666");
        request.setAttribute("msg","hello, world");
        Cookie cookie = new Cookie("c1", "v1");
        response.addCookie(cookie);
        return "forward:/success";
    }

}
