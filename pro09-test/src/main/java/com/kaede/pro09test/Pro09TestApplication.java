package com.kaede.pro09test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 单元测试
 * 1、JUnit5的变化
 * JUnit5 = JUnit Platform + JUnit Jupiter + JUnit Vintage
 * Junit Platform是在JVM上启动测试框架的基础，不仅支持Junit自制的测试引擎，其他测试引擎也都可以接入
 * JUnit Jupiter提供了JUnit5的新的编程模型，是JUnit5新特性的核心，内部包含了一个测试引擎，用于在Junit Platform上运行
 * JUnit Vintage提供了兼容JUnit4.x,Junit3.x的测试引擎
 * 注意：SpringBoot2.4以上版本移除了默认对Vintage的依赖，如果需要兼容junit4需要自行引入
 * 引入Junit5（spring-boot-starter-test）
 * SpringBoot整合Junit后，测试：1）编写测试方法（@Test需使用Junit5版本的注解）
 *                            2）Junit类具有Spring的功能，如@Autowired（自动装配组件）、
 *                               @Transactional（标注在单元测试方法上，测试完毕后会自动回滚）
 * 2、Junit5常用注解
 * @Test：表示该方法为测试方法，可由Jupiter提供额外测试
 * @DisplayName：为测试类或测试方法设置展示名称
 * @BeforeEach：在每个单元测试方法运行之前都需执行
 * @AfterEach：在每个单元测试方法结束以后都需执行
 * @BeforeAll：在所有单元测试方法运行之前执行（静态方法）
 * @AfterAll：在所有单元测试方法结束之后执行（静态方法）
 * @Disabled：表示测试类或测试方法不执行
 * @Timeout：表示测试方法运行如果超过了指定时间将会返回错误
 * @@RepeatedTest：表示测试方法执行的次数
 * @ExtendWith：为测试类或测试方法提供扩展类引用
 * ps：在类上标注@SpringBootTest就可以使用Spring的功能，如@Autowired
 * @SpringBootTest中含@BootstrapWith(SpringBootTestContextBootstrapper.class)
 *                   @ExtendWith({SpringExtension.class})
 * 3、断言
 * 检查业务逻辑返回的数据是否合理，所有测试运行结束后，会有详细的测试报告（用maven中的test才会有）
 * 若前面的断言失败，则后面的代码都不会执行
 *    1）简单断言：用来对某个值进行简单的验证
 *       assertEquals：判断两个对象或两个原始类型是否相等
 *       assertNotEquals：判断两个对象或两个原始类型是否不相等
 *       assertSame：判断两个对象引用是否指向同一个对象
 *       assertNotSame：判断两个对象引用是否指向不同的对象
 *       assertTrue：判断给定的布尔值是否为 true
 *       assertFalse：判断给定的布尔值是否为 false
 *       assertNull：判断给定的对象引用是否为 null
 *       assertNotNull：判断给定的对象引用是否不为 null
 *    2）数组断言
 *    3）组合断言（所有断言都需成功）
 *    4）异常断言
 *    5）超时断言
 *    6）快速失败
 * 4、前置条件
 * 类似于断言，但不满足的断言会使测试方法失败，而不满足的前置条件只会使测试方法终止执行
 * 5、嵌套测试
 * 6、参数化测试
 * 用不同的参数多次运行测试
 * @ValueSource：为参数化测试指定入参来源，支持八大基础类以及String类型，Class类型
 * @MethodSource：表示读取指定方法的返回值作为参数化测试入参（方法需返回一个流或者数组，支持自定义对象）
 *
 */

@SpringBootApplication
public class Pro09TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(Pro09TestApplication.class, args);
    }

}
