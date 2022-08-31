package com.kaede.feature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Thymeleaf使用
 *    1）引入依赖
 *    2）SpringBoot自动配置好了thymeleaf
 * 自动配好的策略：
 *    1）所有thymeleaf的配置值都在ThymeleafAutoConfiguration中的ThymeleafProperties中
 *    2）配置好了SpringTemplateEngine
 *    3）配置好了ThymeleafViewResolver
 *    DEFAULT_PREFIX="classpath:/templates/"
 *    DEFAULT_SUFFIX=".html"
 *
 * 配置拦截器：1、编写一个拦截器实现HandlerInterceptor
 *           2、拦截器注册到容器中（实现WebMvcConfigurer的addInterceptors方法）
 *           3、指定拦截规则（如果是拦截所有，静态资源也会被拦截）
 * 拦截器原理：1、根据当前请求找到HandlerExecutionChain[可以处理请求的handler以及所有handler的拦截器]
 *           2、先顺序执行所有拦截器的preHandle方法
 *              1）若返回为true，则执行下一个拦截器的preHandle方法
 *              2）若返回为false，则直接倒序执行所有已经执行了的拦截器的afterCompletion方法（包括当前执行的）
 *           3、若任何一个拦截器返回false，则直接跳出，不执行目标方法
 *           4、若所有拦截器都返回true，则执行目标方法，再进行以下步骤
 *           5、倒序执行所有拦截器的postHandle方法
 *           6、前面的步骤有任何异常都会直接倒序触发afterCompletion方法（包括当前执行异常的）
 *           7、页面成功渲染完成以后，也会倒序触发afterCompletion方法
 *
 * 文件上传自动配置类-MultipartAutoConfiguration
 * 1、自动配置好了StandardServletMultipartResolver（文件上传解析器）
 * 2、原理步骤：
 *    1）请求进来后使用文件上传解析器判断是否为文件上传请求
 *    2）若是则进行封装并返回文件上传请求
 *    3）参数解析器解析请求中的文件内容并封装为MultipartFile
 *    4）将request中的文件信息封装为一个Map（MultiValueMap<String,MultipartFile>)
 *    5）最后使用工具类实现文件流的拷贝
 * 可配置单个文件最大上传大小和单次请求最大总上传大小
 * spring: servlet: multipart: max-file-size: 10MB
 * spring: servlet: multipart: max-request-size: 100MB
 *
 * 错误处理：
 * 1、默认规则
 *    1）默认情况下，SpringBoot提供/error处理所有错误的映射
 *    2）对于浏览器客户端，响应一个"whitelabel"错误视图，以HTML格式呈现错误、HTTP状态和异常消息的详细信息
 *      对于非浏览器客户端（如postman），将生成json响应，包含相同的数据
 * 2、定制错误处理逻辑
 *    1）自定义错误页，static或templates文件夹下的error文件夹下的404、5xx页面会被自动解析，
 *      有精准错误状态码对应的页面就匹配精确，没有就找5xx.html，若都没有，则触发白页
 *    2）@ControllerAdvice + @ExceptionHandler处理全局异常（常见）
 *    3）@ResponseStatus + 自定义异常
 *    4）自定义的异常解析器，实现HandlerExceptionResolver接口
 *
 */

@SpringBootApplication
public class Pro06WebFeatureApplication {

    public static void main(String[] args) {
        SpringApplication.run(Pro06WebFeatureApplication.class, args);
    }

}
