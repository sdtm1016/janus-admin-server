package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.constant.common.XHeaders;
import com.clsaa.janus.admin.entity.dto.v1.TrafficLimitDtoV1;
import com.clsaa.janus.admin.entity.vo.v1.TrafficLimitV1;
import com.clsaa.janus.admin.result.Pagination;
import com.clsaa.janus.admin.service.TrafficLimitService;
import com.clsaa.janus.admin.validator.dto.TrafficLimitDtoV1Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
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
    @Autowired
    private TrafficLimitDtoV1Validator trafficLimitDtoV1Validator;

    @InitBinder(value = "trafficLimitDtoV1")
    protected void initTrafficLimitDtoV1Validator(WebDataBinder binder) {
        binder.setValidator(trafficLimitDtoV1Validator);
    }

    /**
     * 创建限流策略
     *
     * @param loginUserId       登录用户id
     * @param trafficLimitDtoV1 {@link TrafficLimitDtoV1}
     * @return 创建的限流策略Id
     * @summary 创建限流策略
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-27
     */
    @PostMapping(value = "/v1/trafficLimit")
    public Mono<String> addTrafficLimitV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                          @Validated @RequestBody TrafficLimitDtoV1 trafficLimitDtoV1) {
        return Mono.create(sink -> sink.success(this.trafficLimitService.addTrafficLimit(loginUserId,
                trafficLimitDtoV1.getRegionId(), trafficLimitDtoV1.getName(), trafficLimitDtoV1.getDescription(),
                trafficLimitDtoV1.getUnit(), trafficLimitDtoV1.getApiLimit(), trafficLimitDtoV1.getAppLimit())));
    }

    /**
     * 删除限流策略
     *
     * @param loginUserId    登录用户id
     * @param trafficLimitId 限流策略id
     * @return 是否删除成功
     * @summary 删除限流策略
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-27
     */
    @DeleteMapping(value = "/v1/trafficLimit/{trafficLimitId}")
    public Mono<Boolean> delTrafficLimitByIdV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                               @PathVariable(value = "trafficLimitId") String trafficLimitId) {
        return Mono.create(sink -> sink.success(this.trafficLimitService.delTrafficLimitById(loginUserId, trafficLimitId)));
    }

    /**
     * 修改限流策略
     *
     * @param loginUserId       登录用户id
     * @param trafficLimitId    限流策略id
     * @param trafficLimitDtoV1 {@link TrafficLimitDtoV1}
     * @return 是否修改成功
     * @summary 修改限流策略
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-27
     */
    @PutMapping(value = "/v1/trafficLimit/{trafficLimitId}")
    public Mono<Boolean> updateTrafficLimitV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                              @PathVariable(value = "trafficLimitId") String trafficLimitId,
                                              @Validated @RequestBody TrafficLimitDtoV1 trafficLimitDtoV1) {
        return Mono.create(sink -> sink.success(this.trafficLimitService.updateTrafficLimit(loginUserId, trafficLimitId,
                trafficLimitDtoV1.getRegionId(), trafficLimitDtoV1.getName(), trafficLimitDtoV1.getDescription(),
                trafficLimitDtoV1.getUnit(), trafficLimitDtoV1.getApiLimit(), trafficLimitDtoV1.getAppLimit())));
    }

    /**
     * 根据id查询限流策略
     *
     * @param loginUserId    登录用户id
     * @param trafficLimitId 限流策略id
     * @return {@link Mono<TrafficLimitV1>}
     * @summary 根据id查询限流策略
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-27
     */
    @GetMapping(value = "/v1/trafficLimit/{trafficLimitId}")
    public Mono<TrafficLimitV1> getTrafficLimitByIdV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                                      @PathVariable(value = "trafficLimitId") String trafficLimitId) {
        return Mono.create(sink -> sink.success(this.trafficLimitService.getTrafficLimitV1ById(loginUserId, trafficLimitId)));
    }

    /**
     * 分页查询限流策略
     *
     * @param loginUserId 登录用户id
     * @param keyword     关键词
     * @param pageNo      页号
     * @param pageSize    页大小
     * @return {@link Mono<Pagination<TrafficLimitV1>>}
     * @summary 分页查询限流策略
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-27
     */
    @GetMapping(value = "/v1/trafficLimit/pagination")
    public Mono<Pagination<TrafficLimitV1>> getTrafficLimitPaginationV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                                                        @RequestParam(value = "keyword", required = false) String keyword,
                                                                        @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return Mono.create(sink -> sink.success(this.trafficLimitService.getTrafficLimitV1Pagination(loginUserId, keyword, pageNo, pageSize)));
    }
}
