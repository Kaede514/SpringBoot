package com.kaede.data.controller;

import com.kaede.data.bean.Person;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kaede
 * @create 2022-08-12 14:05
 */

@RestController
public class ParamController {

    //可以单独提取值，也可以把所有的k-v都提取到类型为Map<String,String>的集合中
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String,Object> getCar(@PathVariable("id") Integer id, @PathVariable("username") String name,
                                     @PathVariable Map<String,String> pv,
                                     @RequestHeader("User-Agent") String userAgent,
                                     @RequestHeader Map<String,String> headers,
                                     Integer age, @RequestParam("hobby") String[] hobbies,
                                     @RequestParam Map<String,String> params) {
        Map<String,Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("pv", pv);
        map.put("userAgent", userAgent);
        map.put("headers", headers);
        map.put("age", age);
        map.put("hobbies", hobbies);
        map.put("params", params);
        return map;
    }

    @PostMapping("/save")
    public Map<String,Object> postMethod(@RequestBody String content) {
        Map<String,Object> map = new HashMap<>();
        map.put("content", content);
        return map;
    }

    //数据绑定，页面提交的数据和对象属性进行绑定
    @PostMapping("/saveuser")
    public Person saveUser(Person person) {
        return person;
    }

}
