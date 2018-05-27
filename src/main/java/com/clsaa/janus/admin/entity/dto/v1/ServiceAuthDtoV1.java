package com.clsaa.janus.admin.entity.dto.v1;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * API后端服务认证信息传输层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-25
 */
@Getter
@Setter
public class ServiceAuthDtoV1 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地域id,t_region.id
     */
    private String regionId;
    /**
     * 用于权限认证的AK
     */
    private String accessKey;
    /**
     * 用于权限认证的AS
     */
    private String accessSecret;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description = "";
}
