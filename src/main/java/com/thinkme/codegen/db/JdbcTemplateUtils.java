package com.thinkme.codegen.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.Properties;

public class JdbcTemplateUtils {

    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate jdbcTemplate() {
        if(jdbcTemplate == null) {
            jdbcTemplate = createJdbcTemplate();
        }
        return jdbcTemplate;
    }

    private static JdbcTemplate createJdbcTemplate() {

        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("codegen.properties"));
        } catch (IOException e) {
            throw new RuntimeException("加载属性文件codegen.properties出错");
        }

        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(properties.getProperty("driver-class-name"));
        ds.setUrl(properties.getProperty("url"));
        ds.setUsername(properties.getProperty("username"));
        ds.setPassword(properties.getProperty("password"));

        return new JdbcTemplate(ds);
    }


}
