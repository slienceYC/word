package com.fyc.word.service.analysis.impl;

import com.fyc.word.entity.WordInfo;
import com.fyc.word.service.analysis.WordAnalysisService;
import com.fyc.word.util.ListUtil;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("WordAnalysisService")
public class WordAnalysisServiceImpl implements WordAnalysisService {

    @Override
    public List wordFrequency(String article, String[] words, Integer rank) {

        Map<String, Integer> map = new HashMap<>();
        System.out.println("开始分析----"+article.length());

        if (words == null) {
            String result = ToAnalysis.parse(article).toStringWithOutNature();
            words = result.split(",");
        }


        for (String word : words) {
            String str = word.trim();
            String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？a-zA-Z0-9]";
            // 过滤空白字符
            if (str.equals(""))
                continue;
                // 过滤一些高频率的符号
            else if (str.matches(regEx))
                continue;
            // 此处过滤长度为1的str
            else if (str.length() < 2)
                continue;

            if (!map.containsKey(word)) {
                map.put(word, 1);
            } else {
                int n = map.get(word);
                map.put(word, ++n);
            }
        }

        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
        }
        List<WordInfo> list = new ArrayList<>();
        Map.Entry<String, Integer> entry;
        while ((entry = getMax(map)) != null) {
            WordInfo wordInfo = new WordInfo();
            wordInfo.setKeyWord(entry.getKey());
            wordInfo.setFrequency(entry.getValue());
            list.add(wordInfo);
        }


        if (rank != null || rank != 0) {
            list = ListUtil.trimList(list, 0, rank - 1);
        }
        System.out.println(Arrays.toString(list.toArray()));
        return list;
    }

    public static Map.Entry<String, Integer> getMax(Map<String, Integer> map) {
        if (map.size() == 0) {
            return null;
        }
        Map.Entry<String, Integer> maxEntry = null;
        boolean flag = false;
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (!flag) {
                maxEntry = entry;
                flag = true;
            }
            if (entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        map.remove(maxEntry.getKey());
        return maxEntry;
    }
}
