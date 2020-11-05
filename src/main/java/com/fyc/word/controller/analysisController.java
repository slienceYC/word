package com.fyc.word.controller;

import com.fyc.word.common.Result;
import com.fyc.word.service.analysis.WordAnalysisService;
import com.fyc.word.service.file.FileParsingService;
import com.fyc.word.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/word/analysis")
@Slf4j
public class analysisController {

    @Autowired
    private FileParsingService fileParsingService;

    @Autowired
    private WordAnalysisService wordAnalysisService;

    @RequestMapping(value = "/frequency",method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> wordFrequency(@RequestParam String fileName){

        try {
            String article = fileParsingService.readPdf(fileName);
            List result = wordAnalysisService.wordFrequency(article, null, 20);
            return new ResultUtil<Object>().setData(result);
        }catch (Exception e){
            log.error("分析失败"+ e.getMessage());
            return new ResultUtil<Object>().setErrorMsg("分析失败");
        }
    }
}

