package com.kaede.feature.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author kaede
 * @create 2022-08-14 14:57
 *
 * 登录检查
 * 1、配置好拦截器要拦截哪些请求
 * 2、把这些配置放在容器中
 */

//这里不需要放入IOC容器，因为在AdminWebConfig中配置时是通过
//new对象进行的，在SpringMVC中是通过自动分配进行的
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("拦截的路径是：" + requestURI);
        //登录检查逻辑
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        if(loginUser != null) {
            //放行
            return true;
        }
        //拦截住，未登录，跳转到登录页
        //session.setAttribute("msg","请先登录");
        //重定向取不出变量
        //response.sendRedirect("/");
        //th:text="${msg}" 获取请求域中的数据
        //th:text="${session.msg}" 获取会话域中的数据
        request.setAttribute("msg","请先登录");
        request.getRequestDispatcher("/").forward(request,response);
        return false;
    }

    //目标方法执行完成以后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    //页面渲染以后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
