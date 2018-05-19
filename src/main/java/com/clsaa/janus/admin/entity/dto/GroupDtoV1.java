package com.clsaa.janus.admin.entity.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * API分组信息传输层对象
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Getter
@Setter
public class GroupDtoV1 implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 地域id,t_region.id
     */
    @Length(min = 32, max = 32, message = "地域id格式不符合要求")
    @NotEmpty(message = "地域id不能为空")
    private String regionId;
    /**
     * 分组名称
     */
    @Length(min = 4, max = 50, message = "分组名称长度不符合要求,请控制在4-50个字符")
    @Pattern(regexp = "^[^0-9]+$", message = "分组名称格式不符合要求,分组名称必须唯一，支持汉字、英文字母、数字、英文格式的下划线，必须以英文字母或汉字开头，4~50个字符")
    @NotEmpty(message = "分组名称不能为空")
    private String name;
    /**
     * 描述
     */
    @Length(max = 180, message = "分组名称长度不符合要求,请控制在0-180个字符")
    private String description;
    /**
     * 子域地址
     */
    @NotEmpty(message = "子域地址不能为空")
    private String subDomain;
    /**
     * 流量限制,既QPS,默认为500
     */
    @Range(min = 1, max = 1000)
    @NotNull(message = "流量限制不能为空")
    private Integer trafficLimit;
}
