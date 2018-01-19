package com.thinkme.codegen.service;

import com.thinkme.codegen.model.config.ConfigBean;
import com.thinkme.codegen.utils.JacksonUtils;
import org.junit.Test;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/18 下午7:20
 */
public class GenServiceTest {

    /**
     * 单表测试
     * @throws Exception
     */

    @Test
    public void genTableByConfig() throws Exception {
        String xmlPath = "/Users/hai/IdeaProjects/proj_think_me/Adam/adam-codegen/src/main/resources/codegenconfig.xml";
        ConfigBean codegenConfigBean = JacksonUtils.xmlToBean(xmlPath,ConfigBean.class);

        GenService genService = new GenService();
        genService.genTableByConfig(codegenConfigBean,"gen");

    }

}