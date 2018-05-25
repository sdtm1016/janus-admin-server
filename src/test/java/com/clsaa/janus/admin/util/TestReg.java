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
    public static final Pattern PATTERN = Pattern.compile("^[^0-9_]+[A-Za-z0-9_]{6,80}");

    @Test
    public void test(){
        System.out.println(PATTERN.matcher("21fd").matches());
        System.out.println(PATTERN.matcher("sdfsssd").matches());
        System.out.println(PATTERN.matcher("_sdfsd").matches());

    }
}
