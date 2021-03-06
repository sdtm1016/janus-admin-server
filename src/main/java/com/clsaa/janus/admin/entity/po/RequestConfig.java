package com.clsaa.janus.admin.entity.po;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * 客户端到网关的请求配置信息持久层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class RequestConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * ApiId,t_api.id
     */
    private String apiId;
    /**
     * Http方法,1为GET,2为POST,3为PUT,4为DELETE,5为PATCH,6为HEAD
     */
    private Integer httpMethod;
    /**
     * 入参请求模式,1为映射,2为透传
     */
    private Integer mode;
    /**
     * 请求路径
     */
    private String path;
    /**
     * 支持协议,大写且用逗号分隔
     */
    private String protocol;
    /**
     * 双向通信API类别,1为普通,2为注册,3为注销,4为下行通知
     */
    private Integer wsType;
    /**
     * Body格式,1为FORM,2为STREAM
     */
    private Integer bodyFormat;
    /**
     * Body描述
     */
    private String bodyDescription;

    public RequestConfig() {
    }

    public RequestConfig(String id, String apiId, Integer httpMethod, Integer mode, String path, String protocol, Integer wsType, Integer bodyFormat, String bodyDescription) {
        this.id = id;
        this.apiId = apiId;
        this.httpMethod = httpMethod;
        this.mode = mode;
        this.path = path;
        this.protocol = protocol;
        this.wsType = wsType;
        this.bodyFormat = bodyFormat;
        this.bodyDescription = bodyDescription;
    }

    public RequestConfig(String id, Integer httpMethod, Integer mode, String path, String protocol, Integer wsType, Integer bodyFormat, String bodyDescription) {
        this.id = id;
        this.httpMethod = httpMethod;
        this.mode = mode;
        this.path = path;
        this.protocol = protocol;
        this.wsType = wsType;
        this.bodyFormat = bodyFormat;
        this.bodyDescription = bodyDescription;
    }
}
