package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.dao.SnapServiceConfigDao;
import com.clsaa.janus.admin.entity.po.ServiceConfig;
import com.clsaa.janus.admin.entity.po.SnapServiceConfig;
import com.clsaa.janus.admin.entity.vo.v1.SnapServiceConfigV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.util.BeanUtils;
import com.clsaa.janus.admin.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网关到服务端的请求配置快照信息 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class SnapServiceConfigService {
    @Autowired
    private SnapServiceConfigDao snapServiceConfigDao;
    @Autowired
    private ServiceConfigService serviceConfigService;

    /**
     * 添加后端服务请求配置快照
     *
     * @param snapApiId API快照id
     * @param apiId     APIid
     * @return 添加的后端服务请求配置快照id
     */
    public String addSnapServiceConfig(String snapApiId, String apiId) {
        ServiceConfig serviceConfig = this.serviceConfigService.getServiceConfigByApiId(apiId);
        SnapServiceConfig snapServiceConfig = BeanUtils.convertType(serviceConfig, SnapServiceConfig.class);
        snapServiceConfig.setId(UUIDUtil.getUUID());
        snapServiceConfig.setSnapApiId(snapApiId);
        int count = 0;
        try {
            count = this.snapServiceConfigDao.add(snapServiceConfig);
        } catch (Exception e) {
            BizAssert.pass(count == 1, BizCodes.ERROR_INSERT);
        }
        BizAssert.pass(count == 1, BizCodes.ERROR_INSERT);
        return snapServiceConfig.getId();
    }

    /**
     * 根据API快照id查询后端服务配置
     *
     * @param snapApiId API快照id
     * @return {@link SnapServiceConfigV1}
     */
    public SnapServiceConfigV1 getSnapServiceConfigV1ByApiId(String snapApiId) {
        return BeanUtils.convertType(this.snapServiceConfigDao.getBySnapApiId(snapApiId), SnapServiceConfigV1.class);
    }
}
