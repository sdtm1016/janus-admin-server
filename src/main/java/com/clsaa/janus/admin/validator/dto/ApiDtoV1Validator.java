package com.clsaa.janus.admin.validator.dto;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.constant.request.ApiVisibilityEnum;
import com.clsaa.janus.admin.constant.request.AuthTypeEnum;
import com.clsaa.janus.admin.constant.request.SignMethodEnum;
import com.clsaa.janus.admin.constant.response.ResultTypeEnum;
import com.clsaa.janus.admin.entity.dto.v1.ApiDtoV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.validator.commen.IDValidator;
import com.clsaa.janus.admin.validator.commen.NormalDescriptionValidator;
import com.clsaa.janus.admin.validator.commen.NormalNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ApiDtoV1Validator implements Validator {
    @Autowired
    private IDValidator idValidator;
    @Autowired
    private NormalNameValidator normalNameValidator;
    @Autowired
    private NormalDescriptionValidator normalDescriptionValidator;
    @Autowired
    private RequestConfigDtoV1Validation requestConfigDtoV1Validation;
    @Autowired
    private ServiceConfigDtoV1Validator serviceConfigDtoV1Validator;

    @Override
    public boolean supports(Class<?> clazz) {
        return ApiDtoV1.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BizAssert.validParam(target != null, BizCodes.INVALID_PARAM.getCode(), "传入参数为空");
        ApiDtoV1 apiDtoV1 = (ApiDtoV1) target;
        //API自身信息
        this.idValidator.validate(apiDtoV1.getGroupId(), errors);
        this.idValidator.validate(apiDtoV1.getRegionId(), errors);
        this.normalNameValidator.validate(apiDtoV1.getName(), errors);
        this.normalDescriptionValidator.validate(apiDtoV1.getDescription(), errors);
        BizAssert.validParam(StringUtils.hasText(apiDtoV1.getSuccessResultSample()) &&
                apiDtoV1.getSuccessResultSample().length() <= 255, BizCodes.INVALID_PARAM.getCode(), "失败结果长度在1-255之间");
        BizAssert.validParam(StringUtils.hasText(apiDtoV1.getFailResultSample()) &&
                apiDtoV1.getFailResultSample().length() <= 255, BizCodes.INVALID_PARAM.getCode(), "失败结果长度在1-255之间");
        BizAssert.validParam(AuthTypeEnum.getByCode(apiDtoV1.getAuthType()) != null,
                BizCodes.INVALID_PARAM.getCode(), "认证方式非法");
        if (AuthTypeEnum.getByCode(apiDtoV1.getAuthType()) != AuthTypeEnum.无认证) {
            BizAssert.validParam(SignMethodEnum.getByCode(apiDtoV1.getSignMethod()) != null,
                    BizCodes.INVALID_PARAM.getCode(), "签名方法非法");
        }
        BizAssert.validParam(ApiVisibilityEnum.getByCode(apiDtoV1.getVisibility()) != null,
                BizCodes.INVALID_PARAM.getCode(), "API可见性非法");
        BizAssert.validParam(ResultTypeEnum.getByCode(apiDtoV1.getResultType()) != null,
                BizCodes.INVALID_PARAM.getCode(), "API返回类型非法");

        this.requestConfigDtoV1Validation.validate(apiDtoV1.getRequestConfig(), errors);
        this.serviceConfigDtoV1Validator.validate(apiDtoV1.getServiceConfig(), errors);
    }
}
