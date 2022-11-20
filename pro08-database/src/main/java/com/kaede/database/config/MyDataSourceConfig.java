package com.kaede.database.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author kaede
 * @create 2022-08-29 13:16
 */

//@Configuration
public class MyDataSourceConfig {

    //默认的自动配置是判断容器中没有数据源才会放Hikari数据源
    //将属性与配置文件中的值绑定
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        //dataSource.setUrl();
        //dataSource.setUsername();
        //dataSource.setPassword();
        //开启监控页的监控功能和防火墙功能
        //能调用set方法设置的属性都可以在配置文件中设置
        dataSource.setFilters("stat,wall");
        return dataSource;
    }

    //配置druid的监控页功能
    @Bean
    public ServletRegistrationBean statViewServlet() {
        StatViewServlet statViewServlet = new StatViewServlet();
        //http://localhost:8080/druid即可访问监控页
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(statViewServlet, "/druid/*");
        //添加查看监控页所需的账号密码验证
        registrationBean.addInitParameter("loginUsername", "kaede");
        registrationBean.addInitParameter("loginPassword", "123456");
        return registrationBean;
    }

    //采集web-jdbc关联监控的数据
    @Bean
    public FilterRegistrationBean webStatFilter() {
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(webStatFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        filterRegistrationBean.addInitParameter("exclusions", "*.css,*.js,*.jpg,*.png,*.ico,/druid/*,/aa/**");
        return filterRegistrationBean;
    }

}
