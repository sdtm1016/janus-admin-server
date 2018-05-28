package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.ApiRule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * 创建API规则
     *
     * @param apiRule {@link ApiRule}
     * @return 影响记录数
     */
    int add(ApiRule apiRule);

    /**
     * 根据ApiId,环境id,规则类型删除API规则
     *
     * @param apiId         APIid
     * @param environmentId 环境id
     * @param type          规则类型
     * @return 影响记录数
     */
    int delByApiIdAndEnvIdAndType(@Param("apiId") String apiId,
                                  @Param("environmentId") String environmentId,
                                  @Param("type") Integer type);

    /**
     * 根据ApiId,环境id,规则类型查询API规则
     *
     * @param apiId         APIid
     * @param environmentId 环境id
     * @param type          规则类型
     * @return {@link ApiRule}
     */
    ApiRule getByApiIdAndEnvIdAndType(@Param("apiId") String apiId,
                                      @Param("environmentId") String environmentId,
                                      @Param("type") Integer type);


    /**
     * 查询分页数据总量
     *
     * @param ruleId 规则id
     * @return 分页数据总量
     */
    int getPaginationCount(@Param("ruleId") String ruleId);

    /**
     * 查询分页数据
     *
     * @param ruleId    规则id
     * @param rowOffset 偏移量
     * @param pageSize  页大小
     * @return {@link List<ApiRule>}
     */
    List<ApiRule> getPaginationList(@Param("ruleId") String ruleId,
                                    @Param("rowOffset") Integer rowOffset,
                                    @Param("pageSize") Integer pageSize);
}
