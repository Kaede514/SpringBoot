package com.kaede.principle.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author kaede
 * @create 2022-08-31 15:22
 */

public class MyApplicationListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("MyApplicationListener...onApplicationEvent...");
    }

}
