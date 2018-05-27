package com.clsaa.janus.admin.constant.request;

/**
 * IP控制类型,1为白名单,2为黑名单
 *
 * @author 任贵杰
 * @summary IP控制类型枚举
 * @since 2018-05-28
 */
public enum IPStrategyTypeEnum {
    /**
     * IP控制类型:白名单,编码:1
     */
    白名单(1),

    /**
     * IP控制类型:黑名单,编码:2
     */
    黑名单(2);

    private int code;

    IPStrategyTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /**
     * 通过IP控制类型编码获取对应BodyFormat枚举,若编码错误,则返回null
     *
     * @param code IP控制类型编码
     * @return {@link IPStrategyTypeEnum}
     */
    public static IPStrategyTypeEnum getByCode(int code) {
        for (IPStrategyTypeEnum authTypeEnum : IPStrategyTypeEnum.values()) {
            if (authTypeEnum.code == code) {
                return authTypeEnum;
            }
        }
        return null;
    }
}
