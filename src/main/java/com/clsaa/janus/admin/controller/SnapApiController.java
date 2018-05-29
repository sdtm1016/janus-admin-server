package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.constant.common.XHeaders;
import com.clsaa.janus.admin.entity.vo.v1.SnapApiV1;
import com.clsaa.janus.admin.service.SnapApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * <p>
 * API快照信息 接口实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-29
 */
@RestController
@CrossOrigin
public class SnapApiController {
    @Autowired
    private SnapApiService snapApiService;

    /**
     * 根据API快照id查询API快照信息
     *
     * @param loginUserId 登录用户id
     * @param snapApiId   API快照id
     * @return {@link Mono<SnapApiV1>}
     * @summary 根据API快照id查询API快照信息
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-29
     */
    @PostMapping(value = "/v1/api/snap/{snapApiId}")
    public Mono<SnapApiV1> getSnapApiByIdV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                            @PathVariable(value = "snapApiId") String snapApiId) {
        return Mono.create(sink -> sink.success(this.snapApiService.getSnapApiV1ById(loginUserId, snapApiId)));
    }
}
