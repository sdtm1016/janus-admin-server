package com.clsaa.janus.admin.entity.po;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * 限流策略持久层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class TrafficLimit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 地域id,t_region.id
     */
    private String regionId;
    /**
     * 名称
     */
    private String name;
    /**
     * 时间单位,1为秒,2为分钟,3为小时
     */
    private Integer unit;
    /**
     * 描述
     */
    private String description;
    /**
     * api流量限制,单位次
     */
    private Integer apiLimit;
    /**
     * 客户端流量限制,单位次
     */
    private Integer appLimit;
}
