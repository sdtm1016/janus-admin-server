package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.constant.common.ParamLocationEnum;
import com.clsaa.janus.admin.dao.ServiceParamDao;
import com.clsaa.janus.admin.entity.po.ServiceParam;
import com.clsaa.janus.admin.entity.vo.ServiceParamV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
public class ServiceParamService {
    @Autowired
    private ServiceParamDao serviceParamDao;

    private void doValidation(List<ServiceParam> serviceParamList) {
        long size = serviceParamList.size();
        long diffIdCount = serviceParamList.stream().map(ServiceParam::getId).distinct().count();
        long diffApiIdCount = serviceParamList.stream().map(ServiceParam::getApiId).distinct().count();
        long diffRequestParamIdCount = serviceParamList.stream().map(ServiceParam::getRequestParamId).distinct().count();
        long diffNameCount = serviceParamList.stream().map(ServiceParam::getName).distinct().count();
        long diffSortCount = serviceParamList.stream().map(ServiceParam::getSort).distinct().count();
        BizAssert.validParam(diffIdCount == size, BizCodes.INVALID_PARAM.getCode(), "后端服务参数有重复ID");
        BizAssert.validParam(diffApiIdCount == 1, BizCodes.INVALID_PARAM.getCode(), "后端服务参数ApiId不同");
        BizAssert.validParam(diffRequestParamIdCount == size, BizCodes.INVALID_PARAM.getCode(), "前后端参数映射有重复");
        BizAssert.validParam(diffNameCount == size, BizCodes.INVALID_PARAM.getCode(), "后端服务参数名相同");
        BizAssert.validParam(diffSortCount == size, BizCodes.INVALID_PARAM.getCode(), "后端服务参数排序值相同");
        for (ServiceParam serviceParam : serviceParamList) {
            BizAssert.validParam(ParamLocationEnum.getByCode(serviceParam.getLocation()) != null,
                    BizCodes.INVALID_PARAM.getCode(), "常量参数位置非法");
            BizAssert.validParam(StringUtils.hasText(serviceParam.getName())
                            && serviceParam.getName().length() < 80,
                    BizCodes.INVALID_PARAM.getCode(), "常量参数名称非法");
        }
    }

    /**
     * 批量添加后端服务请求参数
     *
     * @param serviceParamList 后端服务请求参数列表
     * @return 是否添加成功
     */
    public boolean addServiceParamBatch(List<ServiceParam> serviceParamList) {
        if (serviceParamList.size() == 0) {
            return true;
        }
        this.doValidation(serviceParamList);
        String apiId = serviceParamList.get(0).getApiId();
        BizAssert.validParam(this.serviceParamDao.getListByApiId(apiId).size() == 0,
                BizCodes.ERROR_INSERT.getCode(), "必须先清空原有后端服务请求参数");
        int count = 0;
        try {
            count = this.serviceParamDao.addBatch(serviceParamList);
        } catch (Exception e) {
            BizAssert.pass(count == serviceParamList.size(), BizCodes.ERROR_DELETE);
        }
        BizAssert.pass(count == serviceParamList.size(), BizCodes.ERROR_DELETE);
        return true;
    }

    /**
     * 根据APIId删除其后端服务请求参数
     *
     * @param apiId APIId
     * @return 是否删除成功
     */
    public boolean delServiceParamByApiId(String apiId) {
        try {
            this.serviceParamDao.delByApiId(apiId);
        } catch (Exception e) {
            BizAssert.justFailed(BizCodes.ERROR_DELETE);
        }
        return true;
    }

    /**
     * 根据APIId获取其后端服务参数
     *
     * @param apiId APIId
     * @return {@link List<ServiceParamV1>}
     */
    public List<ServiceParamV1> getServiceParamV1ListByApiId(String apiId) {
        return this.serviceParamDao.getListByApiId(apiId)
                .stream().map(s -> BeanUtils.convertType(s, ServiceParamV1.class)).collect(Collectors.toList());
    }
}
