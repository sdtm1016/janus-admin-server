package com.clsaa.janus.admin.constant.request;

/**
 * Body格式,1为FORM,2为STREAM
 *
 * @author 任贵杰
 * @summary 请求Body格式枚举
 * @since 2018/5/19
 */
public enum BodyFormatEnum {
    /**
     * Body格式:FORM,编码:1
     */
    FORM(1),

    /**
     * Body格式:STREAM,编码:2
     */
    STREAM(2);

    private int code;

    BodyFormatEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /**
     * 通过BodyFormat编码获取对应BodyFormat枚举,若编码错误,则返回null
     *
     * @param code BodyFormat编码
     * @return {@link BodyFormatEnum}
     */
    public static BodyFormatEnum getByCode(int code) {
        for (BodyFormatEnum bodyFormatEnum : BodyFormatEnum.values()) {
            if (bodyFormatEnum.code == code) {
                return bodyFormatEnum;
            }
        }
        return null;
    }
}
