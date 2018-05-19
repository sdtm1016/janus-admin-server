package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.service.EnvironmentVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 环境变量信息 接口实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class EnvironmentVariableController {
    @Autowired
    private EnvironmentVariableService environmentVariableService;
}
