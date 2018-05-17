package com.clsaa.janus.admin.entity.po;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/17
 */
@Getter
@Setter
public class ApiRule {
    /**
     * 主键
     */
    private String id;
    /**
     * APIid,t_api.id
     */
    private String apiId;
    /**
     * 规则id,t_ip_strategy.id或t_traffic_limit.id,t_auth.id等附加在api上的规则
     */
    private String ruleId;
    /**
     * 环境id,t_environment.id
     */
    private String environmentId;
    /**
     * 类型,1为ip访问控制,2为流量限制,3为权限控制
     */
    private Integer type;
    /**
     * 创建时间
     */
    private Timestamp ctime;
    /**
     * 创建人
     */
    private String cuser;
}
