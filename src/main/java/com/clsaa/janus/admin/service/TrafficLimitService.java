package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.dao.TrafficLimitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param regionId    地域id
     * @param name        名称
     * @param description 描述
     * @param unit        时间单位,时间单位,1为秒,2为分钟,3为小时
     * @param apiLimit    api限流数
     * @param appLimit    app限流数
     * @return
     */
    public String addTrafficLimit(String regionId, String name, String description, Integer unit, Integer apiLimit, Integer appLimit) {
        return null;
    }
}
