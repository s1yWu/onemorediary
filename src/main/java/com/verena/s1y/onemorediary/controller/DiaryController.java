package com.verena.s1y.onemorediary.controller;

import com.verena.s1y.onemorediary.constant.Status;
import com.verena.s1y.onemorediary.pojo.Diary;
import com.verena.s1y.onemorediary.pojo.base.ApiResponse;
import com.verena.s1y.onemorediary.server.DiaryServer;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@Api(value = "图片上传")
@Slf4j
@RequestMapping(value = "/one-more-dairy/diary/")
public class DiaryController {

//    public static final String filePath = "d:/testimage/";

    public static final String filePath = "/usr/local/nginx/html/diary/";
    private final DiaryServer diaryServer;
    @Autowired
    public DiaryController(DiaryServer diaryServer) {
        this.diaryServer = diaryServer;
    }




    @GetMapping("upload")
    public String upload() {
        return "upload";
    }


    @GetMapping(value = "diary")
    public ApiResponse select(int id_user){
        List<Diary> list = diaryServer.selectDiaryByIdUser(id_user);
        Collections.reverse(list);
        return ApiResponse.ofSuccess(list);
    }

    @PostMapping(value = "diary")
    public ApiResponse add(Diary diary){
        diaryServer.insertDiary(diary);
        return ApiResponse.ofSuccess(diary);
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
