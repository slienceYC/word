package com.fyc.word;

import com.fyc.word.service.analysis.WordAnalysisService;
import com.fyc.word.service.file.FileParsingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WordApplicationTests {

    @Autowired
    private WordAnalysisService wordAnalysisService;

    @Autowired
    private FileParsingService fileParsingService;

    @Test
    void contextLoads() {
//        String str = fileParsingService.readPdf("a84f45b0dbe98df8740b0a.pdf");
//        wordAnalysisService.wordFrequency(str,null,10);
    }

}
