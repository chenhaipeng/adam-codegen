package com.thinkme.codegen.main;

import com.thinkme.codegen.model.config.ConfigBean;
import com.thinkme.codegen.service.GenService;
import com.thinkme.codegen.utils.JacksonUtils;
import com.thinkme.utils.io.URLResourceUtil;

import java.io.File;


public class Codegen {

    public static void main(String[] args) {
        new Codegen().execute();
    }

    public void execute() {
        try {
            String path = "classpath://codegenconfig.xml";
            File file = URLResourceUtil.asFile(path);

            if (!file.exists()) {
                throw new RuntimeException("codegenconfig.xml 不存在");
            }
            ConfigBean codegenConfigBean = JacksonUtils.xmlToBean(file.getAbsolutePath(), ConfigBean.class);

            GenService genService = new GenService();
            genService.genTableByConfig(codegenConfigBean, "gen");
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }

    }


}
