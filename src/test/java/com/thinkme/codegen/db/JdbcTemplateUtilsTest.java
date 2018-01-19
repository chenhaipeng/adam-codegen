package com.thinkme.codegen.db;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/14 下午7:11
 */
public class JdbcTemplateUtilsTest {

    @Test
    public void loadProperties() throws IOException {
        Properties properties =  PropertiesLoaderUtils.loadProperties(new ClassPathResource("codegen.properties"));
        System.out.println(properties.get("url"));
    }

}