package com.kaede.servlet.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author kaede
 * @create 2022-08-24 9:01
 */

//注意：servlet中是*，spring中是**
//@WebFilter({"/css/*","/image/*"})
@Slf4j
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("MyFilter工作");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("MyFilter初始化完成");
    }

    @Override
    public void destroy() {
        log.info("MyFilter销毁完成");
    }
}
