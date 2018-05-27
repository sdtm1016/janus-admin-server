package com.clsaa.janus.admin.entity.dto.v1;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 应用信息传输层对象,包含前端请求API网关的认证信息AccessKey和AccessSecret
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class AppDtoV1 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description = "";
}
