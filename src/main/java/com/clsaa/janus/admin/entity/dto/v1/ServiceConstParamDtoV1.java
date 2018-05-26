package com.clsaa.janus.admin.entity.dto.v1;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * 网关请求到服务端的常量参数传输层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class ServiceConstParamDtoV1 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 参数名
     */
    private String name;
    /**
     * 参数值
     */
    private String value;
    /**
     * 参数位置
     */
    private Integer location;
    /**
     * 参数说明
     */
    private String description;
    /**
     * 参数顺序
     */
    private Integer sort;
}
