package com.example.star.utils.json;

import com.google.gson.Gson;

/**
 * Created by：Cral-Gates on 16/8/20 14:53
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/8/20
 * Description:
 */
public class GsonUtils {
    /**
     * 对象转换成json字符串
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }


    /**
     * json字符串转成对象
     * @param str
     * @param type
     * @return
     */
    public static <T> T fromJson(String str, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }
}
