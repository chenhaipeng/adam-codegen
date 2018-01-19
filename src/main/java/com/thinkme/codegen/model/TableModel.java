package com.thinkme.codegen.model;

import com.thinkme.codegen.model.config.Variable;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/14 下午7:49
 */
@Data
public class TableModel {
    /**
     * 表名
     */
    private String tableName;

    /**
     * 去掉前缀后表名
     */
    private String noPrefixTableName;


    /**
     * 表名显示，默认为去掉prefix,
     * CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "table_name")
     * tk_user  => User
     */
    private String tableDisplayName;

    /**
     * 表注释
     */
    private String tableComment;
    /**
     * 外键
     */
    private String foreignKey = "";


    private List<Variable> variables = new ArrayList<>();

    /**
     * 列数据
     */
    private List<ColumnModel> columnList = new ArrayList();

    /**
     * 关联子表数据
     */
    private List<TableModel> subTableList = new ArrayList();


    private Map param = new HashMap();

    /**
     * 是否子表
     */
    private Boolean isSub;

}
