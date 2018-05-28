package com.clsaa.janus.admin.validator.commen;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.result.BizAssert;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/28
 */
@Component
public class IPV4Validator implements Validator {
    public static final Pattern IP_PATTERN = Pattern.compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");

    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BizAssert.validParam(target != null, BizCodes.INVALID_PARAM.getCode(), "IP非法");
        String ip = (String) target;
        BizAssert.validParam(IP_PATTERN.matcher(ip).matches(), BizCodes.INVALID_PARAM.getCode(), "IP非法");
    }
}
