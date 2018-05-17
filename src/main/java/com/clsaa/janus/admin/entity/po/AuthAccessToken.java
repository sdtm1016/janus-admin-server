package com.clsaa.janus.admin.entity.po;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Oauth2认证,AccessToken持久层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class AuthAccessToken implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 认证客户端id,t_auth.id
     */
    private String authId;
    /**
     * Oauth2协议token
     */
    private String accessToken;
    /**
     * 失效时间
     */
    private Timestamp expires;
    /**
     * 创建时间
     */
    private Timestamp ctime;
    /**
     * 修改时间
     */
    private Timestamp mtime;
    /**
     * 状态,0为禁用,1为可用
     */
    private Integer status;
}
