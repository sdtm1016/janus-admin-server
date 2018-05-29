package com.clsaa.janus.admin.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/29
 */
public class VersionUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private VersionUtil(){
        throw new UnsupportedOperationException();
    }

    public static String newCode() {
        String orderId = "";
        orderId += getDateTime();
        orderId += getRandomCode();
        return orderId;
    }

    private static String getDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(FORMATTER);
    }

    private static String getRandomCode(){
        String randomCode = UUID.randomUUID().toString().replace("-","");
        return randomCode.substring(14);
    }

    public static void main(String[] args) {
        System.out.println(newCode());
    }
}
