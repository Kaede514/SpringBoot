package com.kaede.hello.property.service;

import com.kaede.hello.property.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author kaede
 * @create 2022-08-31 11:16
 */

//默认不要放在容器中
public class HelloService {

    @Autowired
    private HelloProperties helloProperties;

    public String sayHello(String name) {
        return helloProperties.getPrefix() + " " + name + " " + helloProperties.getSuffix();
    }

}
