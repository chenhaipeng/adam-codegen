package com.thinkme.codegen.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 将数据库类型转为javaType
 *
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/15 下午3:36
 */
public class ParseSqlUtil {

    private static Map<String, String> parseMap = new HashMap<>();

    static {
        parseMap.put("varchar", "String");
        parseMap.put("char", "String");
        parseMap.put("blob", "byte[]");
        parseMap.put("text", "String");
        parseMap.put("integer","Long");
        parseMap.put("tinyint","Integer");
        parseMap.put("smallint","Integer");
        parseMap.put("mediumint","Integer");
        parseMap.put("bit","Boolean");
        parseMap.put("bigint","Long");
        parseMap.put("float","Float");
        parseMap.put("double","Double");
        parseMap.put("decimal","java.math.BigDecimal");
        parseMap.put("date","java.util.Date");
        parseMap.put("time","java.util.Date");
        parseMap.put("datetime","java.util.Date");
        parseMap.put("timestamp","java.util.Date");
    }

    public static String getJavaType(String sqlType){
        return parseMap.getOrDefault(sqlType,"String");

    }
}
