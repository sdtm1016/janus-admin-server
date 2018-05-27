package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.dao.IpStrategyDao;
import com.clsaa.janus.admin.entity.po.IpStrategy;
import com.clsaa.janus.admin.entity.vo.v1.IpStrategyV1;
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
 * IP控制策略信息,主要包含IP控制策略基本信息及控制类型 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class IpStrategyService {
    @Autowired
    private IpStrategyDao ipStrategyDao;

    /**
     * 创建IP访问策略
     *
     * @param regionId    地域id
     * @param type        类型
     * @param name        名称
     * @param description 描述
     * @return 创建IP访问策略的id
     */
    public String addIpStrategy(String loginUserId, String regionId, Integer type, String name, String description) {
        IpStrategy ipStrategy = new IpStrategy(UUIDUtil.getUUID(), regionId, name, type, description,
                TimestampUtil.now(), loginUserId, TimestampUtil.now(), loginUserId);
        int count = 0;
        try {
            count = this.ipStrategyDao.add(ipStrategy);
        } catch (Exception e) {
            BizAssert.validParam(count == 1, BizCodes.ERROR_INSERT);
        }
        BizAssert.validParam(count == 1, BizCodes.ERROR_INSERT);
        return ipStrategy.getId();
    }

    /**
     * 删除IP访问策略
     *
     * @param loginUserId  登录用户id
     * @param ipStrategyId IP访问策略id
     * @return 是否删除成功
     */
    public boolean delIpStrategyById(String loginUserId, String ipStrategyId) {
        int count = 0;
        try {
            count = this.ipStrategyDao.delById(ipStrategyId);
        } catch (Exception e) {
            BizAssert.validParam(count == 1, BizCodes.ERROR_DELETE);
        }
        BizAssert.validParam(count == 1, BizCodes.ERROR_DELETE);
        return true;
    }

    /**
     * 更新IP访问策略策略
     *
     * @param loginUserId  登录用户id
     * @param ipStrategyId IP访问策略策略id
     * @param regionId     地域id
     * @param type         类型
     * @param name         名称
     * @param description  描述
     * @return 是否修改成功
     */
    public boolean updateIpStrategy(String loginUserId, String ipStrategyId, String regionId, Integer type, String name, String description) {
        IpStrategy ipStrategy = this.ipStrategyDao.getById(ipStrategyId);
        BizAssert.found(ipStrategy != null, BizCodes.INVALID_PARAM.getCode(), "IP访问策略策略不存在");
        ipStrategy.setName(name);
        ipStrategy.setType(type);
        ipStrategy.setDescription(description);
        ipStrategy.setMuser(loginUserId);
        ipStrategy.setMtime(TimestampUtil.now());
        int count = 0;
        try {
            count = this.ipStrategyDao.update(ipStrategy);
        } catch (Exception e) {
            BizAssert.validParam(count == 1, BizCodes.ERROR_UPDATE);
        }
        BizAssert.validParam(count == 1, BizCodes.ERROR_UPDATE);
        return true;
    }

    /**
     * 根据id获取IP访问策略策略
     *
     * @param loginUserId  登录用户id
     * @param ipStrategyId IP访问策略策略id
     * @return {@link IpStrategyV1}
     */
    public IpStrategyV1 getIpStrategyV1ById(String loginUserId, String ipStrategyId) {
        return BeanUtils.convertType(this.ipStrategyDao.getById(ipStrategyId), IpStrategyV1.class);
    }

    /**
     * 分页查询IP控制策略
     *
     * @param loginUserId 登录用户id
     * @param regionId    地域id
     * @param keyword     关键词
     * @param pageNo      页号
     * @param pageSize    页大小
     * @return {@link Pagination<IpStrategyV1>}
     */
    public Pagination<IpStrategyV1> getIpStrategyV1Pagination(String loginUserId, String regionId, String keyword, Integer pageNo, Integer pageSize) {
        int count = this.ipStrategyDao.getPaginationCount(regionId, keyword);
        Pagination<IpStrategyV1> pagination = new Pagination<>();
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        pagination.setTotalCount(count);
        if (count == 0) {
            pagination.setPageList(Collections.emptyList());
            return pagination;
        }
        List<IpStrategy> ipStrategyList = this.ipStrategyDao.getPaginationList(regionId, keyword, pagination.getRowOffset(), pagination.getPageSize());
        pagination.setPageList(ipStrategyList.stream().map(i -> BeanUtils.convertType(i, IpStrategyV1.class)).collect(Collectors.toList()));
        return pagination;
    }
}
