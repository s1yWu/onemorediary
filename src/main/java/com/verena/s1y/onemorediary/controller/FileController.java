package com.verena.s1y.onemorediary.controller;

import com.verena.s1y.onemorediary.constant.Status;
import com.verena.s1y.onemorediary.pojo.base.ApiResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@Api(value = "图片上传")
@Slf4j
@RequestMapping(value = "/one-more-dairy/diary/")
public class FileController {

    public static final String filePath = "d:/testimage/";

    @GetMapping("upload")
    public String upload() {
        return "upload";
    }


    @PostMapping(value = "diary")
    public ApiResponse add(){
        return ApiResponse.ofSuccess(null);
    }

    @PostMapping(value = "image")
    public ApiResponse uploadPic(@RequestParam("file")MultipartFile diaryImg){
        String httpImageUrl = "";
        try {
            String imgType = diaryImg.getContentType();
            log.info("-----图片类型-----" + imgType);
            String iType = "image";
            if (!imgType.startsWith(iType)){
                return ApiResponse.ofFail(Status.IMAGE_FILE_ERROR,null);
            }
            String fileName = diaryImg.getOriginalFilename();
            log.info("--------图片名称--------"+ fileName);
            File dest = new File(filePath + fileName);

            diaryImg.transferTo(dest);
            log.info("上传成功");
            return ApiResponse.ofSuccess(null);
        }catch ( IOException e){
            return ApiResponse.ofFail(Status.IMAGE_FILE_ERROR,null);
        }
    }
}
