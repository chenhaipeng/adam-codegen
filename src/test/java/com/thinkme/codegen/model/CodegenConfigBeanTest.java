package com.thinkme.codegen.model;

import com.thinkme.codegen.model.config.ConfigBean;
import com.thinkme.codegen.utils.JacksonUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/14 下午5:12
 */
public class CodegenConfigBeanTest {

    @Test
    public void xml2Bean() throws IOException {
        String xmlPath = "/Users/hai/IdeaProjects/proj_think_me/Adam/adam-codegen/src/main/resources/codegenconfig.xml";
        ConfigBean codegenConfigBean = JacksonUtils.xmlToBean(xmlPath,ConfigBean.class);

        System.out.println(codegenConfigBean);
    }

}