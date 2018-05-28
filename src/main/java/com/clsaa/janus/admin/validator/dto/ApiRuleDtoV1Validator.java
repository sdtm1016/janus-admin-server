package com.clsaa.janus.admin.validator.dto;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.constant.request.ApiRuleTypeEnum;
import com.clsaa.janus.admin.entity.dto.v1.ApiRuleDtoV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.validator.commen.IDValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/28
 */
@Component
public class ApiRuleDtoV1Validator implements Validator {
    @Autowired
    private IDValidator idValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return ApiRuleDtoV1.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BizAssert.validParam(target != null, BizCodes.INVALID_PARAM.getCode(), "传入参数为空");
        ApiRuleDtoV1 apiRuleDtoV1 = (ApiRuleDtoV1) target;
        BizAssert.validParam(ApiRuleTypeEnum.getByCode(apiRuleDtoV1.getType()) != null,
                BizCodes.INVALID_PARAM.getCode(), "API规则类型错误");
        this.idValidator.validate(apiRuleDtoV1.getRuleId(), errors);
        this.idValidator.validate(apiRuleDtoV1.getEnvironmentId(), errors);
        for (String apiId : apiRuleDtoV1.getApiIdList()) {
            this.idValidator.validate(apiId, errors);
        }
    }

}
