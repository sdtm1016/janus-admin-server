package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.dao.IpStrategyItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
