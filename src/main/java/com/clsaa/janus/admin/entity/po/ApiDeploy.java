package com.clsaa.janus.admin.entity.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * API部署信息持久层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class ApiDeploy implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int STATUS_ONLINE = 1;
    public static final int STATUS_OFFLINE = 0;

    /**
     * 主键id
     */
    private String id;
    /**
     * APIid,t_api.id
     */
    private String apiId;
    /**
     * 环境id,t_environment.id
     */
    private String environmentId;
    /**
     * API快照id,t_snap_api.id
     */
    private String snapApiId;
    /**
     * 版本号
     */
    private String version;
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
     * 状态,0为下线,1为上线
     */
    private Integer status;

    public ApiDeploy() {
    }

    public ApiDeploy(String id, String apiId, String environmentId, String snapApiId, String version, String description, Timestamp ctime, String cuser, Timestamp mtime, String muser, Integer status) {
        this.id = id;
        this.apiId = apiId;
        this.environmentId = environmentId;
        this.snapApiId = snapApiId;
        this.version = version;
        this.description = description;
        this.ctime = ctime;
        this.cuser = cuser;
        this.mtime = mtime;
        this.muser = muser;
        this.status = status;
    }
}
