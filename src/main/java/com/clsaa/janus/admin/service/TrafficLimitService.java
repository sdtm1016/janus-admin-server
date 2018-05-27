package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.dao.TrafficLimitDao;
import com.clsaa.janus.admin.entity.po.TrafficLimit;
import com.clsaa.janus.admin.entity.vo.v1.TrafficLimitV1;
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
 * 限流策略 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class TrafficLimitService {
    @Autowired
    private TrafficLimitDao trafficLimitDao;


    /**
     * 创建限流策略
     *
     * @param loginUserId 登录用户id
     * @param regionId    地域id
     * @param name        名称
     * @param description 描述
     * @param unit        时间单位,时间单位,1为秒,2为分钟,3为小时
     * @param apiLimit    api限流数
     * @param appLimit    app限流数
     * @return 添加的限流策略Id
     */
    public String addTrafficLimit(String loginUserId, String regionId, String name, String description, Integer unit, Integer apiLimit, Integer appLimit) {
        TrafficLimit trafficLimit = new TrafficLimit(UUIDUtil.getUUID(), regionId, name, unit, description, apiLimit, appLimit,
                TimestampUtil.now(), loginUserId, TimestampUtil.now(), loginUserId);
        int count = 0;
        try {
            count = this.trafficLimitDao.add(trafficLimit);
        } catch (Exception e) {
            BizAssert.validParam(count == 1, BizCodes.ERROR_INSERT);
        }
        BizAssert.validParam(count == 1, BizCodes.ERROR_INSERT);
        return trafficLimit.getId();
    }

    /**
     * 删除限流策略
     *
     * @param loginUserId    登录用户id
     * @param trafficLimitId 限流策略id
     * @return 是否删除成功
     */
    public boolean delTrafficLimitById(String loginUserId, String trafficLimitId) {
        int count = 0;
        try {
            count = this.trafficLimitDao.delById(trafficLimitId);
        } catch (Exception e) {
            BizAssert.validParam(count == 1, BizCodes.ERROR_DELETE);
        }
        BizAssert.validParam(count == 1, BizCodes.ERROR_DELETE);
        return true;
    }

    /**
     * 修改限流策略
     *
     * @param loginUserId    登录用户id
     * @param trafficLimitId 限流策略id
     * @param regionId       地域id
     * @param name           名称
     * @param description    描述
     * @param unit           时间单位,时间单位,1为秒,2为分钟,3为小时
     * @param apiLimit       api限流数
     * @param appLimit       app限流数
     * @return 是否修改成功
     */
    public boolean updateTrafficLimit(String loginUserId, String trafficLimitId, String regionId, String name,
                                      String description, Integer unit, Integer apiLimit, Integer appLimit) {
        TrafficLimit trafficLimit = this.trafficLimitDao.getTrafficLimitById(trafficLimitId);
        BizAssert.found(trafficLimit != null, BizCodes.INVALID_PARAM.getCode(), "限流策略不存在");
        trafficLimit.setName(name);
        trafficLimit.setDescription(description);
        trafficLimit.setUnit(unit);
        trafficLimit.setApiLimit(apiLimit);
        trafficLimit.setAppLimit(appLimit);
        trafficLimit.setMtime(TimestampUtil.now());
        trafficLimit.setMuser(loginUserId);
        int count = 0;
        try {
            count = this.trafficLimitDao.update(trafficLimit);
        } catch (Exception e) {
            BizAssert.validParam(count == 1, BizCodes.ERROR_UPDATE);
        }
        BizAssert.validParam(count == 1, BizCodes.ERROR_UPDATE);
        return true;
    }


    /**
     * 根据id获取限流策略
     *
     * @param loginUserId    登录用户id
     * @param trafficLimitId 限流策略id
     * @return {@link TrafficLimitV1}
     */
    public TrafficLimitV1 getTrafficLimitV1ById(String loginUserId, String trafficLimitId) {
        return BeanUtils.convertType(this.trafficLimitDao.getTrafficLimitById(trafficLimitId), TrafficLimitV1.class);
    }

    /**
     * 获取分页数据
     *
     * @param loginUserId 登录用户id
     * @param keyword     关键词
     * @param pageNo      页号
     * @param pageSize    页大小
     * @return {@link Pagination<TrafficLimitV1>}
     */
    public Pagination<TrafficLimitV1> getTrafficLimitV1Pagination(String loginUserId, String keyword, Integer pageNo, Integer pageSize) {
        int count = this.trafficLimitDao.getPaginationCount(keyword);
        Pagination<TrafficLimitV1> pagination = new Pagination<>();
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        pagination.setTotalCount(count);
        if (count == 0) {
            pagination.setPageList(Collections.emptyList());
            return pagination;
        }
        List<TrafficLimit> trafficLimitList = this.trafficLimitDao.getPaginationList(keyword, pagination.getRowOffset(), pagination.getPageSize());
        pagination.setPageList(trafficLimitList.stream().map(t -> BeanUtils.convertType(t, TrafficLimitV1.class)).collect(Collectors.toList()));
        return pagination;
    }
}
