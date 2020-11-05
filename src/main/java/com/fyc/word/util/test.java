package com.fyc.word.util;

import org.ansj.splitWord.analysis.ToAnalysis;

import java.io.*;
import java.util.*;

public class test {

    public static void main(String[] args) throws IOException {
//        String str = "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!" ;
//        System.out.println(ToAnalysis.parse(str));
        wordFrequency();
    }

    public static void wordFrequency() throws IOException {
        Map<String, Integer> map = new HashMap<>();

        String article = getString();
        String result = ToAnalysis.parse(article).toStringWithOutNature();
        String[] words = result.split(",");


        for(String word: words){
            String str = word.trim();
            // 过滤空白字符
            if (str.equals(""))
                continue;
                // 过滤一些高频率的符号
            else if(str.matches("[）|（|.|，|。|+|-|“|”|：|？|\\s]"))
                continue;
                // 此处过滤长度为1的str
            else if (str.length() < 2)
                continue;

            if (!map.containsKey(word)){
                map.put(word, 1);
            } else {
                int n = map.get(word);
                map.put(word, ++n);
            }
        }

        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>();
        Map.Entry<String, Integer> entry;
        while ((entry = getMax(map)) != null){
            list.add(entry);
        }

        System.out.println(Arrays.toString(list.toArray()));

    }


    /**
     * 找出map中value最大的entry, 返回此entry, 并在map删除此entry
     * @param map
     * @return
     */
    public static Map.Entry<String, Integer> getMax(Map<String, Integer> map){
        if (map.size() == 0){
            return null;
        }
        Map.Entry<String, Integer> maxEntry = null;
        boolean flag = false;
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> entry = iterator.next();
            if (!flag){
                maxEntry = entry;
                flag = true;
            }
            if (entry.getValue() > maxEntry.getValue()){
                maxEntry = entry;
            }
        }
        map.remove(maxEntry.getKey());
        return maxEntry;
    }

    /**
     * 从文件中读取待分割的文章素材.
     　　　* 文件内容来自简书热门文章: https://www.jianshu.com/p/5b37403f6ba6
     * @return
     * @throws IOException
     */
    public static String getString() throws IOException {
        FileInputStream inputStream = new FileInputStream(new File("/Users/fyc/Desktop/test.pdf"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
        StringBuilder strBuilder = new StringBuilder();

        String line;
        while((line = reader.readLine()) != null){
            strBuilder.append(line);
        }
        reader.close();
        inputStream.close();
        return strBuilder.toString();
    }

}
