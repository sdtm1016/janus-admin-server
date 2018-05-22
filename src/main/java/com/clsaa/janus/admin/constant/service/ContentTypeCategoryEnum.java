package com.clsaa.janus.admin.constant.service;

/**
 * ContentType分类,1为透传,2为网关默认,3为自定义
 *
 * @author 任贵杰
 * @summary ContentType分类枚举
 * @since 2018/5/19
 */
public enum ContentTypeCategoryEnum {
    /**
     * ContentType分类:透传
     */
    透传(1),
    /**
     * ContentType分类:网关默认
     */
    网关默认(2),
    /**
     * ContentType分类:自定义
     */
    自定义(3);

    public static final String DEFAULT_CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";

    private int code;

    ContentTypeCategoryEnum(int code) {
        this.code = code;
    }

    /**
     * 通过ContentType分类编码获取对应ContentType分类枚举,若编码错误,则返回null
     *
     * @param code ContentType分类编码
     * @return {@link ContentTypeCategoryEnum}
     */
    public static ContentTypeCategoryEnum getByCode(int code) {
        for (ContentTypeCategoryEnum contentTypeCategoryEnum : ContentTypeCategoryEnum.values()) {
            if (contentTypeCategoryEnum.code == code) {
                return contentTypeCategoryEnum;
            }
        }
        return null;
    }
}
