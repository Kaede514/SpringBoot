package com.kaede.feature.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author kaede
 * @create 2022-08-23 15:27
 *
 * 文件上传测试
 */

@Controller
@Slf4j
public class FormTestController {

    @GetMapping("/form")
    public String formLayouts() {
        return "form/form";
    }

    @PostMapping("/upload")
    public String upload(String email, String username,
                         @RequestParam("singleUpFile") MultipartFile singleUpFile,
                         //自动封装上传过来的文件
                         @RequestParam("multiUpFile") MultipartFile[] multiUpFile){
        log.info("上传的信息：email={},username={},singleUpFile={},photos={}",
                email,username,singleUpFile.getSize(),multiUpFile.length);
        //保存文件
        return "admin_main";
    }


}
