package com.kaede.monitor.actuator.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kaede
 * @create 2022-08-30 17:23
 */

@Component
public class MyComponentHealthIndicator extends AbstractHealthIndicator {

    //编写真实的检查方法，检查组件是否健康
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        //如mongdb，获取连接进行检查
        Map<String ,Object> map = new HashMap<>();
        //进行检查
        if(true) {
            //健康
            //builder.up();
            builder.status(Status.UP);
            map.put("count", 1);
            map.put("ms", 100);
        } else {
            //不健康
            //builder.down();
            builder.status(Status.OUT_OF_SERVICE);
            map.put("err", "连接超时");
            map.put("ms", 3000);
        }
        //更详细的信息
        builder.withDetail("code", 100).withDetails(map);
    }

}
