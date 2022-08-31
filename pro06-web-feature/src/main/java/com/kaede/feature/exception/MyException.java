package com.kaede.feature.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author kaede
 * @create 2022-08-23 18:59
 */

//标注此异常最终可返回状态码信息
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "我的自定义异常")
public class MyException extends RuntimeException{

    public MyException() {

    }

    public MyException(String message) {
        super(message);
    }

}
