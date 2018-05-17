package com.clsaa.janus.admin.entity.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * API分组信息持久层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class Group implements Serializable {

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
     * 分组名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 子域地址
     */
    private String subDomain;
    /**
     * 流量限制,既QPS,默认为500
     */
    private Integer trafficLimit;
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
    /**
     * 状态,0为已删除,1为可用
     */
    private Integer status;
}
