package com.clsaa.janus.admin.constant.request;

/**
 * 双向通信API类别,1为普通,2为注册,3为注销,4为下行通知
 *
 * @author 任贵杰
 * @summary 双向通信API类别枚举
 * @since 2018/5/19
 */
public enum WSTypeEnum {
    /**
     * 双向通信API类别:普通
     */
    普通(1),

    /**
     * 双向通信API类别:注册
     */
    注册(2),

    /**
     * 双向通信API类别:注销
     */
    注销(3),

    /**
     * 双向通信API类别:下行通知
     */
    下行通知(4);

    private int code;

    WSTypeEnum(int code) {
        this.code = code;
    }

    /**
     * 通过双向通信API类别编码获取对应双向通信API类别枚举,若编码错误,则返回null
     *
     * @param code 双向通信API类别编码
     * @return {@link WSTypeEnum}
     */
    public static WSTypeEnum getByCode(int code) {
        for (WSTypeEnum ws : WSTypeEnum.values()) {
            if (ws.code == code) {
                return ws;
            }
        }
        return null;
    }
}
