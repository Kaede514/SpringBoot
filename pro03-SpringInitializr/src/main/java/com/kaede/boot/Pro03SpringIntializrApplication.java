package com.kaede.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot除了以application.properties作为配置文件外，还兼容yaml格式
 * yaml适合用来做以数据为中心的配置文件，比xml更简洁、占用空间更少（推荐使用）
 * 基本语法：key: value 之间有空格
 * 			大小写敏感
 * 			使用缩进表示层级关系
 * 			缩进的空格数不重要，只要相同层级的元素左对齐即可
 * 		    #表示注释
 * 		    字符串一般无需加引号，""不会转义，''会转义（改变原本的行为）
 * 数据类型：
 * 字面量：k: v
 * 对象：键值对的集合
 * 		1）行内写法：k: {k1: v1, k2: v2}
 * 		2）k:
 * 		     k1: v1
 * 		     k2: v2
 * 数组：1）行内写法：k: [k1, k2]
 * 		2）k:
 * 		     - k1
 * 		     - k2
 *
 */

@SpringBootApplication
public class Pro03SpringIntializrApplication {

	public static void main(String[] args) {
		SpringApplication.run(Pro03SpringIntializrApplication.class, args);
	}

}
