package com.clsaa.janus.admin.entity.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * IP控制策略信息持久层对象,主要包含IP控制策略基本信息及控制类型
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class IpStrategy implements Serializable {

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
     * 名称
     */
    private String name;
    /**
     * 类型,1为白名单,2为黑名单
     */
    private Integer type;
    /**
     * 描述
     */
    private String description;
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

    public IpStrategy() {
    }

    public IpStrategy(String id, String regionId, String name, Integer type, String description, Timestamp ctime, String cuser, Timestamp mtime, String muser) {
        this.id = id;
        this.regionId = regionId;
        this.name = name;
        this.type = type;
        this.description = description;
        this.ctime = ctime;
        this.cuser = cuser;
        this.mtime = mtime;
        this.muser = muser;
    }
}
