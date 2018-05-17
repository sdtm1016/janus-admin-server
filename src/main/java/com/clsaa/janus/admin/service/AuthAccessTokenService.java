package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.dao.AuthAccessTokenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Oauth2认证,AccessToken表 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class AuthAccessTokenService {
    @Autowired
    private AuthAccessTokenDao authAccessTokenDao;
}
