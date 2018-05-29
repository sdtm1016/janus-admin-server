package com.clsaa.janus.admin.entity.po;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 服务端可能抛出的错误码快照持久层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class SnapServiceErrorCode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * API快照id,t_snap_api.id
     */
    private String snapApiId;
    /**
     * 错误码
     */
    private String code;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序,在文档中的位置
     */
    private Integer sort;
}
