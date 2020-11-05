package com.fyc.word.controller;

import com.fyc.word.common.Constant;
import com.fyc.word.common.Result;
import com.fyc.word.util.ResultUtil;
import com.fyc.word.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/word/file")
@Slf4j
public class FileController {

    @PostMapping("/upload")
    @ResponseBody
    public Result<Object> upload(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return new ResultUtil<Object>().setErrorMsg("请选择要上传的文件");
        }
        Map data = new HashMap<>();
        try {
            String fileOriginalFilename = multipartFile.getOriginalFilename();
            String suffix = fileOriginalFilename.substring(fileOriginalFilename.lastIndexOf("."), fileOriginalFilename.length());
            System.out.println(suffix);
            String prefix = UUIDUtil.generate();
            String fileName = prefix + suffix;
            File file = new File(Constant.LOCAL_DATA_FILE_PATH + "/" + fileName);
            multipartFile.transferTo(file);
            data.put("suffix",suffix);
            data.put("fileName",fileName);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("文件上传失败：" + e.getMessage());
            return new ResultUtil<Object>().setErrorMsg("上传失败");
        }
        return new ResultUtil<Object>().setData(data);
    }
}
