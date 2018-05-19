package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.dao.GroupDao;
import com.clsaa.janus.admin.entity.po.Group;
import com.clsaa.janus.admin.entity.po.Region;
import com.clsaa.janus.admin.entity.vo.GroupV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.result.Pagination;
import com.clsaa.janus.admin.util.TimestampUtil;
import com.clsaa.janus.admin.util.UUIDUtil;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * API分组信息 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class GroupService {
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private RegionService regionService;

    private static GroupV1 valueOf(Group group) {
        if (group == null) {
            return null;
        } else {
            GroupV1 groupV1 = new GroupV1();
            BeanUtils.copyProperties(group, groupV1);
            return groupV1;
        }
    }

    /**
     * 创建Group
     *
     * @param loginUserId
     * @param regionId     地域id
     * @param name         分组名称
     * @param description  分组描述
     * @param subDomain    分组子域地址
     * @param trafficLimit 流量限制
     * @return 创建的分组id
     */
    public String addGroup(String loginUserId, String regionId, String name, String description, String subDomain, Integer trafficLimit) {
        Region region = this.regionService.getRegionById(regionId);
        BizAssert.found(region != null, BizCodes.INVALID_PARAM.getCode(), "无法找到对应地域信息");
        Group existGroup = this.groupDao.getByName(name);
        BizAssert.pass(existGroup == null, BizCodes.INVALID_PARAM.getCode(), "");
        Group group = new Group(UUIDUtil.getUUID(), regionId, name, description, subDomain, trafficLimit,
                TimestampUtil.now(), loginUserId, TimestampUtil.now(), loginUserId);
        int count = 0;
        try {
            count = this.groupDao.add(group);
        } catch (Exception e) {
            e.printStackTrace();
            BizAssert.pass(count == 1, BizCodes.ERROR_INSERT);
        }
        BizAssert.pass(count == 1, BizCodes.ERROR_INSERT);
        return group.getId();
    }

    /**
     * 根据id删除API分组
     *
     * @param loginUserId 登录用户id
     * @param groupId     API分组id
     * @return 是否删除成功
     */
    public Boolean delGroupById(String loginUserId, String groupId) {
        //检查是否还有API,只有分组中没有API才能删除
        int count = 0;
        try {
            count = this.groupDao.delById(groupId);
        } catch (Exception e) {
            BizAssert.pass(count == 1, BizCodes.ERROR_UPDATE);
        }
        BizAssert.pass(count == 1, BizCodes.ERROR_UPDATE);
        return true;
    }

    /**
     * 更新API分组
     *
     * @param loginUserId  登录用户id
     * @param groupId      API分组id
     * @param name         API分组名称
     * @param description  API分组描述
     * @param subDomain    子域地址
     * @param trafficLimit 流量限制(QPS)
     * @return 是否修改成功
     */
    public boolean updateGroup(String loginUserId, String groupId, String name, String description, String subDomain, @Range(min = 1, max = 1000) @NotNull(message = "流量限制不能为空") Integer trafficLimit) {
        Group existGroup = this.groupDao.getById(groupId);
        BizAssert.found(existGroup != null, BizCodes.INVALID_PARAM.getCode(), "要修改的分组不存在");
        Group existNameGroup = this.groupDao.getByName(name);
        BizAssert.pass(existNameGroup == null || existNameGroup.getId().equals(existGroup.getId()),
                BizCodes.INVALID_PARAM.getCode(), "");
        existGroup.setMtime(TimestampUtil.now());
        existGroup.setMuser(loginUserId);
        existGroup.setName(name);
        existGroup.setDescription(description);
        existGroup.setSubDomain(subDomain);
        existGroup.setTrafficLimit(trafficLimit);
        int count = 0;
        try {
            count = this.groupDao.update(existGroup);
        } catch (Exception e) {
            BizAssert.pass(count == 1, BizCodes.ERROR_UPDATE);
        }
        BizAssert.pass(count == 1, BizCodes.ERROR_UPDATE);
        return true;
    }

    /**
     * 根据id获取API分组
     *
     * @param loginUserId 登录用户id
     * @param groupId     API分组id
     * @return {@link GroupV1}
     */
    public GroupV1 getGroupV1ById(String loginUserId, String groupId) {
        return valueOf(this.groupDao.getById(groupId));
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
     */
    public Pagination<GroupV1> getPagination(String loginUserId, String regionId, String keyword, Integer pageNo, Integer pageSize) {
        int count = this.groupDao.getPaginationCount(regionId, keyword);
        Pagination<GroupV1> pagination = new Pagination<>();
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        pagination.setTotalCount(count);
        if (count == 0) {
            pagination.setPageList(Collections.emptyList());
            return pagination;
        }
        List<Group> groupList = this.groupDao.getPaginationList(regionId, keyword, pagination.getRowOffset(), pagination.getPageSize());
        List<GroupV1> groupV1List = groupList.stream().map(GroupService::valueOf).collect(Collectors.toList());
        pagination.setPageList(groupV1List);
        return pagination;
    }
}
