package com.clsaa.janus.admin.validator.commen;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.result.BizAssert;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author 任贵杰
 * @version v1
 * @summary 普通描述校验器
 * @since 2018/5/26
 */
@Component
public class NormalDescriptionValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
        return String.class.equals(cls);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BizAssert.validParam(o != null, BizCodes.INVALID_PARAM.getCode(), "描述为空");
        String description = (String) o;
        BizAssert.validParam(description.length() < 180, BizCodes.INVALID_PARAM.getCode(),
                "描述不符合要求,请控制在1-180个字符");
    }

}
