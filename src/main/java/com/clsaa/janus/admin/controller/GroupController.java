package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.constant.common.XHeaders;
import com.clsaa.janus.admin.entity.dto.GroupDtoV1;
import com.clsaa.janus.admin.entity.vo.GroupV1;
import com.clsaa.janus.admin.result.Pagination;
import com.clsaa.janus.admin.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * <p>
 * API分组信息 接口实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class GroupController {
    @Autowired
    private GroupService groupService;

    /**
     * 创建Group
     *
     * @param loginUserId 登录用户id
     * @param groupDtoV1  {@link GroupDtoV1}
     * @return 创建的分组id
     * @summary 根据id获取API分组
     * @author 任贵杰 812022339@qq.com
     * @since 2018/5/19
     */
    @PostMapping(value = "/v1/group")
    public Mono<String> addGroupV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                   @RequestBody GroupDtoV1 groupDtoV1) {
        return Mono.create(sinkMono -> sinkMono.success(this.groupService.addGroup(loginUserId,
                groupDtoV1.getRegionId(),
                groupDtoV1.getName(),
                groupDtoV1.getDescription(),
                groupDtoV1.getSubDomain(),
                groupDtoV1.getTrafficLimit())));
    }

    /**
     * 根据id删除API分组
     *
     * @param loginUserId 登录用户id
     * @param groupId     API分组id
     * @return 是否删除成功
     * @summary 根据id删除API分组
     * @author 任贵杰 812022339@qq.com
     * @since 2018/5/19
     */
    @DeleteMapping(value = "/v1/group/{groupId}")
    public Mono<Boolean> delGroupV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                    @PathVariable(value = "groupId") String groupId) {
        return Mono.create(sinkMono -> sinkMono.success(this.groupService.delGroupById(loginUserId, groupId)));
    }

    /**
     * 修改API分组
     *
     * @param loginUserId 登录用户id
     * @param groupId     API分组id
     * @param groupDtoV1  {@link GroupDtoV1}
     * @return 是否修改成功
     * @summary 根据id修改API分组
     * @author 任贵杰 812022339@qq.com
     * @since 2018/5/19
     */
    @PutMapping(value = "/v1/group/{groupId}")
    public Mono<Boolean> updateGroupV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                       @PathVariable(value = "groupId") String groupId,
                                       @RequestBody GroupDtoV1 groupDtoV1) {
        return Mono.create(monoSink ->
                monoSink.success(
                        this.groupService.updateGroup(
                                loginUserId,
                                groupId,
                                groupDtoV1.getName(),
                                groupDtoV1.getDescription(),
                                groupDtoV1.getSubDomain(),
                                groupDtoV1.getTrafficLimit()
                        )
                )
        );
    }

    /**
     * 根据id获取API分组
     *
     * @param loginUserId 登录用户id
     * @param groupId     API分组id
     * @return {@link GroupV1}
     * @summary 根据id获取API分组
     * @author 任贵杰 812022339@qq.com
     * @since 2018/5/19
     */
    @GetMapping(value = "/v1/group/{groupId}")
    public Mono<GroupV1> getGroupByIdV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                        @PathVariable(value = "groupId") String groupId) {
        return Mono.create(monoSink -> monoSink.success(this.groupService.getGroupV1ById(loginUserId, groupId)));
    }

    /**
     * 获取API分组分页数据
     *
     * @param loginUserId 登录用户id
     * @param regionId    地域id
     * @param keyword     关键词(当前根据分组名称正向模糊匹配)
     * @param pageNo      页号
     * @param pageSize    页大小
     * @return {@link Pagination<GroupV1>}
     * @summary 获取API分组分页数据
     * @author 任贵杰 812022339@qq.com
     * @since 2018/5/19
     */
    @GetMapping(value = "/v1/group/pagination")
    public Mono<Pagination<GroupV1>> getGroupPaginationV1(@RequestHeader(value = XHeaders.X_LOGIN_USER_ID) String loginUserId,
                                                          @RequestParam(value = "regionId") String regionId,
                                                          @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                                          @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return Mono.create(monoSink -> monoSink.success(this.groupService.getPagination(loginUserId, regionId, keyword, pageNo, pageSize)));
    }
}
