package com.clsaa.janus.admin.constant.request;

/**
 * API可见性,1为公开,2为私有
 *
 * @author 任贵杰
 * @summary API可见性枚举
 * @since 2018/5/19
 */
public enum ApiVisibilityEnum {
    /**
     * API可见性:公开,编码:1
     */
    公开(1),

    /**
     * API可见性:私有,编码:2
     */
    私有(2);

    private int code;

    ApiVisibilityEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /**
     * 通过API可见性编码获取对应API可见性枚举,若编码错误,则返回null
     *
     * @param code API可见性编码
     * @return {@link ApiVisibilityEnum}
     */
    public static ApiVisibilityEnum getByCode(int code) {
        for (ApiVisibilityEnum authTypeEnum : ApiVisibilityEnum.values()) {
            if (authTypeEnum.code == code) {
                return authTypeEnum;
            }
        }
        return null;
    }
}
