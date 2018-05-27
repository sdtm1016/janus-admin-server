package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.constant.common.XHeaders;
import com.clsaa.janus.admin.entity.dto.v1.IpStrategyDtoV1;
import com.clsaa.janus.admin.entity.vo.v1.IpStrategyV1;
import com.clsaa.janus.admin.result.Pagination;
import com.clsaa.janus.admin.service.IpStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * <p>
 * IP控制策略信息,主要包含IP控制策略基本信息及控制类型 接口实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class IpStrategyController {
    @Autowired
    private IpStrategyService ipStrategyService;

    /**
     * 创建IP访问策略
     *
     * @param loginUserId     登录用户id
     * @param ipStrategyDtoV1 {@link IpStrategyDtoV1}
     * @return 创建IP访问策略
     * @summary 创建IP访问策略
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-27
     */
    @PostMapping(value = "/v1/IpStrategy")
    public Mono<String> addIpStrategyV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                        @Validated @RequestBody IpStrategyDtoV1 ipStrategyDtoV1) {
        return Mono.create(sink -> sink.success(this.ipStrategyService.addIpStrategy(loginUserId, ipStrategyDtoV1.getRegionId(),
                ipStrategyDtoV1.getType(), ipStrategyDtoV1.getName(), ipStrategyDtoV1.getDescription())));
    }

    /**
     * 删除IP访问策略
     *
     * @param loginUserId  登录用户id
     * @param ipStrategyId IP访问策略id
     * @return 删除IP访问策略
     * @summary 删除IP访问策略
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-27
     */
    @DeleteMapping(value = "/v1/IpStrategy/{ipStrategyId}")
    public Mono<Boolean> delIpStrategyByIdV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                             @PathVariable(value = "ipStrategyId") String ipStrategyId) {
        return Mono.create(sink -> sink.success(this.ipStrategyService.delIpStrategyById(loginUserId, ipStrategyId)));
    }

    /**
     * 修改IP访问策略
     *
     * @param loginUserId     登录用户id
     * @param ipStrategyId    IP访问策略id
     * @param ipStrategyDtoV1 {@link IpStrategyDtoV1}
     * @return 是否修改成功
     * @summary 修改IP访问策略
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-27
     */
    @PutMapping(value = "/v1/IpStrategy/{ipStrategyId}")
    public Mono<Boolean> updateIpStrategyV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                            @PathVariable(value = "ipStrategyId") String ipStrategyId,
                                            @Validated @RequestBody IpStrategyDtoV1 ipStrategyDtoV1) {
        return Mono.create(sink -> sink.success(this.ipStrategyService.updateIpStrategy(loginUserId, ipStrategyId,
                ipStrategyDtoV1.getRegionId(), ipStrategyDtoV1.getType(), ipStrategyDtoV1.getName(), ipStrategyDtoV1.getDescription())));
    }

    /**
     * 根据id获取IP访问策略
     *
     * @param loginUserId  登录用户id
     * @param ipStrategyId IP访问策略id
     * @return {@link Mono<IpStrategyV1>}
     * @summary 根据id获取IP访问策略
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-27
     */
    @GetMapping(value = "/v1/IpStrategy/{ipStrategyId}")
    public Mono<IpStrategyV1> getIpStrategyV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                              @PathVariable(value = "ipStrategyId") String ipStrategyId) {
        return Mono.create(sink -> sink.success(this.ipStrategyService.getIpStrategyV1ById(loginUserId, ipStrategyId)));
    }

    @GetMapping(value = "/v1/IpStrategy/pagination")
    public Mono<Pagination<IpStrategyV1>> getIpStrategyPaginationV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                                                    @RequestParam(value = "regionId") String regionId,
                                                                    @RequestParam(value = "keyword", required = false) String keyword,
                                                                    @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return Mono.create(sink -> sink.success(this.ipStrategyService.getIpStrategyV1Pagination(loginUserId, regionId, keyword, pageNo, pageSize)));
    }
}
