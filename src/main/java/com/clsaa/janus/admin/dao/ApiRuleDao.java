package com.clsaa.janus.admin.dao;

import com.clsaa.janus.admin.entity.po.ApiRule;
import org.apache.ibatis.annotations.Param;

/**
 * @author 任贵杰
 * @version v1
 * @summary ApiRuleDao
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
