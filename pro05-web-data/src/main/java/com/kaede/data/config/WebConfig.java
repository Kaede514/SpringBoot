package com.kaede.data.config;

import com.kaede.data.bean.Pet;
import com.kaede.data.converter.KaedeMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kaede
 * @create 2022-08-12 13:04
 */

@Configuration(proxyBeanMethods = false)
public class WebConfig {

    /*@Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        HiddenHttpMethodFilter methodFilter = new HiddenHttpMethodFilter();
        //自定义_method的名字
        methodFilter.setMethodParam("_m");
        return methodFilter;
    }*/

    //WebMvcConfigurer定制化SpringMVC功能
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            //自定义Converter类型转换
            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter(new Converter<String, Pet>(){
                    @Override
                    public Pet convert(String source) {
                        if (!StringUtils.isEmpty(source)) {
                            Pet pet = new Pet();
                            String[] split = source.split(",");
                            pet.setName(split[0]);
                            pet.setAge(Integer.parseInt(split[1]));
                            return pet;
                        }
                        return null;
                    }
                });
            }

            //扩展自定义MessageConverter（不会覆盖默认的MessageConverter）（通过请求头中Accept属性）
            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new KaedeMessageConverter());
            }

            //自定义内容协商策略（通过format决定策略）
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                Map<String, MediaType> mediaTypeMap = new HashMap<>();
                //指定支持解析哪些参数对应的哪些媒体类型
                mediaTypeMap.put("json",MediaType.APPLICATION_JSON);
                mediaTypeMap.put("xml", MediaType.APPLICATION_XML);
                mediaTypeMap.put("y-kaede", MediaType.parseMediaType("application/x-kaede"));
                ParameterContentNegotiationStrategy parameterStrategy = new ParameterContentNegotiationStrategy(mediaTypeMap);
                //可以修改内容协商策略所需的参数的名字（默认为format）
                //parameterStrategy.setParameterName("formatStrategy");
                //自定义内容协商策略后，默认的请求头Accept方式会失效，若想要基于请求头Accept的
                //也生效，则还需将HeaderContentNegotiationStrategy放入其中
                HeaderContentNegotiationStrategy strategy = new HeaderContentNegotiationStrategy();
                configurer.strategies(Arrays.asList(parameterStrategy,strategy));
            }
        };
    }

}
