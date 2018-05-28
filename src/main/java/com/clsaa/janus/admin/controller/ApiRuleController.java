package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.constant.common.XHeaders;
import com.clsaa.janus.admin.entity.dto.v1.ApiRuleDtoV1;
import com.clsaa.janus.admin.entity.vo.v1.ApiItemV1;
import com.clsaa.janus.admin.result.Pagination;
import com.clsaa.janus.admin.service.ApiRuleService;
import com.clsaa.janus.admin.validator.dto.ApiRuleDtoV1Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private ApiRuleDtoV1Validator apiRuleDtoV1Validator;

    @InitBinder
    protected void initApiRuleDtoV1ValidatorBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(apiRuleDtoV1Validator);
    }

    /**
     * 批量创建API规则
     *
     * @param loginUserId  登录用户id
     * @param apiRuleDtoV1 {@link ApiRuleDtoV1}
     * @return 是否添加成功
     * @summary 批量创建API规则
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-27
     */
    @PostMapping(value = "/v1/api/rule/batch")
    public Mono<Boolean> addApiRuleBatchV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                           @Validated @RequestBody ApiRuleDtoV1 apiRuleDtoV1) {
        return Mono.create(sink -> sink.success(this.apiRuleService.addApiRuleBatch(loginUserId, apiRuleDtoV1.getRuleId(),
                apiRuleDtoV1.getEnvironmentId(), apiRuleDtoV1.getType(), apiRuleDtoV1.getApiIdList())));
    }

    /**
     * 删除API规则
     *
     * @param loginUserId 登录用户id
     * @param apiId       APIid
     * @param envId       环境id
     * @param type        规则类型
     * @return 是否删除成功
     * @summary 删除API规则
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-27
     */
    @DeleteMapping(value = "/v1/api/rule")
    public Mono<Boolean> delApiRuleByApiIdAndEnvIdAndTypeV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                                            @RequestParam(value = "apiId") String apiId,
                                                            @RequestParam(value = "envId") String envId,
                                                            @RequestParam(value = "type") Integer type) {
        return Mono.create(sink -> sink.success(this.apiRuleService.delByApiIdAndEnvIdAndType(loginUserId, apiId, envId, type)));
    }

    /**
     * 根据API规则id查询
     *
     * @param loginUserId 登录用户id
     * @param ruleId      规则id
     * @param pageNo      页号
     * @param pageSize    页大小
     * @return {@link Mono<Pagination<ApiItemV1>>}
     */
    @GetMapping(value = "/v1/api/rule/{ruleId}/pagination")
    public Mono<Pagination<ApiItemV1>> getApiRulePaginationV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                                              @PathVariable(value = "ruleId") String ruleId,
                                                              @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return Mono.create(sink -> sink.success(this.apiRuleService.getApiItemV1Pagination(loginUserId, ruleId, pageNo, pageSize)));
    }

}
