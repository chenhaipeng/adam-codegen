package com.thinkme.codegen.model.config;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/14 下午4:35
 */
@Data
@JacksonXmlRootElement(namespace = "config")
public class ConfigBean {

    private Variables variables;

    private Templates templates;

    private FilesBean files;

    private List<TableBean> table;







}





