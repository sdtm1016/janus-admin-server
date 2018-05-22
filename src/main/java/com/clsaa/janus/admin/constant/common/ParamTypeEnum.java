package com.clsaa.janus.admin.constant.common;

/**
 * 参数类型,1为STRING,2为INT,3为LONG,4为FLOAT,5为DOUBLE,6为BOOLEAN
 *
 * @author 任贵杰
 * @summary 参数类型枚举
 * @since 2018/5/19
 */
public enum ParamTypeEnum {
    /**
     * 参数类型:STRING
     */
    STRING(1, String.class),
    /**
     * 参数类型:INT
     */
    INT(2, Integer.class),
    /**
     * 参数类型:LONG
     */
    LONG(3, Long.class),
    /**
     * 参数类型:FLOAT
     */
    FLOAT(4, Float.class),
    /**
     * 参数类型:DOUBLE
     */
    DOUBLE(5, Double.class),
    /**
     * 参数类型:BOOLEAN
     */
    BOOLEAN(6, Boolean.class);

    private int code;
    private Class clazz;

    ParamTypeEnum(int code, Class clazz) {
        this.code = code;
        this.clazz = clazz;
    }

    /**
     * 通过参数类型编码获取对应参数类型枚举,若编码错误,则返回null
     *
     * @param code 参数类型编码
     * @return {@link ParamTypeEnum}
     */
    public static ParamTypeEnum getByCode(int code) {
        for (ParamTypeEnum paramLocationEnum : ParamTypeEnum.values()) {
            if (paramLocationEnum.code == code) {
                return paramLocationEnum;
            }
        }
        return null;
    }
}
