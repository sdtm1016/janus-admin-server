package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * API认证信息,包含AccessKey和AccessSecret 前端控制器
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthService authService;
}
