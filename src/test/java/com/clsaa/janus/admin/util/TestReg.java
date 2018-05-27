package com.clsaa.janus.admin.util;

import org.junit.Test;

import java.util.regex.Pattern;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/25
 */
public class TestReg {
    public static final java.util.regex.Pattern PATTERN = Pattern.compile("^[\u4e00-\u9fa5A-Za-z]+[\u4e00-\u9fa5A-Za-z0-9_]{3,50}");

    @Test
    public void test(){
        System.out.println(PATTERN.matcher("name2").matches());
        System.out.println(PATTERN.matcher("sdfsssd").matches());
        System.out.println(PATTERN.matcher("你是猪那").matches());
        System.out.println(PATTERN.matcher("你是猪那123").matches());
        System.out.println(PATTERN.matcher("你是猪那_").matches());
        System.out.println(PATTERN.matcher("_sdfsd").matches());

    }
}
