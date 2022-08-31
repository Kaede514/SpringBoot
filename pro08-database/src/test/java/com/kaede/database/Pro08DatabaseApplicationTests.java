package com.kaede.database;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest(classes = Pro08DatabaseApplication.class)
@SuppressWarnings("all")
class Pro08DatabaseApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Test
    void contextLoads() {
        Integer i = jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
        System.out.println("i = " + i);
        log.info("数据源类型为 {}", dataSource.getClass());
    }

    //@Test
    public void testRedis() {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("hello", "world");
        String hello = operations.get("hello");
        System.out.println("hello = " + hello);
        System.out.println("redisConnectionFactory = " + redisConnectionFactory);
    }

}
