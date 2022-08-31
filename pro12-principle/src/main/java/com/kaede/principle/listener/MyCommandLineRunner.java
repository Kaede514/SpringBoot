package com.kaede.principle.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author kaede
 * @create 2022-08-31 15:33
 *
 * 应用启动做一个一次性事件
 */

@Order(2)
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("MyCommandLineRunner...run...");
    }

}
