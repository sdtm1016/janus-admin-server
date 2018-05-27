package com.clsaa.janus.admin.validator.dto;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.entity.dto.v1.AppDtoV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.validator.commen.NormalDescriptionValidator;
import com.clsaa.janus.admin.validator.commen.NormalNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/27
 */
@Component
public class AppDtoV1Validator implements Validator {
    @Autowired
    private NormalNameValidator normalNameValidator;
    @Autowired
    private NormalDescriptionValidator normalDescriptionValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return AppDtoV1.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BizAssert.validParam(target != null, BizCodes.INVALID_PARAM.getCode(), "传入参数为空");
        AppDtoV1 appDtoV1 = (AppDtoV1) target;
        this.normalNameValidator.validate(appDtoV1.getName(), errors);
        this.normalDescriptionValidator.validate(appDtoV1.getDescription(), errors);
    }
}
