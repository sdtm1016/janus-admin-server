package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.dao.ApiRuleDao;
import com.clsaa.janus.admin.entity.po.ApiRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/17
 */
@Service
public class ApiRuleService {
    @Autowired
    private ApiRuleDao apiRuleDao;

    public ApiRule getApiRuleById(String id) {
        return this.apiRuleDao.getById(id);
    }
}
