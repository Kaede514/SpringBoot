package com.kaede.feature.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kaede
 * @create 2022-08-23 19:23
 */

//自定义的异常解析器
@Component
//默认为最低优先级，执行不到，会被前面的先处理，故设为最高优先级（数字越小优先级越高）
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomerHandlerExceptionResovler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            //会交给tomcat处理
            response.sendError(511, "我指定的错误");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView();
    }
}
