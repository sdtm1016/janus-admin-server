package com.clsaa.janus.admin.constant.request;

/**
 * 入参请求模式,1为映射,2为透传
 *
 * @author 任贵杰
 * @summary 入参请求模式枚举
 * @since 2018/5/19
 */
public enum ParamModeEnum {
    /**
     * 入参请求模式:映射,编码:1
     */
    映射(1),

    /**
     * 入参请求模式:透传,编码:2
     */
    透传(2);

    private int code;

    ParamModeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /**
     * 通过入参请求模式编码获取对应入参请求模式枚举,若编码错误,则返回null
     *
     * @param code 入参请求模式编码
     * @return {@link ParamModeEnum}
     */
    public static ParamModeEnum getByCode(int code) {
        for (ParamModeEnum p : ParamModeEnum.values()) {
            if (p.code == code) {
                return p;
            }
        }
        return null;
    }
}
