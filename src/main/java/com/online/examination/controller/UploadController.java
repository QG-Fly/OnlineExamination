package com.online.examination.controller;

import com.google.common.base.Preconditions;
import com.online.examination.model.Question;
import com.online.examination.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

  @GetMapping(value = "/question")
  public List<Question> randomSample(
      @RequestParam(name = "type", required = true) String type,
      @RequestParam(name = "count", required = true) int count) {
      Preconditions.checkArgument(Question.Type.fromValue(type) !=null ,"param type invalid");
    return uploadService.randomSample(Question.Type.fromValue(type), count);
  }
}
