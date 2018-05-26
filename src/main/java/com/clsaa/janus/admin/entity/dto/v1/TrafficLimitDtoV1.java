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
    @Pattern(regexp = "[A-Za-z0-9_]{32}", message = "地域id格式不符合要求")
    private String regionId;
    /**
     * 名称
     */
    @Pattern(regexp = "^[^0-9]{4,50}", message = "AccessKey名称格式不符合要求,分组名称必须唯一，支持汉字、英文字母、数字、英文格式的下划线，必须以英文字母或汉字开头，4~50个字符")
    private String name;
    /**
     * 时间单位,1为秒,2为分钟,3为小时
     */
    @Pattern(regexp = "^[1-3]", message = "AccessKey名称格式不符合要求,分组名称必须唯一，支持汉字、英文字母、数字、英文格式的下划线，必须以英文字母或汉字开头，4~50个字符")
    private Integer unit;
    /**
     * 描述
     */
    @Length(max = 180, message = "分组名称长度不符合要求,请控制在0-180个字符")
    private String description;
    /**
     * api流量限制,单位次
     */
    @Range(min = 1L,max = Integer.MAX_VALUE)
    @Nullable
    private Integer apiLimit;
    /**
     * 客户端流量限制,单位次
     */
    @Range(min = 1L,max = Integer.MAX_VALUE)
    @NotNull(message = "不能为空")
    private Integer appLimit;
}
