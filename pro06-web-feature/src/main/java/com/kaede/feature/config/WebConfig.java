package com.kaede.feature.config;

import com.kaede.feature.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author kaede
 * @create 2022-08-28 13:00
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**") //所有请求都会被拦截，静态资源也会拦截，使得页面无样式
                .excludePathPatterns("/","/login","/css/**","/fonts/**","/js/**","/images/**"); //放行的请求
        //此处可以设置静态资源的访问路径，然后放行访问路径下的所有就可以
    }

}
