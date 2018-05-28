package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.dao.SnapRequestConfigDao;
import com.clsaa.janus.admin.entity.po.RequestConfig;
import com.clsaa.janus.admin.entity.po.SnapRequestConfig;
import com.clsaa.janus.admin.entity.vo.v1.RequestConfigV1;
import com.clsaa.janus.admin.entity.vo.v1.SnapRequestConfigV1;
import com.clsaa.janus.admin.util.BeanUtils;
import com.clsaa.janus.admin.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户端到网关的请求配置快照信息 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class SnapRequestConfigService {

    @Autowired
    private SnapRequestConfigDao snapRequestConfigDao;
    @Autowired
    private RequestConfigService requestConfigService;

    /**
     * 创建前端到网关请求配置快照
     *
     * @param apiId     要创建快照的API主键id
     * @param snapApiId 要创建的API快照id
     * @return 创建的请求配置快照id
     */
    public String addRequestConfig(String apiId, String snapApiId) {
        RequestConfig requestConfig = this.requestConfigService.getRequestConfigByApiId(apiId);
        SnapRequestConfig snapRequestConfig = BeanUtils.convertType(requestConfig,SnapRequestConfig.class);
        snapRequestConfig.setSnapApiId(snapApiId);
        snapRequestConfig.setId(UUIDUtil.getUUID());
        this.snapRequestConfigDao.add(snapRequestConfig);
        return snapRequestConfig.getId();
    }


    /**
     * 根据API快照id查询请求配置快照信息
     *
     * @param snapApiId API快照id
     * @return {@link SnapRequestConfigV1}
     */
    public SnapRequestConfigV1 getSnapRequestConfigV1BySnapApiId(String snapApiId) {
        return BeanUtils.convertType(this.snapRequestConfigDao.getBySnapApiId(snapApiId),
                SnapRequestConfigV1.class);
    }
}
