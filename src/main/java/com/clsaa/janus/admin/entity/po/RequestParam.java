package com.clsaa.janus.admin.entity.po;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 客户端到网关的请求参数持久层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class RequestParam implements Serializable {

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
     * 参数名
     */
    private String name;
    /**
     * 参数位置,1为path,2为query,3为head,4为body
     */
    private Integer location;
    /**
     * 参数描述
     */
    private String description;
    /**
     * 参数类型,1为String,2为Int,3为Long,4为Float,5为Double,6为Boolean
     */
    private Integer type;
    /**
     * 是否必填,false为非必填,true为必填
     */
    private Boolean required;
    /**
     * 参数顺序
     */
    private Integer sort;
}
