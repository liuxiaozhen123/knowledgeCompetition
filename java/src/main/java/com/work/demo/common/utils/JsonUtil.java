package com.work.demo.common.utils;

import com.google.gson.Gson;

/**
 * @author 刘小真
 */
public class JsonUtil {

    /**
     * 单例Gson对象
     */
    private static Gson gson = null;

    public static Gson getGson() {
        if (gson == null) {
            synchronized (JsonUtil.class) {
                gson = new Gson();
            }
        }
        return gson;
    }
}
