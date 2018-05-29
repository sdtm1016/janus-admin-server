package com.clsaa.janus.admin.entity.vo.v1;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * API快照信息视图层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class SnapApiV1 implements Serializable {

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
     * 请求配置快照
     */
    private SnapRequestConfigV1 snapRequestConfig;
    /**
     * 请求参数快照列表
     */
    private List<SnapRequestParamV1> snapRequestParams;
    /**
     * 后端服务配置快照
     */
    private SnapServiceConfigV1 snapServiceConfig;
    /**
     * 后端服务常量参数快照列表
     */
    private List<SnapServiceConstParamV1> snapServiceConstParams;
    /**
     * 后端服务错误码快照列表
     */
    private List<SnapServiceErrorCodeV1> snapServiceErrorCodes;
    /**
     * 后端服务参数快照列表
     */
    private List<SnapServiceParamV1> snapServiceParams;
}
