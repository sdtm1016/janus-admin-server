package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.service.ServiceParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 网关请求到服务端的参数 前端控制器
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class ServiceParamController {
    @Autowired
    private ServiceParamService serviceParamService;
}
