package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.ApiRule;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * API规则信息 Mapper 接口
 * </p>
 *
 * @author 任贵杰
 * @since 2018/5/17
 */
public interface ApiRuleDao {
    /**
     * 根据id获取API规则
     *
     * @param id 主键id
     * @return {@link ApiRule}
     */
    ApiRule getById(@Param("id") String id);
}
