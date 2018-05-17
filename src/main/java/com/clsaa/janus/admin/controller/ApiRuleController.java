package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.entity.po.ApiRule;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.service.ApiRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/17
 */
@RestController
@CrossOrigin
public class ApiRuleController {
    @Autowired
    private ApiRuleService apiRuleService;
}
