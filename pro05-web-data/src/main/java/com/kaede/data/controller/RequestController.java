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
        Object msg1 = request.getAttribute("msg");
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("code", code);
        map.put("msg1", msg1);

        Object hello = request.getAttribute("hello");
        Object world = request.getAttribute("world");
        Object msg2 = request.getAttribute("msg2");
        map.put("hello", hello);
        map.put("world", world);
        map.put("msg2",msg2);
        return map;
    }

    /*
        1、矩阵变量语法：用;隔开键值对，多个变量以/区分，每个变量的第一个;前是
           访问路径，;后是矩阵变量
            /cars/sell;low=34;brand=byd,audi,yd
            /boss/1;age=20/2;age=18
        2、SpringBoot默认禁用了矩阵变量的功能
           手动开启：对于路径的处理，都是使用UrlPathHelper进行解析，其中属性
           removeSemicolonContent（移除分号后内容）用于支持矩阵变量，设为false即可
        3、当有多个重名变量时，矩阵路径必须有url路径变量pathVar才能被解析
    */
    @ResponseBody
    @GetMapping("/cars/{path}")
    public Map<String, Object> carsSell(@MatrixVariable("low") Integer low,
                                        @MatrixVariable("brand") List<String> brands,
                                        @PathVariable("path") String path) {
        Map<String, Object> map = new HashMap<>();
        map.put("low", low);
        map.put("brand", brands);
        map.put("path", path);
        return map;
    }

    // /boss/1;age=20/2;age=18
    @ResponseBody
    @GetMapping("/boss/{bossId}/{empId}")
    public Map<String, Object> boss(@MatrixVariable(value = "age", pathVar = "bossId") Integer bossAge,
                                    @MatrixVariable(value = "age", pathVar = "empId") Integer empAge) {
        Map<String, Object> map = new HashMap<>();
        map.put("bossAge", bossAge);
        map.put("empAge", empAge);
        return map;
    }

    @GetMapping("/params")
    public String testParam(Map<String,Object> map,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        map.put("hello", "world666");
        model.addAttribute("world", "hello666");
        request.setAttribute("msg2","hello, world");
        Cookie cookie = new Cookie("c1", "v1");
        cookie.setDomain("localhost");
        response.addCookie(cookie);
        return "forward:/success";
    }

}
