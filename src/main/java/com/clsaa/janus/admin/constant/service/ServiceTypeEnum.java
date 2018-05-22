package com.clsaa.janus.admin.constant.service;

/**
 * 后端服务类型,1为HTTP/HTTPS,2为函数计算
 *
 * @author 任贵杰
 * @summary 后端服务类型枚举
 * @since 2018/5/19
 */
public enum ServiceTypeEnum {
    /**
     * 后端服务类型:HTTP/HTTPS
     */
    HTTP(1),

    /**
     * 后端服务类型:函数计算
     */
    函数计算(2);

    private int code;

    ServiceTypeEnum(int code) {
        this.code = code;
    }

    /**
     * 通过后端服务类型编码获取对应后端服务类型枚举,若编码错误,则返回null
     *
     * @param code 后端服务类型编码
     * @return {@link ServiceTypeEnum}
     */
    public static ServiceTypeEnum getByCode(int code) {
        for (ServiceTypeEnum serviceTypeEnum : ServiceTypeEnum.values()) {
            if (serviceTypeEnum.code == code) {
                return serviceTypeEnum;
            }
        }
        return null;
    }
}
