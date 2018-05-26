package com.clsaa.janus.admin.entity.vo.v1;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * API信息视图层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class ApiV1 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
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
     * 创建时间
     */
    private Timestamp ctime;
    /**
     * 修改时间
     */
    private Timestamp mtime;
    /**
     * 前端请求配置
     */
    private RequestConfigV1 requestConfig;
    /**
     * 前端请求参数列表
     */
    private List<RequestParamV1> requestParams;
    /**
     * 后端服务配置
     */
    private ServiceConfigV1 serviceConfig;
    /**
     * 后端服务常量参数
     */
    private List<ServiceConstParamV1> serviceConstParams;
    /**
     * 后端服务错误码
     */
    private List<ServiceErrorCodeV1> serviceErrorCodes;
    /**
     * 后端请求参数列表
     */
    private List<ServiceParamV1> serviceParams;
}
