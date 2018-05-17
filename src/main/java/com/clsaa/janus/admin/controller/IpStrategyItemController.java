package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.service.IpStrategyItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * IP控制策略项,包含具体要控制的IP信息 前端控制器
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class IpStrategyItemController {
    @Autowired
    private IpStrategyItemService ipStrategyItemService;
}
