package com.kaede.feature.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author kaede
 * @create 2022-08-23 18:47
 *
 * 处理整个web的异常
 */

//标记为异常处理器
/*@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //处理异常
    @ExceptionHandler({ArithmeticException.class,NullPointerException.class})
    public String handlerArithException(Exception ex) {
        log.error("异常是：{}", ex);
        return "admin_main";  //视图地址或ModelAndView
    }

}*/
