package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.dao.SnapServiceParamDao;
import com.clsaa.janus.admin.entity.po.ServiceParam;
import com.clsaa.janus.admin.entity.po.SnapServiceParam;
import com.clsaa.janus.admin.entity.vo.v1.SnapServiceParamV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.util.BeanUtils;
import com.clsaa.janus.admin.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 网关请求到服务端的参数 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class SnapServiceParamService {
    @Autowired
    private SnapServiceParamDao snapServiceParamDao;
    @Autowired
    private ServiceParamService serviceParamService;

    /**
     * 批量添加后端服务请求参数
     *
     * @param snapApiId API快照id
     * @param apiId     APIid
     * @return 是否添加成功
     */
    public boolean addServiceParamBatch(String snapApiId, String apiId) {
        List<ServiceParam> serviceParamList = this.serviceParamService.getServiceParamListByApiId(apiId);
        if (serviceParamList.size() == 0) {
            return true;
        }
        List<SnapServiceParam> snapServiceParamList = serviceParamList
                .stream()
                .map(s -> {
                    SnapServiceParam snapServiceParam = BeanUtils.convertType(s, SnapServiceParam.class);
                    snapServiceParam.setId(UUIDUtil.getUUID());
                    snapServiceParam.setSnapApiId(snapApiId);
                    return snapServiceParam;
                }).collect(Collectors.toList());
        int count = 0;
        try {
            count = this.snapServiceParamDao.addBatch(snapServiceParamList);
        } catch (Exception e) {
            BizAssert.pass(count == snapServiceParamList.size(), BizCodes.ERROR_INSERT);
        }
        BizAssert.pass(count == snapServiceParamList.size(), BizCodes.ERROR_INSERT);
        return true;
    }

    /**
     * 根据API快照id获取其后端服务参数快照
     *
     * @param snapApiId API快照id
     * @return {@link List<SnapServiceParamV1>}
     */
    public List<SnapServiceParamV1> getSnapServiceParamV1ListBySnapApiId(String snapApiId) {
        return this.snapServiceParamDao.getListBySnapApiId(snapApiId)
                .stream().map(s -> BeanUtils.convertType(s, SnapServiceParamV1.class)).collect(Collectors.toList());
    }
}
