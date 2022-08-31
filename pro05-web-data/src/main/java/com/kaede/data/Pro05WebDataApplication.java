package com.kaede.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 请求参数处理
 * 1、请求映射
 * @xxxMapping；
 * Rest风格支持（使用HTTP请求方式动词来表示对资源的操作）
 * 以前：/getUser 获取用户    /deleteUser 删除用户    /editUser 修改用户    /saveUser 保存用户
 * 现在：/user    GET-获取用户    DELETE-删除用户    PUT-修改用户     POST-保存用户
 * 核心Filter；HiddenHttpMethodFilter（若未配置spring.mvc.hiddenmethod.filter:enalbed为true，
 *            则SpringBoot默认不开启该功能）
 *   用法：表单method=post，隐藏域 _method=put
 *   在SpringBoot配置文件中手动开启
 *
 * Rest原理（表单提交要使用Rest的时候）：
 *     1）表单提交会带上 _method=XX
 *     2）请求过来被HiddenHttpMethodFilter拦截
 *        若请求正常并且是POST请求会获取_method的值并转为大写（兼容PUT、DELETE、PATCH），
 *        然后通过包装类重写getMethod()方法，返回传入的_method大写后的值
 * Rest使用客户端工具时：如Postman直接发送PUT、DELETE等发送请求时，无需filter进行请求方式的转换
 *                     故当前后端分离等服务器可以直接接收PUT、DELETE请求的情况下，可以不配置
 *                     spring.mvc.hiddenmethod.filter:enalbed为true
 * 扩展：如何自定义_method的名字：在配置类中注入类型为HiddenHttpMethodFilter的组件，在其中配置
 *
 * 请求映射原理：
 * 接口 HttpServlet   doGet、doPost
 *   抽象类 HttpServletBean
 *     抽象类 FrameworkServlet    doGet、dePost -> processRequest -> doService
 *       实现类 DispatcherServlet    doService -> doDispatch（每个请求都会调用）
 * doDispatch中调用getHandler()方法，找到当前请求使用哪个Handler（controller的方法）处理，
 * 在方法中匹配处理器映射，所有的请求映射都在HandlerMapping中，请求进来后，挨个匹配所有的HandlerMapping，
 * 若能匹配上则返回该Handler，若匹配不上就继续匹配下一个Handler
 * RequestMappingHandlerMapping：保存了所有@RequestMapping和Handler的映射规则
 * SpringBoot自动配置了欢迎页的WelcomPageHandlerMapping，访问/能访问到index.html
 *
 * 2、普通参数与基本注解
 *    1）@PathVariable、@RequestParam、@RequestHeader、@RequestBody、@CookieValue、@MatrixVariable
 *    2）ServletAPI
 *    3）Model、Map、ModelMap、ModelAndView
 *    4）自定义对象参数（可以自动类型转换和格式化，可以级联封装）
 *       WebDataBinder（web数据绑定器）将请求参数的值绑定到指定的JavaBean中，并使用
 *       Converters将请求数据转成指定的数据类型，再次封装到JavaBean中
 *       自定义Converter：在容器中放入WebMvcConfigurer并实现对应方法
 *
 * 3、参数处理原理
 *    1）HandlerMapping中找到能处理请求的Handler（即能处理请求的controller中的某个方法）
 *    2）为当前Handler找一个HandlerAdapter（适配器）
 *       其中RequestMappingHandlerAdaper支持方法上标注@RequestMapping的适配器
 *          HandlerFunctionAdapter支持函数式编程的方法
 *    3）执行目标方法
 *       mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
 *       调用然后进入到RequestMappingHandlerAdaper类中执行handleInternal方法
 *       mav = this.invokeHandlerMethod(request, response, handlerMethod);
 *    4）然后执行invocableMethod.invokeAndHandle(webRequest, mavContainer, new Object[0]);
 *       invocableMethod就是目标方法，里面的东西都封装完成了
 *    5）最后进入ServletInvocableHandlerMethod中真正执行目标方法
 *       Object returnValue = this.invokeForRequest(webRequest, mavContainer, providedArgs);
 *    6）执行过程中用到参数解析器-HandlerMethodArgumentResolver确定目标方法每一个参数的值
 *       挨个判断所有参数解析器哪个支持解析这个参数，匹配后解析该参数的值，利用Converters将
 *       请求数据转成指定的参数数据类型，然后使用反射执行方法
 *    7）执行完目标方法后将所有的数据都放在ModelAndViewContainer，
 *       包含要去的页面地址View，还包含Model数据
 *    8）处理派发结果
 *
 *
 * 数据响应与内容协商
 * 1、响应Json
 *    1）jackson.jar（web场景自动引入了json依赖） + @ResponseBody，给前端自动返回Json数据
 *       返回值处理器原理：1）挨个判断是否支持该类型的返回值
 *                      2）若支持，则调用handleReturnValue进行处理
 *       RequestResponseBodyMethodProcessor可以处理返回值标了@ResponseBody注解的返回值
 *          1）利用MessageConverters进行处理，将数据写为json
 *             -内容协商：浏览器在发请求时会在请求头的Accept中告诉服务可以接收的内容类型
 *             -服务器最终根据自身的能力决定服务器能生产出什么样的内容类型的数据
 *             -SpringMVC会挨个遍历所有容器底层的MessageConverter，找出可以处理的
 *    2）SpringMVC所支持的返回值：ModelAndView、Model、View、ResponseEntity、map、
 *      有@ModelAttribute且为对象类型的、有@ResponseBody注解...
 *    3）HTTPMessageConverter原理：将此Class类型的对象转为MediaType类型的数据，
 *                                如将Person对象转为json（响应）或者json转为Person（请求）
 *
 * 2、内容协商
 * 根据客户端接收能力不同，返回不同媒体类型的数据
 *    1）引入支持xml的依赖
 *    2）postman分别测试返回json和xml
 *      只需改变请求头中Accept字段（Http协议中规定的，告诉服务器本客户端可以接收的数据类型）
 *    3）开启基于浏览器参数方式的内容协商功能：
 *       spring: mvc: contentnegotiation: favor-parameter: true
 *       开启后可通过http://localhost:8080/test/person?format=xml访问xml内容数据
 *                 http://localhost:8080/test/person?format=json访问json内容数据
 *    4）内容协商原理：1）判断当前响应头中是否已经有确定的媒体类型（MediaType）
 *                   2）获取客户端支持接收的内容类型（请求头中的Accept字段）
 *                   3）遍历循环当前系统所有的MessageConverter，找到支持的MessageConverter
 *                   4）统计出MessageConverter支持的媒体类型
 *                   5）进行内容协商的最佳匹配
 *                   6）调用将对象转为最佳匹配媒体类型的MessageConverter进行转换
 *    （若开启基于浏览器参数方式的内容协商功能，则以Parameter策略优先，获取请求头中的format的值来匹配）
 *    5）自定义MessageConverter：在容器中放入WebMvcConfigurer并实现对应方法
 *    6）自定义内容协商策略：在容器中放入WebMvcConfigurer并实现对应方法
 *
 */

@SpringBootApplication
public class Pro05WebDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(Pro05WebDataApplication.class, args);
    }

}
