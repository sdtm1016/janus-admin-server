package com.clsaa.janus.admin.entity.po;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 地域信息持久层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 所在城市
     */
    private String local;
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

}
