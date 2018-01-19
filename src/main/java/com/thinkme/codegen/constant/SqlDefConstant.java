package com.thinkme.codegen.constant;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/14 下午10:36
 */
public interface SqlDefConstant {

    interface Mysql{
        //获取列信息
        String COLUMNS_INFO = "select * "+
                " from information_schema.columns " +
                " where table_schema=DATABASE() and table_name=?";

        //获取表信息
        String TABLE_COMMENT = "select table_name, table_comment " +
                " from information_schema.tables t " +
                " where t.table_schema=DATABASE() and table_name=?";

        //获取所有数据信息
        String ALL_TABLE_COMMENT = "select table_name, table_comment " +
                " from information_schema.tables t " +
                " where t.table_schema=DATABASE()";


    }
}
