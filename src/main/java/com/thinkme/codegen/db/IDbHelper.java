package com.thinkme.codegen.db;

import com.thinkme.codegen.model.TableModel;

import java.util.List;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/14 下午7:48
 */
public interface IDbHelper {

    TableModel getTableInfo(String tableName,String prefix);

    List<String> getAllTable();
}
