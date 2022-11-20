package com.kaede.monitorserver;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 第三方指标监控开源项目Boot Admin Server的使用
 * 1、服务器端引入依赖
 * 2、服务器端主类上标注@EnableAdminServer开启服务监控功能
 * 3、配置端口号，防止与待监控的项目端口号冲突
 * 4、客户端引入依赖
 * 5、客户端配置spring.boot.admin.client.url=服务器的地址
 *
 */

@EnableAdminServer
@SpringBootApplication
public class Pro11MonitorServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Pro11MonitorServerApplication.class, args);
    }

}
