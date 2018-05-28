package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.constant.common.XHeaders;
import com.clsaa.janus.admin.entity.dto.v1.IpStrategyItemDtoV1;
import com.clsaa.janus.admin.entity.po.IpStrategyItem;
import com.clsaa.janus.admin.entity.vo.v1.IpStrategyItemV1;
import com.clsaa.janus.admin.result.Pagination;
import com.clsaa.janus.admin.service.IpStrategyItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * <p>
 * IP控制策略项,包含具体要控制的IP信息 接口实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class IpStrategyItemController {
    @Autowired
    private IpStrategyItemService ipStrategyItemService;

    /**
     * 创建IP访问策略项
     *
     * @param loginUserId         登录用户id
     * @param ipStrategyItemDtoV1 {@link IpStrategyItemDtoV1}
     * @return 创建的IP访问策略项的id
     * @summary 创建IP访问策略项
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-27
     */
    @PostMapping(value = "/v1/IpStrategy/item")
    public Mono<String> addIpStrategyItemV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                            @Validated @RequestBody IpStrategyItemDtoV1 ipStrategyItemDtoV1) {
        return Mono.create(sink -> sink.success(this.ipStrategyItemService.addIpStrategyItem(loginUserId,
                ipStrategyItemDtoV1.getIpStrategyId(), ipStrategyItemDtoV1.getName(), ipStrategyItemDtoV1.getCidrIp())));
    }

    /**
     * 根据id删除IP访问策略项
     *
     * @param loginUserId      登录用户id
     * @param ipStrategyItemId IP访问策略项id
     * @return 是否删除成功
     * @summary 根据id删除IP访问策略项
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-27
     */
    @DeleteMapping(value = "/v1/IpStrategy/item/{ipStrategyItemId}")
    public Mono<Boolean> delIpStrategyItemByIdV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                                 @PathVariable(value = "ipStrategyItemId") String ipStrategyItemId) {
        return Mono.create(sink -> sink.success(this.ipStrategyItemService.delIpStrategyItemById(loginUserId, ipStrategyItemId)));
    }

    /**
     * 修改IP访问策略项
     *
     * @param loginUserId         登录用户id
     * @param ipStrategyItemId    IP访问策略项id
     * @param ipStrategyItemDtoV1 {@link IpStrategyItemDtoV1}
     * @return 是否修改成功
     * @summary 修改IP访问策略项
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-27
     */
    @PutMapping(value = "/v1/IpStrategy/item/{ipStrategyItemId}")
    public Mono<Boolean> updateIpStrategyItemV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                                @PathVariable(value = "ipStrategyItemId") String ipStrategyItemId,
                                                @Validated @RequestBody IpStrategyItemDtoV1 ipStrategyItemDtoV1) {
        return Mono.create(sink -> sink.success(this.ipStrategyItemService.updateIpStrategyItem(loginUserId,
                ipStrategyItemId, ipStrategyItemDtoV1.getIpStrategyId(), ipStrategyItemDtoV1.getName(), ipStrategyItemDtoV1.getCidrIp())));
    }

    /**
     * 根据id查询IP访问策略项
     *
     * @param loginUserId      登录用户id
     * @param ipStrategyItemId IP访问策略项id
     * @return {@link Mono<IpStrategyItem>}
     * @summary 根据id查询IP访问策略项
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-27
     */
    @GetMapping(value = "/v1/IpStrategy/item/{ipStrategyItemId}")
    public Mono<IpStrategyItemV1> getIpStrategyItemV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                                      @PathVariable(value = "ipStrategyItemId") String ipStrategyItemId) {
        return Mono.create(sink -> sink.success(this.ipStrategyItemService.getIpStrategyItemV1ById(loginUserId, ipStrategyItemId)));
    }

    /**
     * 分页查询IP访问策略项
     *
     * @param loginUserId      登录用户id
     * @param ipStrategyItemId IP访问策略项id
     * @param pageNo           页号
     * @param pageSize         页大小
     * @return {@link Mono<IpStrategyItem>}
     * @summary 根据id获取IP访问策略项
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-27
     */
    @GetMapping(value = "/v1/IpStrategy/item/pagination")
    public Mono<Pagination<IpStrategyItemV1>> getIpStrategyItemV1Pagination(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                                                           @RequestParam(value = "ipStrategyId") String ipStrategyId,
                                                                           @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return Mono.create(sink -> sink.success(this.ipStrategyItemService.getIpStrategyItemV1Pagination(loginUserId, ipStrategyId, pageNo, pageSize)));
    }

}
