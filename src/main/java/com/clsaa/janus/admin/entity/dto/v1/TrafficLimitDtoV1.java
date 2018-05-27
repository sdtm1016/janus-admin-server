package com.clsaa.janus.admin.entity.dto.v1;


import com.clsaa.janus.admin.util.UUIDUtil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


/**
 * 限流策略传输层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class TrafficLimitDtoV1 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地域id,t_region.id
     */
    private String regionId;
    /**
     * 名称
     */
    private String name;
    /**
     * 时间单位,1为秒,2为分钟,3为小时
     */
    private Integer unit;
    /**
     * 描述
     */
    private String description;
    /**
     * api流量限制,单位次
     */
    private Integer apiLimit;
    /**
     * 客户端流量限制,单位次
     */
    private Integer appLimit;
}
