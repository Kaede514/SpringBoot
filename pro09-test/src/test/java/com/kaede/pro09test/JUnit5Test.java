package com.kaede.pro09test;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author kaede
 * @create 2022-08-30 11:38
 */

@SpringBootTest
@DisplayName("测试Junit5功能注解")
public class JUnit5Test {

    @Test
    @DisplayName("测试@DisplayName")
    public void testDisplayName() {
        System.out.println("testDisplayName");
    }

    @BeforeEach
    public void testBeforeEach() {
        System.out.println("\n=======测试开始=======");
    }

    @AfterEach
    public void testAfterEach() {
        System.out.println("=======测试结束=======\n");
    }

    @BeforeAll
    public static void testBeforAll() {
        System.out.println("=======所有测试开始=======");
    }

    @AfterAll
    public static void testAfterAll() {
        System.out.println("=======所有测试结束=======");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Disabled
    @Test
    public void test2() {
        System.out.println("test2");
    }

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    public void testTimeout() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("testTimeout");
    }

    @RepeatedTest(3)
    @Test
    public void test3() {
        System.out.println("test3");
    }

    @Autowired
    private DataSource dataSource;

    @Test
    public void testSpringBootTest() {
        System.out.println("dataSource = " + dataSource.getClass());
    }

    public int cal(int x, int y) {
        return x + y;
    }

    @DisplayName("测试简单断言")
    @Test
    public void testSimpleAssertion() {
        int cal = cal(2, 3);
        //Assertions.assertEquals(cal, 4);
        //自定义断言失败的错误提示
        //Assertions.assertEquals(cal, 4, "业务逻辑计算错误");
        String s1 = new String("msg");
        String s2 = "msg";
        Assertions.assertSame(s1, s2, "引用指向不同对象");
    }

    @DisplayName("测试数组断言")
    @Test
    public void testArrayAssertion() {
        Assertions.assertArrayEquals(new int[]{1,2}, new int[]{2,1});
    }

    @DisplayName("测试组合断言")
    @Test
    public void testAllAssertion() {
        Assertions.assertAll("test",
                () -> Assertions.assertTrue(true && true, "不为true"),
                () -> Assertions.assertEquals(1, 22-1, "不相等")
        );
    }

    @DisplayName("测试异常断言")
    @Test
    public void testExceptionAssertion() {
        Assertions.assertThrows(ArithmeticException.class, () -> {int i = 10/0;}, "业务逻辑正常运行");
    }

    @DisplayName("测试超时断言")
    @Test
    public void testTimeoutAssertion() {
        Assertions.assertTimeout(Duration.ofMillis(1000),() -> Thread.sleep(1000));
    }

    @DisplayName("测试快速失败")
    @Test
    public void testQuicklyFail() {
        Assertions.fail("测试失败");
    }

    @DisplayName("测试前置条件")
    @Test
    public void testAssumption() {
        Assumptions.assumeTrue(false, "结果为false");
        System.out.println("pass");
    }

    @DisplayName("参数化测试")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    public void testParameterized(int i) {
        Assertions.assertNotEquals(i, 3);
        System.out.println(i);
    }

    public static String[] getParams() {
        return new String[]{"apple","pair","peach"};
    }

    @DisplayName("参数化测试2")
    @ParameterizedTest
    @MethodSource("getParams")
    public void testParameterized2(Integer i) {
        System.out.println(i);
    }

    public static Stream<String> getHobbies() {
        return Stream.of("piano", "flute", "violin");
    }

    @DisplayName("参数化测试3")
    @ParameterizedTest
    @MethodSource("getHobbies")
    public void testParameterized3(String i) {
        System.out.println(i);
    }

    @Data
    @AllArgsConstructor
    static class User {
        private String name;
        private Integer age;
    }

    public static Stream<User> getUsers() {
        return Stream.of(new User("kaede", 18),new User("luna", 16));
    }

    @DisplayName("参数化测试4")
    @ParameterizedTest
    @MethodSource("getUsers")
    public void testParameterized4(User user) {
        System.out.println(user);
    }

}
