package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.dao.ServiceConstParamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网关请求到服务端的常量参数 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class ServiceConstParamService {
    @Autowired
    private ServiceConstParamDao serviceConstParamDao;
}