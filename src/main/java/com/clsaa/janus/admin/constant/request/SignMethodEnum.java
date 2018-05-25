package com.clsaa.janus.admin.constant.request;

/**
 * 签名算法,当前支持HmacSHA256
 *
 * @author 任贵杰
 * @summary 安全认证类型枚举
 * @since 2018/5/19
 */
public enum SignMethodEnum {
    /**
     * 签名算法:HmacSHA256
     */
    HmacSHA256();

    /**
     * 通过签名算法名称获取对应签名算法枚举,若编码错误,则返回null
     *
     * @param name 签名算法名
     * @return {@link SignMethodEnum}
     */
    public static SignMethodEnum getByCode(String name) {
        for (SignMethodEnum authTypeEnum : SignMethodEnum.values()) {
            if (authTypeEnum.name().toUpperCase().equals(name.toUpperCase())) {
                return authTypeEnum;
            }
        }
        return null;
    }
}
