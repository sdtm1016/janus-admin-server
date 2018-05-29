package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.constant.common.XHeaders;
import com.clsaa.janus.admin.entity.dto.v1.ApiDtoV1;
import com.clsaa.janus.admin.entity.vo.v1.ApiV1;
import com.clsaa.janus.admin.result.Pagination;
import com.clsaa.janus.admin.service.ApiService;
import com.clsaa.janus.admin.validator.dto.ApiDtoV1Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * <p>
 * API信息 接口实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class ApiController {
    @Autowired
    private ApiService apiService;
    @Autowired
    private ApiDtoV1Validator apiDtoV1Validator;
    @InitBinder(value = "apiDtoV1")
    public void initApiDtoV1ValidatorBinder(WebDataBinder webDataBinder){
        webDataBinder.setValidator(this.apiDtoV1Validator);
    }

    /**
     * 创建API
     *
     * @param loginUserId 登录用户id
     * @param apiDtoV1    {@link ApiDtoV1}
     * @return 创建的APIid
     * @summary 创建API
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-24
     */
    @PostMapping(value = "/v1/api")
    public Mono<String> addApiV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                 @Validated @RequestBody ApiDtoV1 apiDtoV1) {
        return Mono.create(monoSink -> monoSink.success(this.apiService.addApi(loginUserId, apiDtoV1)));
    }

    /**
     * 删除API
     *
     * @param loginUserId 登录用户id
     * @param apiId       要删除API的id
     * @return 是否删除成功
     * @summary 删除API
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-24
     */
    @DeleteMapping("/v1/api/{apiId}")
    public Mono<Boolean> delApiByIdV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                      @PathVariable(value = "apiId") String apiId) {
        return Mono.create(sink -> sink.success(this.apiService.delApiById(loginUserId, apiId)));
    }

    /**
     * 修改API
     *
     * @param loginUserId 登录用户id
     * @param apiDtoV1    {@link ApiDtoV1}
     * @return 是否修改成功
     * @summary 修改API
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-24
     */
    @PutMapping(value = "/v1/api/{apiId}")
    public Mono<Boolean> updateApiV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                     @PathVariable(value = "apiId") String apiId,
                                     @Validated @RequestBody ApiDtoV1 apiDtoV1) {
        return Mono.create(sink -> sink.success(this.apiService.updateApi(loginUserId, apiId, apiDtoV1)));
    }

    /**
     * 根据id查询API详情
     *
     * @param loginUserId 登录用户id
     * @param apiId       APIId
     * @return 是否修改成功
     * @summary 根据id查询API详情
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-24
     */
    @GetMapping(value = "/v1/api/{apiId}")
    public Mono<ApiV1> getApiByIdV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                    @PathVariable(value = "apiId") String apiId) {
        return Mono.create(sink -> sink.success(this.apiService.getApiV1ById(apiId)));
    }

    /**
     * 分页查询API
     *
     * @param loginUserId 登录用户id
     * @param regionId    地域id
     * @param groupId     分组id
     * @param keyword     关键词
     * @param pageNo      页号
     * @param pageSize    页大小
     * @return {@link Mono<Pagination<ApiV1>>}
     * @summary 分页查询API
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-24
     */
    @GetMapping(value = "/v1/api/pagination")
    public Mono<Pagination<ApiV1>> getPaginationV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                                   @RequestParam(value = "regionId") String regionId,
                                                   @RequestParam(value = "groupId", required = false) String groupId,
                                                   @RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return Mono.create(sink -> sink.success(this.apiService.getApiV1Pagination(loginUserId, regionId, groupId, keyword, pageNo, pageSize)));
    }
}
