package com.clsaa.janus.admin.entity.po;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * 网关到服务端的请求配置信息持久层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class ServiceConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * APIid,t_api.id
     */
    private String apiId;
    /**
     * 后端服务地址
     */
    private String address;
    /**
     * http方法
     */
    private String httpMethod;
    /**
     * 请求路径
     */
    private String path;
    /**
     * 协议
     */
    private String protocol;
    /**
     * 超时时间,单位毫秒
     */
    private Long timeout;
    /**
     * mock开关
     */
    private Integer mock;
    /**
     * mock数据
     */
    private String mockResult;
}
