package com.online.examination.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by HP on 2019/5/13.
 */
@RestController
@RequestMapping("/v1")
public class UploadController {
    @PostMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        return "success";
    }
}
