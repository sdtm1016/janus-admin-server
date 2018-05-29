package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.dao.SnapApiDao;
import com.clsaa.janus.admin.entity.po.Api;
import com.clsaa.janus.admin.entity.po.SnapApi;
import com.clsaa.janus.admin.entity.vo.v1.SnapApiV1;
import com.clsaa.janus.admin.util.BeanUtils;
import com.clsaa.janus.admin.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * API快照信息 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class SnapApiService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private SnapApiDao snapApiDao;
    @Autowired
    private ApiService apiService;
    @Autowired
    private SnapRequestConfigService snapRequestConfigService;
    @Autowired
    private SnapRequestParamService snapRequestParamService;
    @Autowired
    private SnapServiceConfigService snapServiceConfigService;
    @Autowired
    private SnapServiceConstParamService snapServiceConstParamService;
    @Autowired
    private SnapServiceErrorCodeService snapServiceErrorCodeService;
    @Autowired
    private SnapServiceParamService snapServiceParamService;


    /**
     * 创建API快照
     *
     * @param apiId 需要创建快照的APIid
     * @return 创建API快照id
     */
    @Transactional(rollbackFor = Exception.class)
    public String addSnapApi(String apiId) {
        Api api = this.apiService.getApiById(apiId);
        SnapApi snapApi = BeanUtils.convertType(api, SnapApi.class);
        snapApi.setId(UUIDUtil.getUUID());
        this.snapApiDao.add(snapApi);
        this.snapRequestConfigService.addSnapRequestConfig(snapApi.getId(), apiId);
        this.snapRequestParamService.addSnapRequestParamBatch(snapApi.getId(), apiId);
        this.snapServiceConfigService.addSnapServiceConfig(snapApi.getId(), apiId);
        this.snapServiceConstParamService.addSnapServiceConstParamBatch(snapApi.getId(), apiId);
        this.snapServiceErrorCodeService.addSnapServiceErrorCodeBatch(snapApi.getId(), apiId);
        this.snapServiceParamService.addServiceParamBatch(snapApi.getId(), apiId);
        return snapApi.getId();
    }


    /**
     * 根据API快照id查询API快照详情
     *
     * @param loginUserId 登录用户id
     * @param snapApiId   API快照id
     * @return {@link SnapApiV1}
     */
    public SnapApiV1 getSnapApiV1ById(String loginUserId, String snapApiId) {
        SnapApiV1 snapApiV1 = BeanUtils.convertType(this.snapApiDao.getById(snapApiId), SnapApiV1.class);
        if (snapApiV1 == null) {
            return null;
        }
        snapApiV1.setSnapRequestConfig(this.snapRequestConfigService.getSnapRequestConfigV1BySnapApiId(snapApiId));
        snapApiV1.setSnapRequestParams(this.snapRequestParamService.getRequestParamV1ListBySnapApiId(snapApiId));
        snapApiV1.setSnapServiceConfig(this.snapServiceConfigService.getSnapServiceConfigV1ByApiId(snapApiId));
        snapApiV1.setSnapServiceConstParams(this.snapServiceConstParamService.getSnapServiceConstParamV1ListBySnapApiId(snapApiId));
        snapApiV1.setSnapServiceErrorCodes(this.snapServiceErrorCodeService.getSnapServiceErrorCodeV1ListBySnapApiId(snapApiId));
        snapApiV1.setSnapServiceParams(this.snapServiceParamService.getSnapServiceParamV1ListBySnapApiId(snapApiId));
        return snapApiV1;
    }
}
