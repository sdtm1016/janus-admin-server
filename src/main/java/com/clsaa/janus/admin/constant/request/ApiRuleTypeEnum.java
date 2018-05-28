package com.clsaa.janus.admin.constant.request;

/**
 * API规则类型,1为ip访问控制,2为流量限制,3为后端认证信息
 *
 * @author 任贵杰
 * @summary API规则类型
 * @since 2018/5/19
 */
public enum ApiRuleTypeEnum {
    /**
     * API规则类型:ip访问控制,编码:1
     */
    ip访问控制(1),

    /**
     * API规则类型:流量限制,编码:2
     */
    流量限制(2),

    /**
     * API规则类型:后端认证信息,编码:3
     */
    后端认证信息(3);

    private int code;

    ApiRuleTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /**
     * 通过API规则类型编码获取对应API规则类型枚举,若编码错误,则返回null
     *
     * @param code API规则类型编码
     * @return {@link ApiRuleTypeEnum}
     */
    public static ApiRuleTypeEnum getByCode(int code) {
        for (ApiRuleTypeEnum authTypeEnum : ApiRuleTypeEnum.values()) {
            if (authTypeEnum.code == code) {
                return authTypeEnum;
            }
        }
        return null;
    }
}
