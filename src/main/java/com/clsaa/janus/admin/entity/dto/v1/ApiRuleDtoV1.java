package com.clsaa.janus.admin.entity.dto.v1;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * API规则传输层对象
 *
 * @author 任贵杰
 * @since 2018/5/17
 */
@Getter
@Setter
public class ApiRuleDtoV1 implements Serializable {

    private static final long serialVersionUID = 1L;
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
     * APIid列表,t_api.id
     */
    private List<String> apiIdList;
}
