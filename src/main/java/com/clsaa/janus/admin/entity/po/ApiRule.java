package com.clsaa.janus.admin.entity.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * API规则持久层对象
 *
 * @author 任贵杰
 * @since 2018/5/17
 */
@Getter
@Setter
public class ApiRule implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 类型,1为IP访问策略,2为流量限制,3为后端认证信息
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

    public ApiRule(String id, String apiId, String ruleId, String environmentId, Integer type, Timestamp ctime, String cuser) {
        this.id = id;
        this.apiId = apiId;
        this.ruleId = ruleId;
        this.environmentId = environmentId;
        this.type = type;
        this.ctime = ctime;
        this.cuser = cuser;
    }
}
