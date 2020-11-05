package com.fyc.word.service.file;

public interface FileParsingService {

    String readPdf(String fileName);

    String readText(String fileName);

    String readDoc(String fileName);


}
