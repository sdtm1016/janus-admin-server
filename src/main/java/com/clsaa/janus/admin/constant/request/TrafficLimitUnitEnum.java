package com.clsaa.janus.admin.constant.request;

import java.util.concurrent.TimeUnit;

/**
 * @author 任贵杰
 * @summary 限流策略时间单位枚举
 * @since 2018/5/27
 */
public enum TrafficLimitUnitEnum {
    /**
     * 秒,编码为1
     */
    秒(1, TimeUnit.SECONDS),
    /**
     * 分钟,编码为2
     */
    分钟(2, TimeUnit.MINUTES),
    /**
     * 小时,编码为3
     */
    小时(3, TimeUnit.HOURS);


    private int code;
    private TimeUnit timeUnit;

    public int getCode() {
        return code;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    TrafficLimitUnitEnum(int code, TimeUnit timeUnit) {
        this.code = code;
        this.timeUnit = timeUnit;
    }

    /**
     * 根据限流策略时间单位编码获取限流策略时间单位枚举
     *
     * @param code 限流策略时间单位编码
     * @return {@link TrafficLimitUnitEnum}
     */
    public static TrafficLimitUnitEnum getByCode(int code) {
        for (TrafficLimitUnitEnum trafficLimitUnitEnum : TrafficLimitUnitEnum.values()) {
            if (trafficLimitUnitEnum.getCode() == code) {
                return trafficLimitUnitEnum;
            }
        }
        return null;
    }
}
