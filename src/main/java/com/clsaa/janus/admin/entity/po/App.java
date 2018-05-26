package com.clsaa.janus.admin.entity.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 应用信息持久层对象,包含前端请求API网关的认证信息AccessKey和AccessSecret
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class App implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
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
    private String description;
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

    public App() {
    }

    public App(String id, String accessKey, String accessSecret, String name, String description, Timestamp ctime, String cuser, Timestamp mtime, String muser) {
        this.id = id;
        this.accessKey = accessKey;
        this.accessSecret = accessSecret;
        this.name = name;
        this.description = description;
        this.ctime = ctime;
        this.cuser = cuser;
        this.mtime = mtime;
        this.muser = muser;
    }
}
