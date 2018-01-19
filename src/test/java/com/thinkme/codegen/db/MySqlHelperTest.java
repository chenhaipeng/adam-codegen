package com.thinkme.codegen.db;

import com.thinkme.codegen.model.ColumnModel;
import org.junit.Test;

import java.util.List;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/14 下午10:19
 */
public class MySqlHelperTest {
    @Test
    public void getByColumnInfo() throws Exception {
        List<ColumnModel> list = new MySqlHelper().getByColumnInfo("sys_user");
        System.out.println(list);

    }

    @Test
    public void getByTableInfo() throws Exception {
//        TableModel tableModel = new MySqlHelper().getByTableByName("sys_user");


    }

}