package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.dao.AuthDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * API认证信息,包含AccessKey和AccessSecret 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class AuthService {
    @Autowired
    private AuthDao authDao;
}
