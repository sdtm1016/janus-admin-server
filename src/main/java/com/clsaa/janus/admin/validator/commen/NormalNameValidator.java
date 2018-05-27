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
 * @summary 普通名称校验器
 * @since 2018/5/26
 */
@Component
public class NormalNameValidator implements Validator {
    public static final java.util.regex.Pattern NORMAL_NAME_PATTERN = Pattern.compile("^[\u4e00-\u9fa5A-Za-z]+[\u4e00-\u9fa5A-Za-z0-9_]{3,50}");


    @Override
    public boolean supports(Class<?> cls) {
        return String.class.equals(cls);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BizAssert.validParam(o != null, BizCodes.INVALID_PARAM.getCode(),
                "名称非法,名称必须唯一，支持汉字、英文字母、数字、英文格式的下划线，必须以英文字母或汉字开头，4~50个字符");
        String name = (String) o;
        BizAssert.validParam(NORMAL_NAME_PATTERN.matcher(name).matches(), BizCodes.INVALID_PARAM.getCode(),
                "名称非法,名称必须唯一，支持汉字、英文字母、数字、英文格式的下划线，必须以英文字母或汉字开头，4~50个字符");
    }
}
