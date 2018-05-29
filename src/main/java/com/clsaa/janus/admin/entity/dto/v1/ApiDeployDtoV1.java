package com.clsaa.janus.admin.entity.dto.v1;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * API部署信息传输层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class ApiDeployDtoV1 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * APIid,t_api.id
     */
    private String apiId;
    /**
     * 环境id,t_environment.id
     */
    private String environmentId;
    /**
     * 描述
     */
    private String description;
}
