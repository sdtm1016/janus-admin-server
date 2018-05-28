package com.clsaa.janus.admin.entity.dto.v1;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * IP控制策略项传输层对象,包含具体要控制的IP信息
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class IpStrategyItemDtoV1 implements Serializable {

    private static final long serialVersionUID = 1L;

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
}
