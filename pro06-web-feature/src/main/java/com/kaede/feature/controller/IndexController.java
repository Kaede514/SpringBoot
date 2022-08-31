package com.kaede.feature.controller;

import com.kaede.feature.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author kaede
 * @create 2022-08-14 9:46
 */

@Controller
public class IndexController {

    //进入登录页
    @GetMapping({"/","/login"})
    public String loginPage() {
        return "admin_login";
    }

    //处理登录请求
    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model) {
        if(StringUtils.hasLength(user.getUserName()) && "123456".equals(user.getPassword())) {
            //把登录成功的用户保存起来
            session.setAttribute("loginUser", user);
            //登录成功，重定向到main.html（重定向防止刷新时表单重复提交）
            return "redirect:/main";
        } else{
            //回到登录页
            model.addAttribute("msg", "账号密码不正确");
            return "admin_login";
        }
    }

    //去main页面
    @GetMapping("/main")
    public String mainPage(HttpSession session, Model model) {
        //判断是否登录（最好使用拦截器或者过滤器等）
        Object loginUser = session.getAttribute("loginUser");
        if(loginUser != null) {
            return "admin_main";
        } else{
            //回到登录页
            model.addAttribute("msg", "请重新登录");
            return "admin_login";
        }
    }

}
