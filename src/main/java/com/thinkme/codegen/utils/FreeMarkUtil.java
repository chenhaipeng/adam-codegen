package com.thinkme.codegen.utils;

import com.thinkme.codegen.model.config.ConfigBean;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/16 上午10:46
 */
@Slf4j
public class FreeMarkUtil {

    public void genTableByConfig(ConfigBean configBean){
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        String baseDir = configBean.getFiles().getBaseDir();
        try {
            cfg.setDirectoryForTemplateLoading(new File(baseDir));

//            List<TableModel> tableModels = my

        } catch (IOException e) {
            log.error("生成文件失败",e);
        }
    }
}
