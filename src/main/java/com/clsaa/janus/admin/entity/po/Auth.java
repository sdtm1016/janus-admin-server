package com.clsaa.janus.admin.entity.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * API认证信息持久层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class Auth implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int STATUS_OK = 1;
    public static final int STATUS_BANNED = 2;

    /**
     * 主键
     */
    private String id;
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
    /**
     * 状态,1为可用,2为禁用
     */
    private Integer status;

    public Auth() {
    }

    public Auth(String id, String regionId, String accessKey, String accessSecret, String name, String description, Timestamp ctime, String cuser, Timestamp mtime, String muser, Integer status) {
        this.id = id;
        this.regionId = regionId;
        this.accessKey = accessKey;
        this.accessSecret = accessSecret;
        this.name = name;
        this.description = description;
        this.ctime = ctime;
        this.cuser = cuser;
        this.mtime = mtime;
        this.muser = muser;
        this.status = status;
    }
}
