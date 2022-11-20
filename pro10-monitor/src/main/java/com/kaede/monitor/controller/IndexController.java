package com.kaede.monitor.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kaede
 * @create 2022-08-30 15:54
 */

@RestController
public class IndexController {

    @RequestMapping("/")
    public String index() {
        //每调用一次方法增加一次次数
        counter.increment();
        return "index";
    }

    Counter counter;

    //构造器注入
    public IndexController(MeterRegistry meterRegistry) {
        //自定义将要展示的指标名
        counter = meterRegistry.counter("indexController.index.count");
    }

}
