package com.clsaa.janus.admin.constant.common;

/**
 * 参数位置,1为PATH,2为QUERY,3为HEAD,4为BODY
 *
 * @author 任贵杰
 * @summary 参数位置枚举
 * @since 2018/5/19
 */
public enum ParamLocationEnum {
    /**
     * 参数位置:PATH
     */
    PATH(1),
    /**
     * 参数位置:QUERY
     */
    QUERY(2),
    /**
     * 参数位置:HEAD
     */
    HEAD(3),
    /**
     * 参数位置:BODY
     */
    BODY(4);

    private int code;

    ParamLocationEnum(int code) {
        this.code = code;
    }

    /**
     * 通过参数位置编码获取对应参数位置枚举,若编码错误,则返回null
     *
     * @param code 参数位置编码
     * @return {@link ParamLocationEnum}
     */
    public static ParamLocationEnum getByCode(int code) {
        for (ParamLocationEnum paramLocationEnum : ParamLocationEnum.values()) {
            if (paramLocationEnum.code == code) {
                return paramLocationEnum;
            }
        }
        return null;
    }
}
