package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.dao.IpStrategyItemDao;
import com.clsaa.janus.admin.entity.po.IpStrategyItem;
import com.clsaa.janus.admin.entity.vo.v1.IpStrategyItemV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.result.Pagination;
import com.clsaa.janus.admin.util.BeanUtils;
import com.clsaa.janus.admin.util.TimestampUtil;
import com.clsaa.janus.admin.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * IP控制策略项,包含具体要控制的IP信息 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class IpStrategyItemService {
    @Autowired
    private IpStrategyItemDao ipStrategyItemDao;

    /**
     * 添加IP控制策略项
     *
     * @param loginUserId  登录用户id
     * @param ipStrategyId IP控制策略id
     * @param name         名称
     * @param cidrIp       适用的IP或IP段,多个用分号分隔
     * @return 创建IP控制策略项的id
     */
    public String addIpStrategyItem(String loginUserId, String ipStrategyId, String name, String cidrIp) {
        IpStrategyItem ipStrategyItem = new IpStrategyItem(UUIDUtil.getUUID(), ipStrategyId, name, cidrIp,
                TimestampUtil.now(), loginUserId, TimestampUtil.now(), loginUserId);
        int count = 0;
        try {
            count = this.ipStrategyItemDao.add(ipStrategyItem);
        } catch (Exception e) {
            BizAssert.pass(count == 1, BizCodes.ERROR_INSERT);
        }
        BizAssert.pass(count == 1, BizCodes.ERROR_INSERT);
        return ipStrategyItem.getId();
    }

    /**
     * 删除IP控制策略项
     *
     * @param loginUserId      登录用户ID
     * @param ipStrategyItemId IP控制策略项id
     * @return 是否删除成功
     */
    public boolean delIpStrategyItemById(String loginUserId, String ipStrategyItemId) {
        int count = 0;
        try {
            count = this.ipStrategyItemDao.delById(ipStrategyItemId);
        } catch (Exception e) {
            BizAssert.pass(count == 1, BizCodes.ERROR_DELETE);
        }
        BizAssert.pass(count == 1, BizCodes.ERROR_DELETE);
        return true;
    }

    /**
     * 更新IP控制策略项
     *
     * @param loginUserId      登录用户id
     * @param ipStrategyId     IP控制策略id
     * @param ipStrategyItemId IP控制策略项id
     * @param name             名称
     * @param cidrIp           适用的IP或IP段,多个用分号分隔
     * @return 是否更新成功
     */
    public boolean updateIpStrategyItem(String loginUserId, String ipStrategyItemId, String ipStrategyId, String name, String cidrIp) {
        IpStrategyItem ipStrategyItem = this.ipStrategyItemDao.getById(ipStrategyItemId);
        BizAssert.found(ipStrategyItem != null, BizCodes.INVALID_PARAM.getCode(), "IP访问策略项不存在");
        BizAssert.validParam(ipStrategyItem.getIpStrategyId().equals(ipStrategyId), BizCodes.INVALID_PARAM.getCode(),
                "此IP访问控制策略项不属于此IP访问控制策略");
        ipStrategyItem.setMtime(TimestampUtil.now());
        ipStrategyItem.setMuser(loginUserId);
        ipStrategyItem.setName(name);
        ipStrategyItem.setCidrIp(cidrIp);

        int count = 0;
        try {
            count = this.ipStrategyItemDao.update(ipStrategyItem);
        } catch (Exception e) {
            BizAssert.pass(count == 1, BizCodes.ERROR_UPDATE);
        }
        BizAssert.pass(count == 1, BizCodes.ERROR_UPDATE);
        return true;
    }

    /**
     * 根据id查询IP控制策略项
     *
     * @param loginUserId      登录用户ID
     * @param ipStrategyItemId IP控制策略项id
     * @return {@link IpStrategyItem}
     */
    public IpStrategyItemV1 getIpStrategyItemV1ById(String loginUserId, String ipStrategyItemId) {
        return BeanUtils.convertType(this.ipStrategyItemDao.getById(ipStrategyItemId), IpStrategyItemV1.class);
    }

    /**
     * 分页查询IP访问策略项
     *
     * @param loginUserId  登录用户ID
     * @param ipStrategyId IP控制策id
     * @param pageNo
     * @param pageSize
     * @return {@link Pagination<IpStrategyItemV1>}
     */
    public Pagination<IpStrategyItemV1> getIpStrategyItemV1Pagination(String loginUserId, String ipStrategyId, Integer pageNo, Integer pageSize) {
        int count = this.ipStrategyItemDao.getPaginationCount(ipStrategyId);
        Pagination<IpStrategyItemV1> pagination = new Pagination<>();
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        pagination.setTotalCount(count);
        if (count == 0) {
            pagination.setPageList(Collections.emptyList());
            return pagination;
        }
        List<IpStrategyItem> ipStrategyItemList = this.ipStrategyItemDao.getPaginationList(ipStrategyId, pagination.getRowOffset(), pagination.getPageSize());
        pagination.setPageList(ipStrategyItemList.stream().map(i -> BeanUtils.convertType(i, IpStrategyItemV1.class)).collect(Collectors.toList()));
        return pagination;
    }
}
