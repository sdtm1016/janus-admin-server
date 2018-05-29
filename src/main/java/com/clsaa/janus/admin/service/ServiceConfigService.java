package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.constant.common.HttpMethodEnum;
import com.clsaa.janus.admin.constant.service.ContentTypeCategoryEnum;
import com.clsaa.janus.admin.constant.service.ServiceTypeEnum;
import com.clsaa.janus.admin.dao.ServiceConfigDao;
import com.clsaa.janus.admin.entity.po.ServiceConfig;
import com.clsaa.janus.admin.entity.vo.v1.ServiceConfigV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.util.BeanUtils;
import com.clsaa.janus.admin.util.UUIDUtil;
import com.clsaa.janus.admin.util.validator.AddressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 网关到服务端的请求配置信息 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class ServiceConfigService {
    @Autowired
    private ServiceConfigDao serviceConfigDao;
    
    /**
     * 添加后端服务配置
     *
     * @param apiId               APIid
     * @param type                后端服务类型,1为HTTP/HTTPS,2为函数计算
     * @param contentTypeCategory ContentType分类,1为透传,2为网关默认,3为自定义
     * @param contentTypeValue    ContentType值
     * @param address             后端服务地址
     * @param httpMethod          http方法
     * @param path                请求路径
     * @param timeout             超时时间,单位毫秒
     * @param mock                mock开关,false为关,true为开
     * @param mockResult          mock数据
     * @return 主键id
     */
    public String addServiceConfig(String apiId, Integer type, Integer contentTypeCategory, String contentTypeValue,
                                   String address, Integer httpMethod, String path, Long timeout, Boolean mock, String mockResult) {
        ServiceConfig serviceConfig = new ServiceConfig(UUIDUtil.getUUID(), apiId, type, contentTypeCategory,
                contentTypeValue, address, httpMethod, path, timeout, mock, mockResult);
        int count = 0;
        try {
            count = this.serviceConfigDao.add(serviceConfig);
        } catch (Exception e) {
            BizAssert.pass(count == 1, BizCodes.ERROR_INSERT);
        }
        BizAssert.pass(count == 1, BizCodes.ERROR_INSERT);
        return serviceConfig.getId();
    }

    /**
     * 根据ApiId删除其后端服务配置
     *
     * @param apiId APIId
     * @return 是否删除成功
     */
    public boolean delServiceConfigByApiId(String apiId) {
        int count = 0;
        try {
            count = this.serviceConfigDao.delByApiId(apiId);
        } catch (Exception e) {
            BizAssert.pass(count == 1, BizCodes.ERROR_DELETE);
        }
        BizAssert.pass(count == 1, BizCodes.ERROR_DELETE);
        return true;
    }

    /**
     * 根据APIId查询后端服务配置
     *
     * @param apiId APIId
     * @return {@link ServiceConfigV1}
     */
    public ServiceConfigV1 getServiceConfigV1ByApiId(String apiId) {
        return BeanUtils.convertType(this.serviceConfigDao.getByApiId(apiId), ServiceConfigV1.class);
    }

    /**
     * 根据APIId查询后端服务配置
     *
     * @param apiId APIId
     * @return {@link ServiceConfig}
     */
    public ServiceConfig getServiceConfigByApiId(String apiId) {
        return this.serviceConfigDao.getByApiId(apiId);
    }
}
