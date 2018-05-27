package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.constant.common.XHeaders;
import com.clsaa.janus.admin.entity.dto.v1.ServiceAuthDtoV1;
import com.clsaa.janus.admin.entity.vo.v1.ServiceAuthV1;
import com.clsaa.janus.admin.result.Pagination;
import com.clsaa.janus.admin.service.ServiceAuthService;
import com.clsaa.janus.admin.validator.dto.ServiceAuthDtoV1Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * <p>
 * API后端服务认证信息,包含AccessKey和AccessSecret 接口实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class ServiceAuthController {
    @Autowired
    private ServiceAuthService serviceAuthService;
    @Autowired
    private ServiceAuthDtoV1Validator serviceAuthDtoV1Validator;

    @InitBinder("serviceAuthDtoV1")
    protected void initServiceAuthDtoV1ValidatorBinder(WebDataBinder binder) {
        binder.setValidator(serviceAuthDtoV1Validator);
    }

    /**
     * 创建API后端服务认证信息
     *
     * @param loginUserId      登录用户id
     * @param serviceAuthDtoV1 {@link ServiceAuthDtoV1}
     * @return API后端服务认证信息的id
     * @summary 创建API后端服务认证信息
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-24
     */
    @PostMapping(value = "/v1/serviceAuth")
    public Mono<String> addAuthV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                  @Validated @RequestBody ServiceAuthDtoV1 serviceAuthDtoV1) {
        return Mono.create(sink -> sink.success(this.serviceAuthService.addServiceAuth(loginUserId, serviceAuthDtoV1.getRegionId(),
                serviceAuthDtoV1.getAccessKey(), serviceAuthDtoV1.getAccessSecret(), serviceAuthDtoV1.getName(), serviceAuthDtoV1.getDescription())));
    }

    /**
     * 删除API后端服务认证信息
     *
     * @param loginUserId   登录用户id
     * @param serviceAuthId 后端服务认证信息id
     * @return 是否删除成功
     * @summary 删除后端服务认证信息
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-24
     */
    @DeleteMapping("/v1/serviceAuth/{serviceAuthId}")
    public Mono<Boolean> delAuthByIdV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                       @PathVariable(value = "serviceAuthId") String serviceAuthId) {
        return Mono.create(sink -> sink.success(this.serviceAuthService.delServiceAuthById(loginUserId, serviceAuthId)));
    }

    /**
     * 修改API后端服务认证信息
     *
     * @param loginUserId      登录用户id
     * @param authId           后端服务认证信息id
     * @param serviceAuthDtoV1 {@link ServiceAuthDtoV1}
     * @return 是否修改成功
     * @summary 修改API后端服务认证信息
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-24
     */
    @PutMapping(value = "/v1/serviceAuth/{authId}")
    public Mono<Boolean> updateAuthByIdV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                          @PathVariable(value = "serviceAuthId") String authId,
                                          @Validated @RequestBody ServiceAuthDtoV1 serviceAuthDtoV1) {
        return Mono.create(sink -> sink.success(this.serviceAuthService.updateServiceAuth(loginUserId, authId, serviceAuthDtoV1.getRegionId(),
                serviceAuthDtoV1.getAccessKey(), serviceAuthDtoV1.getAccessSecret(), serviceAuthDtoV1.getName(), serviceAuthDtoV1.getDescription())));
    }

    /**
     * 根据id查询API后端服务认证信息
     *
     * @param loginUserId 登录用户id
     * @param authId      后端服务认证信息id
     * @return {@link Mono< ServiceAuthV1 >}
     * @summary 根据id查询API后端服务认证信息
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-24
     */
    @GetMapping(value = "/v1/serviceAuth/{authId}")
    public Mono<ServiceAuthV1> getAuthByIdV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                             @PathVariable(value = "serviceAuthId") String authId) {
        return Mono.create(sink -> sink.success(this.serviceAuthService.getServiceAuthV1ById(loginUserId, authId)));
    }

    /**
     * 分页查询API后端服务认证信息
     *
     * @param loginUserId 登录用户id
     * @param regionId    地域id
     * @param keyword     关键词
     * @param pageNo      页号
     * @param pageSize    页大小
     * @return {@link Mono< ServiceAuthV1 >}
     * @summary 分页查询API后端服务认证信息
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-26
     */
    @GetMapping(value = "/v1/serviceAuth/pagination")
    public Mono<Pagination<ServiceAuthV1>> getPaginationV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                                           @RequestParam(value = "regionId") String regionId,
                                                           @RequestParam(value = "keyword", required = false) String keyword,
                                                           @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return Mono.create(sink -> sink.success(this.serviceAuthService.getServiceAuthV1Pagination(loginUserId, regionId, keyword, pageNo, pageSize)));
    }
}
