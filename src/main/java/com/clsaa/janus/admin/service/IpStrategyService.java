package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.dao.IpStrategyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
