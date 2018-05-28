package com.clsaa.janus.admin.entity.po;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * IP控制策略项持久层对象,包含具体要控制的IP信息
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class IpStrategyItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * IP访问策略id,t_ip_access.id
     */
    private String ipStrategyId;
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

    public IpStrategyItem() {
    }

    public IpStrategyItem(String id, String ipStrategyId, String name, String cidrIp, Timestamp ctime, String cuser, Timestamp mtime, String muser) {
        this.id = id;
        this.ipStrategyId = ipStrategyId;
        this.name = name;
        this.cidrIp = cidrIp;
        this.ctime = ctime;
        this.cuser = cuser;
        this.mtime = mtime;
        this.muser = muser;
    }
}
