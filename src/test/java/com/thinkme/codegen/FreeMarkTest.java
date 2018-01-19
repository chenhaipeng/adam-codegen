package com.thinkme.codegen;


import com.thinkme.utils.io.FilePathUtil;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenhaipeng
 *
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/15 下午6:40
 */
public class FreeMarkTest {
    public static void main(String[] args) {
        try {
            Map root = new HashMap();
            root.put("str", "hello world!");
            List data = new ArrayList();
            data.add("11");
            data.add("12");
            data.add("13");
            root.put("data", data);

            Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
            String path = FilePathUtil.getProjectRootPath()+File.separator+"template"+File.separator;
            File dir = new File(path);
            if (!dir.exists()){
                dir.mkdirs();
            }
            cfg.setDirectoryForTemplateLoading(new File(path));
            cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
            Template temp = cfg.getTemplate("demo.ftl");
            String fileName = "demo.htm";
            File file = new File(path + File.separator+fileName);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            temp.process(root, bw);
            bw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
