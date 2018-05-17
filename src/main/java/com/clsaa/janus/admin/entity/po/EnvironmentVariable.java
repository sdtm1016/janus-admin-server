package com.clsaa.janus.admin.entity.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 环境变量信息持久层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class EnvironmentVariable implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 环境id,t_environment.id
     */
    private String environmentId;
    /**
     * API分组id,t_group.id
     */
    private String groupId;
    /**
     * 变量名称
     */
    private String name;
    /**
     * 变量值
     */
    private String value;
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
     * 状态,0为已删除,1为可用
     */
    private Integer status;
}
