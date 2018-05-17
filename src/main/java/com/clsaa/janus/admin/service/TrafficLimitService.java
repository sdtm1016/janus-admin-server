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
}
