package com.thinkme.codegen.model.config;

import lombok.Data;

import java.util.List;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/14 下午4:41
 */
@Data
public class FilesBean{

    private String baseDir;

    private List<FileBean> file;

}

