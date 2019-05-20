package com.online.examination.controller;

import com.online.examination.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by HP on 2019/5/13.
 */
@RestController
@RequestMapping("/v1")
public class UploadController {

    @Autowired
    private UploadService uploadService;
    @PostMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        try {
            uploadService.analysis(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
