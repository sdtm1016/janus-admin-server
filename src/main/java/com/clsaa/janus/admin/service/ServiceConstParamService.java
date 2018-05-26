package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.constant.common.ParamLocationEnum;
import com.clsaa.janus.admin.dao.ServiceConstParamDao;
import com.clsaa.janus.admin.entity.po.ServiceConstParam;
import com.clsaa.janus.admin.entity.vo.v1.ServiceConstParamV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
public class ServiceConstParamService {
    @Autowired
    private ServiceConstParamDao serviceConstParamDao;

    private void doValidation(List<ServiceConstParam> serviceConstParamList) {
        long size = serviceConstParamList.size();
        long diffIdCount = serviceConstParamList.stream().map(ServiceConstParam::getId).distinct().count();
        long diffApiIdCount = serviceConstParamList.stream().map(ServiceConstParam::getApiId).distinct().count();
        long diffNameCount = serviceConstParamList.stream().map(ServiceConstParam::getName).distinct().count();
        long diffSortCount = serviceConstParamList.stream().map(ServiceConstParam::getSort).distinct().count();
        BizAssert.validParam(diffIdCount == size, BizCodes.INVALID_PARAM.getCode(), "后端常量参数有重复ID");
        BizAssert.validParam(diffApiIdCount == 1, BizCodes.INVALID_PARAM.getCode(), "后端常量参数ApiId不同");
        BizAssert.validParam(diffNameCount == size, BizCodes.INVALID_PARAM.getCode(), "后端常量参数名相同");
        BizAssert.validParam(diffSortCount == size, BizCodes.INVALID_PARAM.getCode(), "后端常量参数排序值相同");
        for (ServiceConstParam serviceConstParam : serviceConstParamList) {
            BizAssert.validParam(ParamLocationEnum.getByCode(serviceConstParam.getLocation()) != null
                            && (ParamLocationEnum.getByCode(serviceConstParam.getLocation()) == ParamLocationEnum.HEAD
                            || ParamLocationEnum.getByCode(serviceConstParam.getLocation()) == ParamLocationEnum.QUERY),
                    BizCodes.INVALID_PARAM.getCode(), "常量参数位置非法");
            BizAssert.validParam(StringUtils.hasText(serviceConstParam.getValue())
                            && serviceConstParam.getDescription().length() < 255,
                    BizCodes.INVALID_PARAM.getCode(), "常量参数值非法");
            BizAssert.validParam(StringUtils.hasText(serviceConstParam.getName())
                            && serviceConstParam.getName().length() < 80,
                    BizCodes.INVALID_PARAM.getCode(), "常量参数名称非法");
            BizAssert.validParam(StringUtils.hasText(serviceConstParam.getDescription())
                            && serviceConstParam.getDescription().length() < 180,
                    BizCodes.INVALID_PARAM.getCode(), "常量参数描述非法");
        }
    }

    /**
     * 批量添加后端服务常量参数
     *
     * @param serviceConstParamList 后端服务常量参数列表
     * @return 是否添加成功
     */
    public boolean addServiceConstParamBatch(List<ServiceConstParam> serviceConstParamList) {
        if (serviceConstParamList.size() == 0) {
            return true;
        }
        this.doValidation(serviceConstParamList);
        String apiId = serviceConstParamList.get(0).getApiId();
        BizAssert.validParam(this.serviceConstParamDao.getListByApiId(apiId).size() == 0,
                BizCodes.ERROR_INSERT.getCode(), "添加之前必须先清空API的全部常量参数");
        int count = 0;
        try {
            count = this.serviceConstParamDao.addBatch(serviceConstParamList);
        } catch (Exception e) {
            BizAssert.pass(count == serviceConstParamList.size(), BizCodes.ERROR_INSERT);
        }
        BizAssert.pass(count == serviceConstParamList.size(), BizCodes.ERROR_INSERT);
        return true;
    }

    /**
     * 通过APIId删除其后端常量参数
     *
     * @param apiId APIId
     * @return 是否删除成功
     */
    public boolean delServiceConstParamByApiId(String apiId) {
        try {
            this.serviceConstParamDao.delByApiId(apiId);
        } catch (Exception e) {
            BizAssert.justFailed(BizCodes.ERROR_DELETE);
        }
        return true;
    }

    /**
     * 根据APIId查询其常量参数列表
     *
     * @param apiId
     * @return {@link List<ServiceConstParamV1>}
     */
    public List<ServiceConstParamV1> getServiceConstParamV1ListByApiId(String apiId) {
        return this.serviceConstParamDao.getListByApiId(apiId)
                .stream().map(s -> BeanUtils.convertType(s, ServiceConstParamV1.class)).collect(Collectors.toList());
    }
}
