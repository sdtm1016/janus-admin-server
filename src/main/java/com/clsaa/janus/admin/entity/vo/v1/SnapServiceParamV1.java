package com.clsaa.janus.admin.entity.vo.v1;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * 网关请求到服务端的参数快照视图层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class SnapServiceParamV1 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 请求入参id,t_request_param.id
     */
    private String requestParamId;
    /**
     * 参数名
     */
    private String name;
    /**
     * 参数位置,1为path,2为query,3为head,4为body
     */
    private Integer location;
    /**
     * 参数顺序
     */
    private Integer sort;
}
