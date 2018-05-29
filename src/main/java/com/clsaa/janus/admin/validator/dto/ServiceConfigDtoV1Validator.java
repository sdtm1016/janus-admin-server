package com.clsaa.janus.admin.validator.dto;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.constant.common.HttpMethodEnum;
import com.clsaa.janus.admin.constant.service.ContentTypeCategoryEnum;
import com.clsaa.janus.admin.constant.service.ServiceTypeEnum;
import com.clsaa.janus.admin.entity.dto.v1.ServiceConfigDtoV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.util.validator.AddressValidator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/30
 */
@Component
public class ServiceConfigDtoV1Validator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ServiceConfigDtoV1.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BizAssert.validParam(target != null, BizCodes.INVALID_PARAM.getCode(), "传入参数为空");
        ServiceConfigDtoV1 serviceConfigDtoV1 = (ServiceConfigDtoV1) target;
        BizAssert.validParam(ServiceTypeEnum.getByCode(serviceConfigDtoV1.getType()) != null, BizCodes.INVALID_PARAM.getCode(), "后端服务类型非法");
        BizAssert.validParam(ContentTypeCategoryEnum.getByCode(serviceConfigDtoV1.getContentTypeCategory()) != null, BizCodes.INVALID_PARAM.getCode(), "ContentType分类非法");
        BizAssert.validParam(StringUtils.hasText(serviceConfigDtoV1.getContentTypeValue()), BizCodes.INVALID_PARAM.getCode(), "ContentType非法");
        BizAssert.validParam(AddressValidator.isAddress(serviceConfigDtoV1.getAddress()), BizCodes.INVALID_PARAM.getCode(), "后端地址非法");
        BizAssert.validParam(HttpMethodEnum.getByCode(serviceConfigDtoV1.getHttpMethod()) != null, BizCodes.INVALID_PARAM.getCode(), "HTTP Method非法");
        BizAssert.validParam(StringUtils.hasText(serviceConfigDtoV1.getPath()), BizCodes.INVALID_PARAM.getCode(), "请求路径非法");
        BizAssert.validParam(serviceConfigDtoV1.getTimeout() > 0, BizCodes.INVALID_PARAM.getCode(), "超时时间错误");
        if (serviceConfigDtoV1.getMock()) {
            BizAssert.validParam(StringUtils.hasText(serviceConfigDtoV1.getMockResult()), BizCodes.INVALID_PARAM.getCode(), "MOCK数据非法");
        } else {
            BizAssert.validParam(StringUtils.isEmpty(serviceConfigDtoV1.getMockResult()), BizCodes.INVALID_PARAM.getCode(), "MOCK数据非法");
        }
    }
}
