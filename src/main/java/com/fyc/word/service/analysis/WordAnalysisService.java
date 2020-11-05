package com.fyc.word.service.analysis;

import java.util.List;

public interface WordAnalysisService {

    List wordFrequency(String article,String[] words,Integer rank);


}
