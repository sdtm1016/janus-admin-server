package com.clsaa.janus.admin.validator.dto;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.entity.dto.v1.ApiDeployDtoV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.validator.commen.IDValidator;
import com.clsaa.janus.admin.validator.commen.NormalDescriptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/30
 */
@Component
public class ApiDeployDtoV1Validator implements Validator {
    @Autowired
    private IDValidator idValidator;
    @Autowired
    private NormalDescriptionValidator normalDescriptionValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return ApiDeployDtoV1.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BizAssert.validParam(target != null, BizCodes.INVALID_PARAM.getCode(), "传入参数为空");
        ApiDeployDtoV1 apiDeployDtoV1 = (ApiDeployDtoV1) target;
        this.idValidator.validate(apiDeployDtoV1.getApiId(), errors);
        this.idValidator.validate(apiDeployDtoV1.getEnvironmentId(), errors);
        this.normalDescriptionValidator.validate(apiDeployDtoV1.getDescription(), errors);
    }
}
