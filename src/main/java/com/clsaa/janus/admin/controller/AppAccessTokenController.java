package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.service.AppAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Oauth2认证,AccessToken表 接口实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class AppAccessTokenController {
    @Autowired
    private AppAccessTokenService appAccessTokenService;
}
