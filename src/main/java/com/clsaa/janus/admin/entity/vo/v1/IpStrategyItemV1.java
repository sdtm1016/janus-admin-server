package com.clsaa.janus.admin.entity.vo.v1;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * IP控制策略项视图层对象,包含具体要控制的IP信息
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class IpStrategyItemV1 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 分组名称
     */
    private String name;
    /**
     * 适用的IP或IP段,多个用分号分隔
     */
    private String cidrIp;
    /**
     * 创建时间
     */
    private Timestamp ctime;
    /**
     * 修改时间
     */
    private Timestamp mtime;
}
