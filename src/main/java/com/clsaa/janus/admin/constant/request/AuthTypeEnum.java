package com.clsaa.janus.admin.constant.request;

/**
 * 安全认证类型,1为无认证,2为公有云通用认证,3为Oauth2客户端模式认证
 *
 * @author 任贵杰
 * @summary 安全认证类型枚举
 * @since 2018/5/19
 */
public enum AuthTypeEnum {
    /**
     * 安全认证类型:无认证,编码:1
     */
    无认证(1),

    /**
     * 安全认证类型:公有云通用认证,编码:2
     */
    公有云通用认证(2),

    /**
     * 安全认证类型:Oauth2客户端模式认证,编码:3
     */
    Oauth2客户端模式认证(3);

    private int code;

    AuthTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /**
     * 通过安全认证类型编码获取对应BodyFormat枚举,若编码错误,则返回null
     *
     * @param code 安全认证类型编码
     * @return {@link AuthTypeEnum}
     */
    public static AuthTypeEnum getByCode(int code) {
        for (AuthTypeEnum authTypeEnum : AuthTypeEnum.values()) {
            if (authTypeEnum.code == code) {
                return authTypeEnum;
            }
        }
        return null;
    }
}
