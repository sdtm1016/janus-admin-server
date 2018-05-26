package com.clsaa.janus.admin.validator.dto;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.entity.dto.v1.ServiceAuthDtoV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.validator.commen.IDValidator;
import com.clsaa.janus.admin.validator.commen.NormalDescriptionValidator;
import com.clsaa.janus.admin.validator.commen.NormalNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/26
 */
@Component
public class ServiceAuthDtoV1Validator implements Validator {
    public static final Pattern AK_PATTERN = Pattern.compile("^[^_]+[A-Za-z0-9_]{6,80}");
    public static final Pattern AS_PATTERN = Pattern.compile("^[A-Za-z0-9]+[A-Za-z0-9_@#!*]{6,255}");

    @Autowired
    private IDValidator idValidator;
    @Autowired
    private NormalNameValidator normalNameValidator;
    @Autowired
    private NormalDescriptionValidator normalDescriptionValidator;

    @Override
    public boolean supports(Class<?> cls) {
        return ServiceAuthDtoV1.class.equals(cls);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BizAssert.validParam(o != null, BizCodes.INVALID_PARAM.getCode(), "传入参数为空");
        ServiceAuthDtoV1 serviceAuthDtoV1 = (ServiceAuthDtoV1) o;
        this.idValidator.validate(serviceAuthDtoV1.getRegionId(), errors);
        this.normalNameValidator.validate(serviceAuthDtoV1.getName(), errors);
        this.normalDescriptionValidator.validate(serviceAuthDtoV1.getDescription(), errors);
        BizAssert.validParam(AK_PATTERN.matcher(serviceAuthDtoV1.getAccessKey()).matches(),
                BizCodes.INVALID_PARAM.getCode(),
                "AccessKey格式不符合要求,支持英文字母、数字、英文格式的下划线，必须以英文字母或者数字开头，6~80个字符");
        BizAssert.validParam(AS_PATTERN.matcher(serviceAuthDtoV1.getAccessSecret()).matches(),
                BizCodes.INVALID_PARAM.getCode(),
                "AccessSecret格式不符合要求,支持英文字母、数字、英文格式的下划线，必须以英文字母或者数字开头，6~255个字符");
    }
}
