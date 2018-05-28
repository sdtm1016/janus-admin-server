package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.dao.SnapApiDao;
import com.clsaa.janus.admin.entity.po.Api;
import com.clsaa.janus.admin.entity.po.SnapApi;
import com.clsaa.janus.admin.entity.vo.v1.ApiV1;
import com.clsaa.janus.admin.util.BeanUtils;
import com.clsaa.janus.admin.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    /**
     * 创建API快照
     *
     * @param apiId 需要创建快照的APIid
     * @return 创建API快照id
     */
    public String addSnapApi(String apiId) {
        Api api = this.apiService.getApiById(apiId);
        SnapApi snapApi = BeanUtils.convertType(api, SnapApi.class);
        snapApi.setId(UUIDUtil.getUUID());
        this.snapApiDao.add(snapApi);

        return snapApi.getId();
    }


    /**
     * 根据id查询API快照详情
     *
     * @param apiId APIId
     * @return {@link ApiV1}
     */
//    public ApiV1 getApiV1ById(String apiId) {
//        Api api = this.apiDao.getById(apiId);
//        ApiV1 apiV1 = BeanUtils.convertType(api, ApiV1.class);
//
//
//        return apiV1;
//    }

}
