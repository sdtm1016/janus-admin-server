package com.clsaa.janus.admin.entity.po;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;


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
    /**
     * 创建时间
     */
    private Timestamp ctime;
    /**
     * 创建人
     */
    private String cuser;
    /**
     * 修改时间
     */
    private Timestamp mtime;
    /**
     * 修改人
     */
    private String muser;

    public TrafficLimit() {
    }

    public TrafficLimit(String id, String regionId, String name, Integer unit, String description, Integer apiLimit, Integer appLimit, Timestamp ctime, String cuser, Timestamp mtime, String muser) {
        this.id = id;
        this.regionId = regionId;
        this.name = name;
        this.unit = unit;
        this.description = description;
        this.apiLimit = apiLimit;
        this.appLimit = appLimit;
        this.ctime = ctime;
        this.cuser = cuser;
        this.mtime = mtime;
        this.muser = muser;
    }
}
