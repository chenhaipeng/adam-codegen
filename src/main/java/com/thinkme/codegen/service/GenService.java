package com.thinkme.codegen.service;

import com.thinkme.codegen.db.MySqlHelper;
import com.thinkme.codegen.model.TableModel;
import com.thinkme.codegen.model.config.*;
import com.thinkme.codegen.utils.GenFileUtil;
import com.thinkme.utils.base.BeanUtils;
import com.thinkme.utils.collection.type.Pair;
import com.thinkme.utils.io.URLResourceUtil;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/16 上午10:59
 */
@Slf4j
public class GenService {


    public void genTableByConfig(ConfigBean configBean, String mod) {
        List<TableModel> tableModelList = getTableModelList(configBean);
        try {
            if (mod.equals("gen")) {
                log.info("***************begin gen*******************");
            } else {
                log.info("***************begin del*******************");
            }

            FilesBean filesBean = configBean.getFiles();
            List<FileBean> fileBeens = filesBean.getFile();
            if (BeanUtils.isEmpty(filesBean)) {
                log.info("未配置生成file标签,不处理");
                return;
            }

            List<Variable> variableList = configBean.getVariables().getVariable();
            Map<String, String> variableMap = new HashMap<>();
            //全局参数
            for (Variable variable : variableList) {
                variableMap.put(variable.getName(), variable.getValue());
            }

            for (TableModel tableModel : tableModelList) {
                String tableName = tableModel.getTableName();
                //表级参数
                List<Variable> variables = tableModel.getVariables();
                for (Variable variable : variables) {
                    variableMap.put(variable.getName(), variable.getValue());
                }
                //加入表默认数据
                variableMap.put("tableName", tableModel.getTableName());
                variableMap.put("noPrefixTableName", tableModel.getNoPrefixTableName());
                variableMap.put("tableDisplayName", tableModel.getTableDisplayName());

                boolean isSub = tableModel.getIsSub();

                for (FileBean fileBean : fileBeens) {

                    String fileRealName = GenFileUtil.replaceVariable(fileBean.getFilename(), variableMap);
                    String realFileDir = GenFileUtil.replaceVariable(fileBean.getDir(), variableMap);
                    String startTag = GenFileUtil.replaceVariable(fileBean.getStartTag(), tableName);
                    String endTag = GenFileUtil.replaceVariable(fileBean.getEndTag(), tableName);

                    fileBean.setFileRealName(fileRealName);
                    fileBean.setRealFileDir(realFileDir);
                    fileBean.setStartTag(startTag);
                    fileBean.setEndTag(endTag);
                    fileBean.setBaseDir(filesBean.getBaseDir());

                    //设置模板路径
                    GenFileUtil.getTemplatePath(fileBean, configBean);

                    Map<String, Object> paramMap = new HashMap<>();
                    paramMap.put("model", tableModel);
                    paramMap.put("vars", variableMap);
                    paramMap.put("date", new Date());
                    if (mod.equals("gen")) {
                        if (fileBean.getAppend()) {
                            //baseDir ,fileName,fileBean.getTemplate
                            this.genFile(fileBean, configBean, paramMap, Boolean.TRUE);
                        } else if (fileBean.getOverride()) {
                            this.genFile(fileBean, configBean, paramMap, Boolean.FALSE);
                        }
                        log.info("***************gen success*******************");
                    } else {
                        this.delFile(fileBean);
                        log.info("***************del success*******************");

                    }


                }

            }


        } catch (Exception e) {
            log.error("生成文件失败", e);
        }

    }

    private void delFile(FileBean fileBean) {
        //处理附加情况
        String filePath = GenFileUtil.composePath(fileBean.getRealFileDir(), fileBean.getFileRealName());
        File realFile = new File(fileBean.getBaseDir(), filePath);
        if (!realFile.exists()) {
            log.info("文件不存在:{},忽略",realFile.getAbsoluteFile());
        }else{
            log.info("删除文件：{}",realFile.getAbsoluteFile());
            realFile.deleteOnExit();
        }


    }

    private void genFile(FileBean fileBean, ConfigBean configBean, Map<String, Object> paramMap, Boolean isAppend) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        String templatePath = fileBean.getTemplatePath();
        String startTag = fileBean.getStartTag();
        String endTag = fileBean.getEndTag();
        //获取模板,默认类路径下
        if (templatePath.indexOf("classpath") == -1) {
            templatePath = "classpath://" + templatePath;
        }
        File templateFile = URLResourceUtil.asFile(templatePath);
        if (!templateFile.exists()) {
            throw new RuntimeException("请确认" + templatePath + "是否存在");
        }
        //设置模板路径
        cfg.setDirectoryForTemplateLoading(templateFile.getParentFile());

        String baseDir = configBean.getFiles().getBaseDir();
        if (StringUtils.isBlank(baseDir)) {
            log.error("未配置baseDir 路径");
            throw new RuntimeException("未配置baseDir 路径");
        }

        freemarker.template.Template cfgTemplate = cfg.getTemplate(templateFile.getName());
        StringWriter templateWriter = new StringWriter();
        cfgTemplate.process(paramMap, templateWriter);

        //最终生成的模版内容
        String templateContent = templateWriter.toString();

        String content = "";

        //处理附加情况
        String filePath = GenFileUtil.composePath(fileBean.getRealFileDir(), fileBean.getFileRealName());
        File realFile = new File(fileBean.getBaseDir(), filePath);
        //文件存在、附加
        if (realFile.exists() && isAppend) {
            //读取文件
            String fileContent = FileUtils.readFileToString(realFile, "UTF-8");
            Pair<Boolean, int[]> pair = GenFileUtil.isExistStartEndTags(fileContent, startTag, endTag);
            Boolean flag = pair.getFirst();
            boolean tagType = false;
            if (StringUtils.isNotBlank(startTag) && StringUtils.isNotBlank(endTag) && flag) {
                int[] index = pair.getSecond();
                content = GenFileUtil.replaceTags(fileContent, index[0], index[1], templateContent);
                tagType = true;
            }

            if (!tagType) {
                //标签模式与插入模式不同时生效
                String insertTag = fileBean.getInsertTag();
                Pair<Boolean, Integer> integerPair = GenFileUtil.isExistInsertTag(fileContent, insertTag);
                boolean insertTagFlag = integerPair.getFirst();
                if (insertTagFlag) {
                    content = GenFileUtil.replaceInsertTags(fileContent, insertTag, integerPair.getSecond(), templateContent);
                }
            }

        } else {
            File parentDir = realFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            realFile.createNewFile();
        }
        content = StringUtils.isBlank(content) ? templateContent : content;
        log.info("生成文件：{}", realFile.getAbsoluteFile());

        FileUtils.write(realFile, content, "UTF-8");


    }


    /**
     * 获取配置的表与子表信息
     *
     * @param configBean
     * @return
     */
    public List<TableModel> getTableModelList(ConfigBean configBean) {
        MySqlHelper mySqlHelper = new MySqlHelper();
        List<TableModel> result = new ArrayList<>();
        List<TableBean> tableList = configBean.getTable();
        for (TableBean tableBean : tableList) {
            String tableNames = tableBean.getTableNames().toLowerCase();
            //表名
            String[] tableNameArr = StringUtils.split(tableNames, ",");

            for (String tableName : tableNameArr) {
                if (StringUtils.isBlank(tableName)) {
                    continue;
                }
                String prefix = tableBean.getPrefix();
                TableModel tableModel = mySqlHelper.getTableInfo(tableName, prefix);
                tableModel.setVariables(tableBean.getVariable());
                tableModel.setIsSub(false);

                List<SubTable> subTableList = tableBean.getSubTable();
                for (SubTable subTable : subTableList) {
                    String subTableName = subTable.getTableName();
                    String foreignKey = subTable.getForeignKey();
                    TableModel subTableModel = mySqlHelper.getTableInfo(subTableName, prefix);
                    subTableModel.setVariables(subTable.getVariable());
                    subTableModel.setIsSub(true);
                    subTableModel.setForeignKey(foreignKey);
                    tableModel.getSubTableList().add(subTableModel);
                    result.add(subTableModel);

                }
                result.add(tableModel);

            }


        }

        return result;

    }
}
