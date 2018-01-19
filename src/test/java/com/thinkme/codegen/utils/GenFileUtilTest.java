package com.thinkme.codegen.utils;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/16 下午5:42
 */
public class GenFileUtilTest {
    @Test
    public void composePath() throws Exception {
        String path = GenFileUtil.composePath("/users/hai/", "/aaa", "/bbb");
        System.out.println(path);


    }

    @Test
    public void trimPrefix() throws Exception {
        System.out.println(GenFileUtil.trimPathPrefix("/Users/hai/IdeaProjects/proj_think_me/"));
    }

    @Test
    public void trimSuffix() throws Exception {
        System.out.println(GenFileUtil.trimPathSuffix("/Users/hai/IdeaProjects/proj_think_me/"));

    }

    @Test
    public void suffix() {
        String abc = "tb_";
        System.out.println(Arrays.asList(StringUtils.split(abc,",")));

        String s = CharMatcher.anyOf("tb_").trimLeadingFrom("tb_user");
        System.out.println(s);

        String s2 = StringUtils.removeFirst("tb_user", "tb_");
        System.out.println(s2);

        String ss = "abcd${table?lower_camel}".replaceAll("\\$\\{table\\?lower_camel\\}",
                CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
        System.out.println(ss);


    }

}