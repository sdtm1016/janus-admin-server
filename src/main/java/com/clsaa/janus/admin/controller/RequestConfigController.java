package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.service.RequestConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户端到网关的请求配置信息 接口实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class RequestConfigController {
    @Autowired
    private RequestConfigService requestConfigService;
}
