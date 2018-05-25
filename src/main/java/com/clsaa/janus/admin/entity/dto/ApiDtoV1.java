package com.clsaa.janus.admin.entity.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;


/**
 * API信息持久层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class ApiDtoV1 implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 地域id,t_region.id
     */
    @Length(min = 32, max = 32, message = "地域id格式不符合要求")
    @NotEmpty(message = "地域id不能为空")
    private String regionId;
    /**
     * 分组id,t_group.id
     */
    @Length(min = 32, max = 32, message = "API分组id格式不符合要求")
    @NotEmpty(message = "API分组id不能为空")
    private String groupId;
    /**
     * 名称
     */
    @Length(min = 4, max = 50, message = "API名称长度不符合要求,请控制在4-50个字符")
    @Pattern(regexp = "^[^0-9]+$", message = "API名称格式不符合要求,分组名称必须唯一，支持汉字、英文字母、数字、英文格式的下划线，必须以英文字母或汉字开头，4~50个字符")
    @NotEmpty(message = "API名称不能为空")
    private String name;
    /**
     * 描述
     */
    @Length(max = 180, message = "分组名称长度不符合要求,请控制在0-180个字符")
    private String description;
    /**
     * 安全认证类型,1为无认证,2为公有云通用认证,3为Oauth2客户端模式认证
     */
    private Integer authType;
    /**
     * 签名方法
     */
    private String signMethod;
    /**
     * 可见性,1为公开,2为私有
     */
    private Integer visibility;
    /**
     * 响应类型,1为application/json;charset=utf-8,2为text/html;charset=utf-8
     */
    private Integer resultType;
    /**
     * 成功响应示例
     */
    @Length(max = 255, message = "成功响应示例长度不符合要求,请控制在0-255个字符")
    private String successResultSample;
    /**
     * 失败响应示例
     */
    @Length(max = 255, message = "成功响应示例长度不符合要求,请控制在0-255个字符")
    private String failResultSample;
    /**
     * 前端请求配置
     */
    private RequestConfigDtoV1 requestConfig;
    /**
     * 前端请求参数列表
     */
    private List<RequestParamDtoV1> requestParams;
    /**
     * 后端服务配置
     */
    private ServiceConfigDtoV1 serviceConfig;
    /**
     * 后端服务常量参数
     */
    private List<ServiceConstParamDtoV1> serviceConstParams;
    /**
     * 后端服务错误码
     */
    private List<ServiceErrorCodeDtoV1> serviceErrorCodes;
    /**
     * 后端请求参数列表
     */
    private List<ServiceParamDtoV1> serviceParams;
}
