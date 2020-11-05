package com.fyc.word.service.file.impl;

import com.fyc.word.service.file.FileParsingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import static com.fyc.word.common.Constant.LOCAL_DATA_FILE_PATH;

@Slf4j
@Service("")
public class FileParsingServiceImpl implements FileParsingService {


    @Override
    public String readPdf(String fileName) {
        File file = new File(LOCAL_DATA_FILE_PATH + "/" + fileName);
        FileInputStream in = null;
        String result = null;
        try {
            in = new FileInputStream(file);
            // 新建一个PDF解析器对象
            PDFParser parser = new PDFParser(new RandomAccessFile(file,"rw"));
            // 对PDF文件进行解析
            parser.parse();
            // 获取解析后得到的PDF文档对象
            PDDocument pdfdocument = parser.getPDDocument();
            // 新建一个PDF文本剥离器
            PDFTextStripper stripper = new PDFTextStripper();
            stripper .setSortByPosition(true); //sort设置为true 则按照行进行读取，默认是false
            // 从PDF文档对象中剥离文本
            result = stripper.getText(pdfdocument);

        } catch (Exception e) {
            log.error("读取pdf失败"+ e.getMessage());
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
        return result;
    }

    @Override
    public String readText(String fileName) {
        return null;
    }

    @Override
    public String readDoc(String fileName) {
        return null;
    }

    private FileInputStream getFileInputStream(String fileName) {
        FileInputStream fileInputStream = null;
        try {
            File file = new File(LOCAL_DATA_FILE_PATH + "/" + fileName);
            fileInputStream = new FileInputStream(file);

        }catch (Exception e){
            return null;
        }
        return fileInputStream;
    }
}
