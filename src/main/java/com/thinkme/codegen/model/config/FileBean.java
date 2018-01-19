package com.thinkme.codegen.model.config;

import lombok.Data;

@Data
public class FileBean {

    /**
     * 生成的文件存放的目录
     */
    private String dir;

    private String realFileDir;

    /**
     * 引用的模板,引用模板的ID
     */
    private String refTemplate;

    /**
     * 生成的文件名称
     */
    private String filename;

    private String fileRealName;

    /**
     * 子表生成文件
     */
    private Boolean sub = false;

    /**
     * 是否覆盖原有文件 默认为false
     */
    private Boolean override = false;

    /**
     * 是否添加
     */
    private Boolean append = false;

    /**
     * 标签功能，新增的内容插在之前
     */
    private String insertTag;

    /**
     * 起始标签
     */
    private String startTag ;

    /**
     * 结束标签
     */
    private String endTag ;

    /**
     * 对应的模板路径
     */
    private String templatePath;


    private String baseDir;


}
