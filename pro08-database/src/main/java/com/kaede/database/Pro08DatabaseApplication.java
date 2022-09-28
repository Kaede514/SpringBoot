package com.kaede.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 数据访问
 * SQL
 * 1、数据源的自动配置
 *    1）导入jdbc场景（内含数据源、jdbc、事务、）
 *    2）导入mysql驱动
 *       若要修改版本：1、直接依赖引入具体版本（maven的就近依赖原则）
 *                   2、重新声明版本（maven的属性的就近优先原则）
 *    3）分析自动配置并修改配置项
 *      自动配置的类
 *      DataSourceAutoConfiguration：数据源的自动配置
 *          - 修改数据源相关的配置，spring.datasource
 *          - 容器中没有DataSource时，数据库连接池会自动配置（默认是Hikari）
 *      DataSourceTransactionManagerAutoConfiguration：事务管理器的自动配置
 *      JdbcTemplateAutoConfiguration：JdbcTemplate的自动配置（操作数据库）
 *          - 修改JdbcTemplate的配置，spring.jdbc
 *          - @Bean @Primary 容器中有JdbcTemplate这个组件
 *      XADataSourceAutoConfiguration：分布式事务相关的自动配置
 * 2、使用Druid数据源
 *    1）自定义方式
 *       - 引入依赖
 *       - 创建配置类进行配置
 *    2）使用官方starter方式
 *       - 引入druid-starter
 *       - 分析自动配置
 *         扩展配置项spring.datasource.druid
 *         DruidSpringAopConfiguration.class，监控Spring组件的，spring.datasource.druid.aop-patterns
 *         DruidStatViewServletConfiguration.class，监控页的配置，spring.datasource.druid.stat-view-servlet
 *         DruidWebStatFilterConfiguration.class，web监控的配置，spring.datasource.druid.web-stat-filter
 *         DruidFilterConfiguration.class，所有Druid自己filter的配置，spring.filters，spring.filter
 * 3、整合MyBatis
 *    1）引入第三方的starter（内含jdbc、mybatis自动配置类、mybatis、mybatis和Spring的整合）
 *    2）全局配置文件
 *       SqlSessionFactory（自动配置好了） ->
 *       SqlSession（自动配置好了SqlSessionTemplate组合了SqlSession） ->
 *       @Import(AutoConfiguredMapperScannerRegister.class)（自动扫描标注了@Mapper的mapper接口）
 *      （也可以在某个配置类如主类上标注@MapperScan(包)指定包下的所有都为mapper且都放入容器中）
 *       Mapper（只要写的操作MyBatis的mapper接口标注了@Mapper就会被自动扫描进来）
 * 配置项前缀为mybatis
 * 修改配置：方式1：创建mybatis配置文件，yaml中指定配置文件位置，在配置文件中配置
 *         方式2：直接在yaml中配置（两种配置方式不能同时使用）
 * 可以不写mybatis配置文件，所有配置都写在yaml中即可（推荐）
 * 总结：1、导入mybatis官方starter
 *      2、在application.yaml中 1）指定配置文件的位置并在其中配置，2）直接在yaml中配置（推荐）
 *      3、编写mapper接口（标注@Mapper或在配置类上标注@MapperScan(包)）
 *      4、编写sql映射文件并绑定mapper接口
 *
 * NoSQL
 * 1、redis自动配置
 *    1）引入redis依赖
 *    2）自动配置
 *       - RedisAutoConfiguration自动配置类，RedisProperties属性类，配置项前缀spring.redis
 *       - 连接工厂自动准备好了，LettuceConnectionConfiguration、JedisConnectionConfiguration
 *       - 自动注入了RedisTemplate<Object,Object>（xxxTemplate就是来操作xxx的）
 *       - 自动注入了StringRedisTemplate（k、v都是String）
 *       - 底层只要使用RedisTemplate、StringRedisTemplate就可以操作redis
 * 2、redis客户端由lettuce切换为jedis
 *    引入jedis依赖，然后移除lettuce依赖或配置redis
 *
 *
 */

//@MapperScan("com.kaede.database.mapper")
@SpringBootApplication
public class Pro08DatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(Pro08DatabaseApplication.class, args);
    }

}
