package com.clsaa.janus.admin.entity.vo.v1;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * IP控制策略信息视图层对象,主要包含IP控制策略基本信息及控制类型
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class IpStrategyV1 implements Serializable {

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
     * 修改时间
     */
    private Timestamp mtime;
}
