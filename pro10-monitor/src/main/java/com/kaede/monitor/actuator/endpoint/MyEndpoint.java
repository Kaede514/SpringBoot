package com.kaede.monitor.actuator.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * @author kaede
 * @create 2022-08-30 18:20
 */

//自定义的端点默认开启
@Component
//端点名
@Endpoint(id = "myendpoint")
public class MyEndpoint {

    //将来访问端点时显示的信息
    @ReadOperation
    public Map getProjectInfo(){
        return Collections.singletonMap("info","project started...");
    }

    @WriteOperation
    private void restartProject(){
        System.out.println("project restarted....");
    }

}
