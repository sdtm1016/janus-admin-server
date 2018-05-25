package com.clsaa.janus.admin.entity.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * API认证信息传输层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-25
 */
@Getter
@Setter
public class AuthDtoV1 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地域id,t_region.id
     */
    @Pattern(regexp = "[A-Za-z0-9_]{32}", message = "地域id格式不符合要求")
    private String regionId;
    /**
     * 用于权限认证的AK
     */
    @Pattern(regexp = "^[^_]+[A-Za-z0-9_]{6,80}", message = "AccessSecret格式不符合要求,支持英文字母、数字、英文格式的下划线，必须以英文字母或者数字开头，6~20个字符")
    private String accessKey;
    /**
     * 用于权限认证的AS
     */
    @Pattern(regexp = "^[A-Za-z0-9]+[A-Za-z0-9_@#!*]{6,255}", message = "AccessSecret格式不符合要求,支持英文字母、数字、英文格式的下划线，必须以英文字母或者数字开头，6~255个字符")
    private String accessSecret;
    /**
     * 名称
     */
    @Pattern(regexp = "^[^0-9]{4,50}", message = "AccessKey名称格式不符合要求,分组名称必须唯一，支持汉字、英文字母、数字、英文格式的下划线，必须以英文字母或汉字开头，4~50个字符")
    private String name;
    /**
     * 描述
     */
    @Length(max = 180, message = "分组名称长度不符合要求,请控制在0-180个字符")
    private String description;
}
