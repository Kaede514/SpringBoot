package com.kaede.monitor.actuator.info;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author kaede
 * @create 2022-08-30 18:00
 */

@Component
public class AppInfo implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("msg","hello").withDetail("author","kaede2")
                .withDetails(Collections.singletonMap("hobby","music"));
    }

}
