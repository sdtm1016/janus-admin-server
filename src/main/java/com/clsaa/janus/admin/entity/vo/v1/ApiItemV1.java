package com.clsaa.janus.admin.entity.vo.v1;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/28
 */
@Getter
@Setter
public class ApiItemV1 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * APIid
     */
    private String apiId;
    /**
     * 地域id
     */
    private String regionId;
    /**
     * 分组id
     */
    private String groupId;
    /**
     * 环境id
     */
    private String environmentId;
    /**
     * 名称
     */
    private String name;
    /**
     * 可见性,1为公开,2为私有
     */
    private Integer visibility;
    /**
     * 创建时间
     */
    private Timestamp ctime;
    /**
     * 修改时间
     */
    private Timestamp mtime;

}
