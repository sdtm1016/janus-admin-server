package com.clsaa.janus.admin.validator.commen;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.util.UUIDUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * @author 任贵杰
 * @version v1
 * @summary ID校验器
 * @since 2018/5/26
 */
@Component
public class IDValidator implements Validator {
    public static final Pattern ID_PATTERN = Pattern.compile("[A-Za-z0-9]{32}");

    @Override
    public boolean supports(Class<?> cls) {
        return String.class.equals(cls);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BizAssert.validParam(o != null, BizCodes.INVALID_PARAM.getCode(), "ID非法");
        String id = (String) o;
        BizAssert.validParam(ID_PATTERN.matcher(id).matches(), BizCodes.INVALID_PARAM.getCode(), "ID非法");
    }
}
