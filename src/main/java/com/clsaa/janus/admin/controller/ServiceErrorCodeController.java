package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.service.ServiceErrorCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 服务端可能抛出的错误码 接口实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class ServiceErrorCodeController {
    @Autowired
    private ServiceErrorCodeService serviceErrorCodeService;
}
