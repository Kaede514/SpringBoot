package com.kaede.hello.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author kaede
 * @create 2022-08-31 11:21
 */

@ConfigurationProperties("kaede.hello")
public class HelloProperties {

    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

}
