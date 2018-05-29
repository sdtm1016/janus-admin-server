package com.clsaa.janus.admin.entity.dto.v1;

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
    private String regionId;
    /**
     * 分组id,t_group.id
     */
    private String groupId;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
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
    private String successResultSample;
    /**
     * 失败响应示例
     */
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
