package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.constant.common.XHeaders;
import com.clsaa.janus.admin.entity.dto.v1.AppDtoV1;
import com.clsaa.janus.admin.entity.vo.v1.AppV1;
import com.clsaa.janus.admin.result.Pagination;
import com.clsaa.janus.admin.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * <p>
 * 应用信息,包含前端请求API网关的认证信息AccessKey和AccessSecret 接口实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class AppController {
    @Autowired
    private AppService appService;


    /**
     * 创建应用信息
     *
     * @param loginUserId 登录用户id
     * @param appDtoV1    {@link AppDtoV1}
     * @return 应用信息id
     * @summary 创建应用信息
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-27
     */
    @PostMapping(value = "/v1/app")
    public Mono<String> addAppV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                  @Validated @RequestBody AppDtoV1 appDtoV1) {
        return Mono.create(sink -> sink.success(appService.addApp(loginUserId, appDtoV1.getName(), appDtoV1.getDescription())));
    }

    /**
     * 删除应用信息
     *
     * @param loginUserId 登录用户id
     * @param appId       应用信息id
     * @return 是否删除成功
     * @summary 删除应用信息
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-27
     */
    @DeleteMapping("/v1/app/{appId}")
    public Mono<Boolean> delAuthByIdV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                       @PathVariable(value = "appId") String appId) {
        return Mono.create(sink -> sink.success(this.appService.delAppById(loginUserId, appId)));
    }

    /**
     * 修改应用信息
     *
     * @param loginUserId 登录用户id
     * @param appId       应用信息id
     * @param appDtoV1    {@link AppDtoV1}
     * @return 是否修改成功
     * @summary 修改应用信息
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-24
     */
    @PutMapping(value = "/v1/app/{appId}")
    public Mono<Boolean> updateAppByIdV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                         @PathVariable(value = "appId") String appId,
                                         @Validated @RequestBody AppDtoV1 appDtoV1) {
        return Mono.create(sink -> sink.success(this.appService.updateApp(loginUserId, appId, appDtoV1.getName(), appDtoV1.getDescription())));
    }

    /**
     * 更换应用AccessSecret
     *
     * @param loginUserId 登录用户id
     * @param appId       应用信息id
     * @return 是否更换成功
     * @summary 更换应用AccessSecret
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-24
     */
    @PutMapping(value = "/v1/app/{appId}")
    public Mono<Boolean> updateAppAccessSecretByIdV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                                     @PathVariable(value = "appId") String appId) {
        return Mono.create(sink -> sink.success(this.appService.updateAppAccessSecret(loginUserId, appId)));
    }

    /**
     * 根据id查询应用信息
     *
     * @param loginUserId 登录用户id
     * @param appId       应用信息id
     * @return {@link Mono<AppV1>}
     * @summary 根据id查询应用信息
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-24
     */
    @GetMapping(value = "/v1/app/{appId}")
    public Mono<AppV1> getAppByIdV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                    @PathVariable(value = "appId") String appId) {
        return Mono.create(sink -> sink.success(this.appService.getAppV1ById(loginUserId, appId)));
    }

    /**
     * 分页查询应用信息
     *
     * @param loginUserId 登录用户id
     * @param keyword     关键词
     * @param pageNo      页号
     * @param pageSize    页大小
     * @return {@link Mono<AppV1>}
     * @summary 分页查询应用信息
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-26
     */
    @GetMapping(value = "/v1/app/pagination")
    public Mono<Pagination<AppV1>> getPaginationV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                                   @RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return Mono.create(sink -> sink.success(this.appService.getAppV1Pagination(loginUserId, keyword, pageNo, pageSize)));
    }
}
