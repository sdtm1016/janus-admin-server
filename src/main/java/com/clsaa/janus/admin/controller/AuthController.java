package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.constant.common.XHeaders;
import com.clsaa.janus.admin.entity.dto.AuthDtoV1;
import com.clsaa.janus.admin.entity.vo.AuthV1;
import com.clsaa.janus.admin.result.Pagination;
import com.clsaa.janus.admin.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * <p>
 * API认证信息,包含AccessKey和AccessSecret 接口实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthService authService;

    /**
     * 创建认证信息
     *
     * @param loginUserId 登录用户id
     * @param authDtoV1   {@link AuthDtoV1}
     * @return 认证信息的id
     * @summary 创建认证信息
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-24
     */
    @PostMapping("/v1/auth")
    public Mono<String> addAuthV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                  @RequestBody AuthDtoV1 authDtoV1) {
        return Mono.create(sink -> sink.success(this.authService.addAuth(loginUserId, authDtoV1.getRegionId(),
                authDtoV1.getAccessKey(), authDtoV1.getAccessSecret(), authDtoV1.getName(), authDtoV1.getDescription())));
    }

    /**
     * 删除认证信息
     *
     * @param loginUserId 登录用户id
     * @param authId      认证信息id
     * @return 认证信息的id
     * @summary 创建认证信息
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-24
     */
    @DeleteMapping("/v1/auth/{authId}")
    public Mono<Boolean> delAuthByIdV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                       @PathVariable(value = "authId") String authId) {
        return Mono.create(sink -> sink.success(this.authService.delAuthById(loginUserId, authId)));
    }

    /**
     * 修改认证信息
     *
     * @param loginUserId 登录用户id
     * @param authId      认证信息id
     * @param authDtoV1   {@link AuthDtoV1}
     * @return 是否修改成功
     * @summary 修改认证信息
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-24
     */
    @PutMapping(value = "/v1/auth/{authId}")
    public Mono<Boolean> updateAuthByIdV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                          @PathVariable(value = "authId") String authId,
                                          @RequestBody AuthDtoV1 authDtoV1) {
        return Mono.create(sink -> sink.success(this.authService.updateAuth(loginUserId, authId, authDtoV1.getRegionId(),
                authDtoV1.getAccessKey(), authDtoV1.getAccessSecret(), authDtoV1.getName(), authDtoV1.getDescription())));
    }

    /**
     * 根据id查询认证信息
     *
     * @param loginUserId 登录用户id
     * @param authId      认证信息id
     * @return {@link Mono<AuthV1>}
     * @summary 根据id查询认证信息
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-24
     */
    @GetMapping(value = "/v1/auth/{authId}")
    public Mono<AuthV1> getAuthByIdV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                      @PathVariable(value = "authId") String authId) {
        return Mono.create(sink -> sink.success(this.authService.getAuthV1ById(loginUserId, authId)));
    }

    @GetMapping(value = "/v1/auth/pagination")
    public Mono<Pagination<AuthV1>> getPaginationV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                                    @RequestParam(value = "regionId") String regionId,
                                                    @RequestParam(value = "keyword", required = false) String keyword,
                                                    @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return Mono.create(sink -> sink.success(this.authService.getAuthV1Pagination(loginUserId, regionId, keyword, pageNo, pageSize)));
    }
}
