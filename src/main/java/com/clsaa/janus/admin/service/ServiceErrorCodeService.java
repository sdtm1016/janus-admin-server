package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.dao.ServiceErrorCodeDao;
import com.clsaa.janus.admin.entity.po.ServiceErrorCode;
import com.clsaa.janus.admin.entity.vo.v1.ServiceErrorCodeV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务端可能抛出的错误码 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class ServiceErrorCodeService {
    @Autowired
    private ServiceErrorCodeDao serviceErrorCodeDao;

    /**
     * 参数校验
     */
    private void doValidation(List<ServiceErrorCode> serviceErrorCodeList) {
        long size = serviceErrorCodeList.size();
        long diffIdCount = serviceErrorCodeList.stream().map(ServiceErrorCode::getId).distinct().count();
        long diffApiIdCount = serviceErrorCodeList.stream().map(ServiceErrorCode::getApiId).distinct().count();
        long diffCodeCount = serviceErrorCodeList.stream().map(ServiceErrorCode::getCode).distinct().count();
        long diffSortCount = serviceErrorCodeList.stream().map(ServiceErrorCode::getSort).distinct().count();
        BizAssert.validParam(diffIdCount == size, BizCodes.INVALID_PARAM.getCode(), "错误码有重复ID");
        BizAssert.validParam(diffApiIdCount == 1, BizCodes.INVALID_PARAM.getCode(), "错误码ApiId不同");
        BizAssert.validParam(diffCodeCount == size, BizCodes.INVALID_PARAM.getCode(), "错误码相同");
        BizAssert.validParam(diffSortCount == size, BizCodes.INVALID_PARAM.getCode(), "错误码排序值相同");
        for (ServiceErrorCode serviceErrorCode : serviceErrorCodeList) {
            BizAssert.validParam(StringUtils.hasText(serviceErrorCode.getCode())
                            && serviceErrorCode.getDescription().length() < 20,
                    BizCodes.INVALID_PARAM.getCode(), "错误码非法");
            BizAssert.validParam(StringUtils.hasText(serviceErrorCode.getMessage())
                            && serviceErrorCode.getMessage().length() < 80,
                    BizCodes.INVALID_PARAM.getCode(), "错误码信息非法");
            BizAssert.validParam(StringUtils.hasText(serviceErrorCode.getDescription())
                            && serviceErrorCode.getDescription().length() < 180,
                    BizCodes.INVALID_PARAM.getCode(), "错误码描述非法");
        }
    }


    /**
     * 批量添加后端服务错误码,此方法不校验参数正确性
     *
     * @param serviceErrorCodeList 后端服务错误码列表
     * @return 是否成功
     */
    public boolean addServiceErrorCodeBatch(List<ServiceErrorCode> serviceErrorCodeList) {
        if (serviceErrorCodeList.size() == 0) {
            return true;
        }
        this.doValidation(serviceErrorCodeList);
        String apiId = serviceErrorCodeList.get(0).getApiId();
        int count = 0;
        try {
            count = this.serviceErrorCodeDao.addBatch(serviceErrorCodeList);
        } catch (Exception e) {
            BizAssert.pass(count == serviceErrorCodeList.size(), BizCodes.ERROR_INSERT);
        }
        BizAssert.pass(count == serviceErrorCodeList.size(), BizCodes.ERROR_INSERT);
        return true;
    }

    /**
     * 根据APIId删除其错误码
     *
     * @param apiId APIId
     * @return 是否删除成功
     */
    public boolean delServiceErrorCodeByApiId(String apiId) {
        try {
            this.serviceErrorCodeDao.delByApiId(apiId);
        } catch (Exception e) {
            BizAssert.justFailed(BizCodes.ERROR_DELETE);
        }
        return true;
    }

    /**
     * 根据APIId获取其服务错误码
     *
     * @param apiId APIId
     * @return {@link List<ServiceErrorCodeV1>}
     */
    public List<ServiceErrorCodeV1> getServiceErrorCodeV1ListByApiId(String apiId) {
        return this.serviceErrorCodeDao.getListByApiId(apiId)
                .stream().map(s -> BeanUtils.convertType(s, ServiceErrorCodeV1.class)).collect(Collectors.toList());
    }
}
