package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.service.EnvironmentVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 环境变量信息 前端控制器
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class EnvironmentVariableController {
    @Autowired
    private EnvironmentVariableService environmentVariableService;
}
