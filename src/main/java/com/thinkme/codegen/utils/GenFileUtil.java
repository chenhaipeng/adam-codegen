package com.thinkme.codegen.utils;

import com.google.common.base.CaseFormat;
import com.thinkme.codegen.model.config.ConfigBean;
import com.thinkme.codegen.model.config.FileBean;
import com.thinkme.codegen.model.config.Template;
import com.thinkme.codegen.model.config.Templates;
import com.thinkme.utils.collection.type.Pair;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/16 下午5:33
 */
public class GenFileUtil {
    public static final String lower_hyphen = "lower_hyphen";
    public static final String lower_underscore = "lower_underscore";
    public static final String lower_camel = "lower_camel";
    public static final String upper_camel = "upper_camel";

    /**
     * 设置模板路径
     *
     * @param fileBean
     * @param configBean
     */
    public static void getTemplatePath(FileBean fileBean, ConfigBean configBean) {
        Templates templates = configBean.getTemplates();
        List<Template> templateList = templates.getTemplate();
        for (Template template : templateList) {
            if (fileBean.getRefTemplate().equals(template.getId())) {
                fileBean.setTemplatePath(template.getPath());
            }
        }

    }

    String ss = "abcd${table?lower_camel}".replaceAll("\\$\\{table\\?lower_camel\\}",
            CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));

    public static String replaceVariable(String filename, Map<String,String> variablesMap) {
        for (Map.Entry<String,String> variable : variablesMap.entrySet()) {
            filename = filename.replaceAll("\\$\\{" + variable.getKey() + "\\}", variable.getValue());
            if (filename.indexOf("?") != -1) {
                filename = filename.replaceAll("\\$\\{" + variable.getKey() + "\\?lower_hyphen\\}",
                        CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, variable.getValue()));

                filename = filename.replaceAll("\\$\\{" + variable.getKey() + "\\?lower_underscore\\}",
                        CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_UNDERSCORE, variable.getValue()));

                filename = filename.replaceAll("\\$\\{" + variable.getKey() + "\\?lower_camel\\}",
                        CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, variable.getValue()));

                filename = filename.replaceAll("\\$\\{" + variable.getKey() + "\\?upper_camel\\}",
                        CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, variable.getValue()));
            }

        }
        if (filename.indexOf("{") != -1)
            throw new RuntimeException(String.format("参数:%s 没有正确替换", filename));
        return filename;
    }

    public static String replaceVariable(String tagStr, String tableName) {
        if (StringUtils.isBlank(tagStr))
            return null;

        tagStr = tagStr.replaceAll("\\{tableName\\}", tableName);
        if (tagStr.indexOf("{") != -1)
            throw new RuntimeException(String.format("参数:%s 没有正确替换", tagStr));
        return tagStr;
    }

    public static String composePath(String baseDir, String... pathNames) {
        StringBuilder sb = new StringBuilder(trimPathSuffix(baseDir));
        for (String path : pathNames) {
            sb.append(File.separator).append(trimPathSuffix(trimPathPrefix(path)));
        }
        return sb.toString();
    }

    /**
     * 检索开始statTag 与endTag 内容是否存在
     *
     * @param content
     * @param startTag
     * @param endTag
     * @return
     */
    public static Pair<Boolean, int[]> isExistStartEndTags(String content, String startTag, String endTag) {

        if (StringUtils.isBlank(startTag) || StringUtils.isBlank(endTag)) {
            return new Pair<>(Boolean.FALSE, new int[]{});
        }
        int a = content.indexOf(startTag);
        int b = content.indexOf(endTag);

        boolean flag = a != -1 && b != -1 && a < b;
        //截取的标记
        b = b + endTag.length();
        int[] index = new int[]{a, b};
        Pair<Boolean, int[]> pair = new Pair<>(flag, index);
        return pair;
    }

    /**
     * 将目标文件插入到startIndex 与endIndex 之间
     *
     * @param content       源文本
     * @param startIndex
     * @param endIndex
     * @param targetContent 目标文本
     * @return
     */
    public static String replaceTags(String content, int startIndex, int endIndex, String targetContent) {
        StringBuilder sb = new StringBuilder();
        sb.append(content.substring(0, startIndex));
        //换行
        return sb.append("\r\n").append(targetContent).append("\r\n").append(content.substring(endIndex)).toString();

    }

    public static Pair<Boolean, Integer> isExistInsertTag(String content, String insertTag) {
        Integer a = content.indexOf(insertTag);
        boolean flag = a != -1;
        Pair<Boolean, Integer> pair = new Pair<>(flag, a);
        return pair;

    }

    /**
     * 将目标文件插入insertTag
     *
     * @param content
     * @param insertTag
     * @param index
     * @param targetContent
     * @return
     */
    public static String replaceInsertTags(String content, String insertTag, int index, String targetContent) {
        StringBuilder sb = new StringBuilder();
        sb.append(content.substring(0, index)).append("\r\n");
        sb.append(targetContent).append("\r\n");
        sb.append(content.substring(index + insertTag.length()));

        return sb.toString();

    }

    /**
     * 去掉前缀
     *
     * @param pathName
     * @return
     */
    public static String trimPathPrefix(String pathName) {
        while (pathName.startsWith(File.separator)) {
            pathName = pathName.substring(File.separator.length());
        }
        return pathName;

    }


    /**
     * 去掉后缀
     *
     * @param path
     * @return
     */
    public static String trimPathSuffix(String path) {
        while (path.endsWith(File.separator)) {
            path = path.substring(0, path.length() - File.separator.length());
        }
        return path;
    }


    /**
     * 去掉表名前缀
     *
     * @return
     */
    public static String trimTableNamePrefix(String tableName, String prefix) {
        if (StringUtils.isBlank(prefix)) {
            return tableName;
        }
        String result = "";
        String[] prefixArr = StringUtils.split(prefix, ",");
        for (String s : prefixArr) {
            result = StringUtils.removeFirst(tableName, s);
            if (result.length() != tableName.length())
                break;
        }
        return result;

    }


}
