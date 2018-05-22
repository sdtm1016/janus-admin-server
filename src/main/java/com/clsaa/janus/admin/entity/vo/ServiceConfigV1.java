package com.clsaa.janus.admin.entity.vo;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * 网关到服务端的请求配置信息视图层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class ServiceConfigV1 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 后端服务类型,1为HTTP/HTTPS,2为函数计算
     */
    private Integer type;
    /**
     * ContentType分类,1为透传,2为网关默认,3为自定义
     */
    private Integer contentTypeCategory;
    /**
     * ContentType值
     */
    private String contentTypeValue;
    /**
     * 后端服务地址
     */
    private String address;
    /**
     * http方法
     */
    private Integer httpMethod;
    /**
     * 请求路径
     */
    private String path;
    /**
     * 超时时间,单位毫秒
     */
    private Long timeout;
    /**
     * mock开关
     */
    private Boolean mock;
    /**
     * mock数据
     */
    private String mockResult;
}
