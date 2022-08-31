package com.kaede.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 指标监控
 * 1、SpringBoot Actuator
 * SpringBoot抽取了Actuator场景，使得每个微服务快速引用
 * 即可获得生产级别的应用监控、审计等功能
 * 如何使用：1）引入场景
 *          2）访问http://localhost:8080/actuator/**
 * 配置项前缀是management，以web方式暴露端点即可在网页上访问
 * 2、Actuator Endpoint
 *    1）常用端点：health：健康状况
 *               metrics：运行时指标
 *               loggers：日志记录
 *       management.endpoint.xxx：具体配置某个端点
 *    2）Health Endpoint
 *       - health endpoint返回的结果是经过一系列健康检查后的汇总报告
 *       - 很多健康检查默认配置好了，如数据库、redis等
 *       - 可以简单地添加自定义的健康检查机制
 *    3）Metrics Endpoint
 *       提供详细的、层级的、空间级别的指标信息，这些信息可以被push（主动推送）
 *       或者pull（被动获取）的方式得到
 *       http://localhost:8080/actuator/metrics下的是监控指标概览，可以在
 *       url后面添加 /监控指标名 查看该项的完整信息
 *    4）管理Endpoint：
 *       所有监控端点默认开启，可手动关闭，然后设置management.endpoint.<endpointName>.enabled=true
 *       手动开启某个端点
 * 3、定制Endpoint
 *    1）定制health信息：在AbstractHealthIndicator的继承类中自定义并放入容器（类名必须以HealthIndicator结尾）
 *    2）定制info信息（默认无内容），需设置management.info.env.enabled=true，然后两种方式：
 *       1、在配置文件中自定义
 *       2、在InfoContributor的实现类中自定义并放入容器
 *       两种方式同时使用会显示合并结果，若存在重名项，则第二种方式会覆盖第一种方式的值
 *    3）定制metrics信息
 *    4）定制Endpoint：类上标注@Component，@Endpoint(id = "端点名")，给两个方法上
 *       分别标注@ReadOperation和@WriteOperation
 *       可在http://localhost:8080/actuator查看到自定义的端点，也可访问端点
 *
 */

@SpringBootApplication
public class Pro10MonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(Pro10MonitorApplication.class, args);
    }

}
