package com.fyc.word.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fyc
 * @des 集合处理工具类
 */
public class ListUtil {

    /**
     * 根据泛型转换list
     * @param list
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> castList(List<T> list, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        try {
            if (list instanceof List<?>) {
                for (Object o : (List<?>) list) {
                    result.add(clazz.cast(o));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 裁剪list
     * @param list
     * @param length
     * @param <T>
     * @return
     */
    public static <T> List<T> trimList(List<T> list,Integer length){
        if(list == null){
            return list;
        }

        if(length <=0){
            return list;
        }

        if(list.size()<=length){
             return list;
        }

        return list.subList(list.size()-length,list.size());
    }

    public static <T> List<T> trimList(List<T> list,Integer start,Integer end){
        if(list == null){
            return new ArrayList<T>();
        }

        if(end < start || start>list.size()-1){
            return list;
        }

        if(start<0){
           start = 0;
        }

        if(end > list.size()){
            end = list.size();
        }

        return list.subList(start,end);

    }

    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        System.out.println(trimList(list,3).toString());
    }
}
