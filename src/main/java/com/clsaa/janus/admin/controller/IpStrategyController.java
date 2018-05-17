package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.service.IpStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * IP控制策略信息,主要包含IP控制策略基本信息及控制类型 前端控制器
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class IpStrategyController {
    @Autowired
    private IpStrategyService ipStrategyService;
}
