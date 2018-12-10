package cn.jy.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class JsonUtil {

    public static String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }

    public static <T> T toObject(String json, Class<T> cla) {
        return JSON.parseObject(json, cla);
    }

    public static <T> List<T> toList(String json, Class<T> t) {
        return JSON.parseArray(json, t);
    }
}
