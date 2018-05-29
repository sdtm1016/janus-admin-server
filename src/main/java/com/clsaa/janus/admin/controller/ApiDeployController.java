package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.constant.common.XHeaders;
import com.clsaa.janus.admin.entity.dto.v1.ApiDeployDtoV1;
import com.clsaa.janus.admin.entity.vo.v1.ApiDeployV1;
import com.clsaa.janus.admin.result.Pagination;
import com.clsaa.janus.admin.service.ApiDeployService;
import com.clsaa.janus.admin.validator.dto.ApiDeployDtoV1Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * <p>
 * API部署信息 接口实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class ApiDeployController {
    @Autowired
    private ApiDeployService apiDeployService;
    @Autowired
    private ApiDeployDtoV1Validator apiDeployDtoV1Validator;

    @InitBinder
    public void initApiDeployDtoV1ValidatorBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(apiDeployDtoV1Validator);
    }

    /**
     * 创建API部署信息
     *
     * @param loginUserId    登录用户id
     * @param apiDeployDtoV1 {@link ApiDeployDtoV1}
     * @return 创建的API部署信息id
     * @summary 创建API部署信息
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-29
     */
    @PostMapping(value = "/v1/api/deploy")
    public Mono<String> addApiDeployV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                       @Validated @RequestBody ApiDeployDtoV1 apiDeployDtoV1) {
        return Mono.create(sink -> sink.success(this.apiDeployService.addApiDeploy(loginUserId,
                apiDeployDtoV1.getApiId(), apiDeployDtoV1.getEnvironmentId(), apiDeployDtoV1.getDescription())));
    }

    /**
     * 切换到某个API部署版本
     *
     * @param loginUserId 登录用户id
     * @param apiDeployId API部署id
     * @return 是否切换成功
     * @summary 切换到某个API部署版本
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-29
     */
    @PutMapping(value = "/v1/api/deploy/{apiDeployId}/toOnline")
    public Mono<Boolean> updateApiDeployToOnline(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                                 @PathVariable(value = "apiDeployId") String apiDeployId,
                                                 @Validated @RequestBody ApiDeployDtoV1 apiDeployDtoV1) {
        return Mono.create(sink -> sink.success(this.apiDeployService.updateApiDeployToOnline(loginUserId, apiDeployId,
                apiDeployDtoV1.getApiId(), apiDeployDtoV1.getEnvironmentId(), apiDeployDtoV1.getDescription())));
    }

    /**
     * 将API在某个环境下线
     *
     * @param loginUserId   登录用户id
     * @param apiId         APIid
     * @param environmentId 环境id
     * @return 是否下线成功
     * @summary 将API在某个环境下线
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-29
     */
    @PutMapping(value = "/v1/api/{apiId}/deploy/toOffline")
    public Mono<Boolean> updateApiDeployToOffline(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                                  @PathVariable(value = "apiId") String apiId,
                                                  @RequestParam(value = "environmentId") String environmentId) {
        return Mono.create(sink -> sink.success(this.apiDeployService.updateApiDeployToOffline(loginUserId, apiId, environmentId)));
    }

    /**
     * 分页查询API部署信息
     *
     * @param loginUserId 登录用户id
     * @param apiId       APIid
     * @param pageNo      页号
     * @param pageSize    页大小
     * @return {@link Mono<Pagination<ApiDeployV1>>}
     * @summary 分页查询API部署信息
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-29
     */
    @GetMapping(value = "/v1/api/{apiId}/deploy/pagination")
    public Mono<Pagination<ApiDeployV1>> getApiDeployPaginationV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                                                  @PathVariable(value = "apiId") String apiId,
                                                                  @RequestParam(value = "environmentId", required = false) String environmentId,
                                                                  @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return Mono.create(sink -> sink.success(this.apiDeployService.getApiDeployV1Pagination(loginUserId, apiId, environmentId, pageNo, pageSize)));
    }
}
