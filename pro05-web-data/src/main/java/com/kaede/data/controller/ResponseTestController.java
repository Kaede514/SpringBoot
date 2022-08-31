package com.kaede.data.controller;

import com.kaede.data.bean.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author kaede
 * @create 2022-08-13 14:23
 */

@Controller
public class ResponseTestController {

    /**
     * 场景：
     * 1、浏览器发请求返回xml        [application/xml]       jacksonXmlConverter
     * 2、如果是ajax请求返回json     [application/json]      jacksonJsonConverter
     * 3、如果是app请求，返回自定义协议数据     [application/xxx]       xxxConverter
     *   步骤：1、添加自定义的MessageConverter到系统底层
     *        2、系统底层就会统计出所有MessageConverter能操作哪些类型
     *        3、客户端内容协商[xxx --> xxx]
     */
    @ResponseBody
    @GetMapping("/test/person")
    public Person getPerson() {
        return new Person("zhangsan",18, new Date(), null);
    }

}
