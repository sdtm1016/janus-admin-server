package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.constant.common.XHeaders;
import com.clsaa.janus.admin.entity.dto.v1.TrafficLimitDtoV1;
import com.clsaa.janus.admin.service.TrafficLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * <p>
 * 限流策略 接口实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class TrafficLimitController {
    @Autowired
    private TrafficLimitService trafficLimitService;

    /**
     * 创建限流策略
     *
     * @param loginUserId       登录用户id
     * @param trafficLimitDtoV1 {@link TrafficLimitDtoV1}
     * @return 创建的限流策略Id
     * @summary 创建限流策略
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-25
     */
    @PostMapping(value = "/v1/trafficLimit")
    public Mono<String> addTrafficLimitV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                          @RequestBody TrafficLimitDtoV1 trafficLimitDtoV1) {
        return Mono.create(sink -> sink.success(this.trafficLimitService.addTrafficLimit(trafficLimitDtoV1.getRegionId(),
                trafficLimitDtoV1.getName(), trafficLimitDtoV1.getDescription(), trafficLimitDtoV1.getUnit(),
                trafficLimitDtoV1.getApiLimit(), trafficLimitDtoV1.getAppLimit())));
    }

}
