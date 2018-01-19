package com.thinkme.codegen.model;

import lombok.Data;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/14 下午7:50
 */
@Data
public class ColumnModel {
    /**
     * 列名
     */
    private String columnName ;


    private String columnDisplayName;


    /**
     * 列注释
     */
    private String columnComment ;

    /**
     * eg:varchar(100)
     */
    private String columnType;

    /**
     * eg:bigint , varchar
     */
    private String dataType ;

    private Boolean isPK = false;

    /**
     * 长度 1000
     */
    private Long length ;

    /**
     * 精度
     */
    private Integer precision ;

    /**
     * 小数
     */
    private Integer scale;


    private Boolean isNotNull = false;

    /**
     * database 类型对应成java类型
     */
    private String javaType;


}
