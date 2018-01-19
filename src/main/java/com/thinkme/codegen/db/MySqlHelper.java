package com.thinkme.codegen.db;

import com.google.common.base.CaseFormat;
import com.thinkme.codegen.constant.SqlDefConstant;
import com.thinkme.codegen.model.ColumnModel;
import com.thinkme.codegen.model.TableModel;
import com.thinkme.codegen.utils.GenFileUtil;
import com.thinkme.codegen.utils.ParseSqlUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/14 下午7:47
 */
public class MySqlHelper implements IDbHelper{

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

    @Override
    public TableModel getTableInfo(String tableName,String prefix) {
        TableModel tableModel = jdbcTemplate.queryForObject(SqlDefConstant.Mysql.TABLE_COMMENT,
                new RowMapper<TableModel>() {
                    @Override
                    public TableModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                        TableModel tableModel = new TableModel();
                        String tableName = rs.getString("table_name");
                        tableName = tableName.toLowerCase();
                        tableModel.setTableName(tableName);
                        String noPrefixTableName = GenFileUtil.trimTableNamePrefix(tableName,prefix);
                        tableModel.setNoPrefixTableName(noPrefixTableName);
                        tableModel.setTableComment(rs.getString("table_comment"));

                        String displayName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, noPrefixTableName);
                        tableModel.setTableDisplayName(displayName);
                        return tableModel;
                    }
                }, tableName);

        List<ColumnModel> columnModelList = this.getByColumnInfo(tableName);
        tableModel.setColumnList(columnModelList);

        return tableModel;
    }

    public List<ColumnModel> getByColumnInfo(String tableName) {
        List<ColumnModel> list = jdbcTemplate.query(SqlDefConstant.Mysql.COLUMNS_INFO, new RowMapper<ColumnModel>() {
                    @Override
                    public ColumnModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                        ColumnModel columnModel = new ColumnModel();
                        String columnName = rs.getString("column_name");
                        columnModel.setColumnName(columnName);
                        columnModel.setColumnComment(rs.getString("column_comment"));
                        columnModel.setColumnType(rs.getString("column_type"));
                        String dataType = rs.getString("data_type");
                        columnModel.setDataType(dataType);
                        String columnKey = rs.getString("column_key");
                        columnModel.setIsPK(columnKey.equals("PRI"));
                        columnModel.setLength(rs.getLong("character_octet_length"));

                        String isNullAble = rs.getString("is_nullable");
                        columnModel.setPrecision(rs.getInt("numeric_precision"));
                        columnModel.setScale(rs.getInt("numeric_scale"));
                        columnModel.setIsNotNull(isNullAble.equals("NO"));
                        columnModel.setJavaType(ParseSqlUtil.getJavaType(dataType));
                        columnModel.setColumnDisplayName(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,columnName));

                        return columnModel;
                    }
                },
                tableName);
        return list;

    }

    @Override
    public List<String> getAllTable() {
        return null;
    }
}
