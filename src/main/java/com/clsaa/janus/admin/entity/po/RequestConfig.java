package com.clsaa.janus.admin.entity.po;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 客户端到网关的请求配置信息持久层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class RequestConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * ApiId,t_api.id
     */

    private String apiId;
    /**
     * Http方法
     */

    private String httpMethod;
    /**
     * 入参请求模式,1为映射,2为透传
     */
    private Integer mode;
    /**
     * 请求路径
     */
    private String path;
    /**
     * 支持协议,用逗号分隔
     */
    private String protocol;
    /**
     * 双向通信API类别
     */

    private Integer wsType;
    /**
     * Body格式
     */

    private String bodyFormat;
    /**
     * Body描述
     */

    private String bodyDescription;
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
