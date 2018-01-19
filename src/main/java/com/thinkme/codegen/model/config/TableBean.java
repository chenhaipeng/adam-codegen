package com.thinkme.codegen.model.config;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/14 下午5:02
 */
@Data
public class TableBean {

    private String tableNames;

    @JacksonXmlProperty(namespace = "variable")
    private List<Variable> variable;

    private List<SubTable> subTable = new ArrayList<>();

    //去掉前缀
    private String prefix;




}


