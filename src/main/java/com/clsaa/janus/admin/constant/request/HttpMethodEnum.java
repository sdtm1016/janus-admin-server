package com.clsaa.janus.admin.constant.request;

/**
 * HttpMethod,1为GET,2为POST,3为PUT,4为DELETE,5为PATCH,6为HEAD
 *
 * @author 任贵杰
 * @summary HttpMethod枚举
 * @since 2018/5/19
 */
public enum HttpMethodEnum {
    /**
     * HttpMethod:GET,编码:1
     */
    GET(1),

    /**
     * HttpMethod:GET,编码:2
     */
    POST(2),

    /**
     * HttpMethod:GET,编码:3
     */
    PUT(3),

    /**
     * HttpMethod:GET,编码:4
     */
    DELETE(4),

    /**
     * HttpMethod:GET,编码:5
     */
    PATCH(5),

    /**
     * HttpMethod:GET,编码:6
     */
    HEAD(6);

    private int code;

    HttpMethodEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /**
     * 通过HttpMethod编码获取对应HttpMethod枚举,若编码错误,则返回null
     *
     * @param code HttpMethod编码
     * @return {@link HttpMethodEnum}
     */
    public static HttpMethodEnum getByCode(int code) {
        for (HttpMethodEnum httpMethodEnum : HttpMethodEnum.values()) {
            if (httpMethodEnum.code == code) {
                return httpMethodEnum;
            }
        }
        return null;
    }
}
