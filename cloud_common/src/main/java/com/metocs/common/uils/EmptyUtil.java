package com.metocs.common.uils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Collection;

public class EmptyUtil {

    public static boolean is_empty(String param){
        return param == null || "".equals(param);
    }

    public static boolean not_empty(String param){
        return param != null && !"".equals(param);
    }

    public static boolean is_empty(Collection collection){
        if (collection == null){
            return true;
        }
        return collection.isEmpty();
    }
    public static boolean not_empty(Collection collection){
        if (collection != null){
            return !collection.isEmpty();
        }
        return false;
    }
    public static boolean is_empty(Number number){
        return number == null;
    }
    public static boolean not_empty(Number number){
        return number != null;
    }
}
