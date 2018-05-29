package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.dao.SnapServiceConstParamDao;
import com.clsaa.janus.admin.entity.po.ServiceConstParam;
import com.clsaa.janus.admin.entity.po.SnapServiceConstParam;
import com.clsaa.janus.admin.entity.vo.v1.SnapServiceConstParamV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.util.BeanUtils;
import com.clsaa.janus.admin.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 网关请求到服务端的常量参数 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class SnapServiceConstParamService {

    @Autowired
    private SnapServiceConstParamDao snapServiceConstParamDao;
    @Autowired
    private ServiceConstParamService serviceConstParamService;


    /**
     * 批量添加后端服务常量参数快照
     *
     * @param snapApiId API快照id
     * @param apiId     APIid
     * @return 是否添加成功
     */
    public boolean addSnapServiceConstParamBatch(String snapApiId, String apiId) {
        List<ServiceConstParam> serviceConstParamList = this.serviceConstParamService.getServiceConstParamListByApiId(apiId);
        if (serviceConstParamList.size() == 0) {
            return true;
        }
        List<SnapServiceConstParam> snapServiceConstParamList = serviceConstParamList
                .stream()
                .map(s -> {
                    SnapServiceConstParam snapServiceConstParam = BeanUtils.convertType(s, SnapServiceConstParam.class);
                    snapServiceConstParam.setId(UUIDUtil.getUUID());
                    snapServiceConstParam.setSnapApiId(snapApiId);
                    return snapServiceConstParam;
                }).collect(Collectors.toList());
        int count = 0;
        try {
            count = this.snapServiceConstParamDao.addBatch(snapServiceConstParamList);
        } catch (Exception e) {
            BizAssert.pass(count == serviceConstParamList.size(), BizCodes.ERROR_INSERT);
        }
        BizAssert.pass(count == serviceConstParamList.size(), BizCodes.ERROR_INSERT);
        return true;
    }

    /**
     * 根据API快照id查询其常量参数列表
     *
     * @param snapApiId API快照id
     * @return {@link List<SnapServiceConstParamV1>}
     */
    public List<SnapServiceConstParamV1> getSnapServiceConstParamV1ListBySnapApiId(String snapApiId) {
        return this.snapServiceConstParamDao.getListBySnapApiId(snapApiId)
                .stream().map(s -> BeanUtils.convertType(s, SnapServiceConstParamV1.class)).collect(Collectors.toList());
    }
}
