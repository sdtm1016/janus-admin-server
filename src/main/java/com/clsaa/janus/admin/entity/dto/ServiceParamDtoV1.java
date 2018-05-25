package com.clsaa.janus.admin.entity.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * 网关请求到服务端的参数传输层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class ServiceParamDtoV1 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求入参请求入参参数名
     */

    private String requestParamName;
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
