package com.clsaa.janus.admin.constant.response;

/**
 * 响应类型,1为HTTP/HTTPS,2为函数计算
 *
 * @author 任贵杰
 * @summary 后端服务类型枚举
 * @since 2018-05-24
 */
public enum ResultTypeEnum {
    /**
     * 响应类型:application/json;charset=utf-8
     */
    JSON(1),

    /**
     * 响应类型:text/html;charset=utf-8
     */
    HTML(2);

    private int code;

    ResultTypeEnum(int code) {
        this.code = code;
    }

    /**
     * 通过响应类型编码获取对应响应类型枚举,若编码错误,则返回null
     *
     * @param code 后端服务类型编码
     * @return {@link ResultTypeEnum}
     */
    public static ResultTypeEnum getByCode(int code) {
        for (ResultTypeEnum resultTypeEnum : ResultTypeEnum.values()) {
            if (resultTypeEnum.code == code) {
                return resultTypeEnum;
            }
        }
        return null;
    }
}
