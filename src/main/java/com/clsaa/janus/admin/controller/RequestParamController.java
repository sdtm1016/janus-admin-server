package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.entity.po.RequestParam;
import com.clsaa.janus.admin.service.RequestParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 客户端到网关的请求参数 接口实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class RequestParamController {
    @Autowired
    private RequestParamService requestParamService;

}
