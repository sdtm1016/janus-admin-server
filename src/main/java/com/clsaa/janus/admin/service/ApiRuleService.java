package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.dao.ApiRuleDao;
import com.clsaa.janus.admin.entity.po.Api;
import com.clsaa.janus.admin.entity.po.ApiRule;
import com.clsaa.janus.admin.entity.vo.v1.ApiItemV1;
import com.clsaa.janus.admin.entity.vo.v1.ApiRuleV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.result.Pagination;
import com.clsaa.janus.admin.util.BeanUtils;
import com.clsaa.janus.admin.util.TimestampUtil;
import com.clsaa.janus.admin.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * API规则信息 服务实现类
 * </p>
 *
 * @author 任贵杰
 * @since 2018/5/17
 */
@Service
public class ApiRuleService {
    @Autowired
    private ApiRuleDao apiRuleDao;
    @Autowired
    private ApiService apiService;

    /**
     * 批量创建API规则
     *
     * @param loginUserId   登录用户id
     * @param apiIdList     APIid列表
     * @param environmentId 环境id
     * @param ruleId        规则id
     * @param type          类型
     * @return 是否创建成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addApiRuleBatch(String loginUserId, String ruleId, String environmentId, Integer type, List<String> apiIdList) {
        for (String apiId : apiIdList) {
            ApiRule apiRule = new ApiRule(UUIDUtil.getUUID(), apiId, ruleId, environmentId, type, TimestampUtil.now(), loginUserId);
            try {
                this.apiRuleDao.delByApiIdAndEnvIdAndType(apiId, environmentId, type);
            } catch (Exception e) {
                BizAssert.pass(false, BizCodes.ERROR_INSERT);
            }
            int count = 0;
            try {
                count = this.apiRuleDao.add(apiRule);
            } catch (Exception e) {
                BizAssert.pass(count == 1, BizCodes.ERROR_INSERT);
            }
            BizAssert.pass(count == 1, BizCodes.ERROR_INSERT);
        }

        return true;
    }

    /**
     * 根据APIid,环境id,规则类型查询API规则
     *
     * @param apiId         APIid
     * @param environmentId 环境id
     * @param type          规则类型
     * @return {@link ApiRuleV1}
     */
    public ApiRuleV1 getApiRuleV1ListByApiIdAndEnvIdAndType(String apiId, String environmentId, Integer type) {
        ApiRule apiRule = this.apiRuleDao.getByApiIdAndEnvIdAndType(apiId, environmentId, type);
        return BeanUtils.convertType(apiRule, ApiRuleV1.class);
    }

    /**
     * 根据APIid,环境id,规则类型删除API规则
     *
     * @param loginUserId 登录用户id
     * @param apiId       APIid
     * @param envId       环境id
     * @param type        规则类型
     * @return 是否删除成功
     */
    public boolean delByApiIdAndEnvIdAndType(String loginUserId, String apiId, String envId, Integer type) {
        int count = 0;
        try {
            count = this.apiRuleDao.delByApiIdAndEnvIdAndType(apiId, envId, type);
        } catch (Exception e) {
            BizAssert.pass(count == 1, BizCodes.ERROR_DELETE);
        }
        BizAssert.pass(count == 1, BizCodes.ERROR_DELETE);
        return true;
    }

    /**
     * 分页查询API规则信息
     *
     * @param loginUserId 登录用户id
     * @param ruleId      规则id
     * @param pageNo      页号
     * @param pageSize    页大小
     * @return {@link Pagination<ApiItemV1>}
     */
    public Pagination<ApiItemV1> getApiItemV1Pagination(String loginUserId, String ruleId, Integer pageNo, Integer pageSize) {
        int count = this.apiRuleDao.getPaginationCount(ruleId);
        Pagination<ApiItemV1> pagination = new Pagination<>();
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        pagination.setTotalCount(count);
        if (count == 0) {
            pagination.setPageList(Collections.emptyList());
            return pagination;
        }
        List<ApiRule> apiRuleList = this.apiRuleDao.getPaginationList(ruleId, pagination.getRowOffset(), pagination.getPageSize());
        List<ApiItemV1> apiItemV1List = new ArrayList<>();
        for (ApiRule apiRule : apiRuleList) {
            Api api = this.apiService.getApiById(apiRule.getApiId());
            ApiItemV1 apiItemV1 = new ApiItemV1();
            apiItemV1.setApiId(api.getId());
            apiItemV1.setRegionId(api.getRegionId());
            apiItemV1.setGroupId(api.getGroupId());
            apiItemV1.setEnvironmentId(apiRule.getEnvironmentId());
            apiItemV1.setName(api.getName());
            apiItemV1.setVisibility(api.getVisibility());
            apiItemV1.setCtime(api.getCtime());
            apiItemV1.setMtime(api.getMtime());
            apiItemV1List.add(apiItemV1);
        }
        pagination.setPageList(apiItemV1List);
        return pagination;
    }
}
