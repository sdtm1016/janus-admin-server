package com.clsaa.janus.admin.validator.dto;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.constant.common.HttpMethodEnum;
import com.clsaa.janus.admin.constant.common.ProtocolEnum;
import com.clsaa.janus.admin.constant.request.BodyFormatEnum;
import com.clsaa.janus.admin.constant.request.ParamModeEnum;
import com.clsaa.janus.admin.constant.request.WSTypeEnum;
import com.clsaa.janus.admin.entity.dto.v1.RequestConfigDtoV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.validator.commen.NormalDescriptionValidator;
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
public class RequestConfigDtoV1Validation implements Validator {
    @Autowired
    private NormalDescriptionValidator normalDescriptionValidator;
    @Override
    public boolean supports(Class<?> clazz) {
        return RequestConfigDtoV1.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BizAssert.validParam(target != null, BizCodes.INVALID_PARAM.getCode(), "传入参数为空");
        RequestConfigDtoV1 requestConfigDtoV1 = (RequestConfigDtoV1) target;
        this.normalDescriptionValidator.validate(requestConfigDtoV1.getBodyDescription(),errors);
        BizAssert.validParam(StringUtils.hasText(requestConfigDtoV1.getBodyDescription())
                && requestConfigDtoV1.getBodyDescription().length() <= 180, BizCodes.INVALID_PARAM.getCode(), "bodyDescription非法");
        BizAssert.validParam(HttpMethodEnum.getByCode(requestConfigDtoV1.getHttpMethod()) != null, BizCodes.INVALID_PARAM.getCode(), "HTTPMethod非法");
        BizAssert.validParam(ParamModeEnum.getByCode(requestConfigDtoV1.getMode()) != null, BizCodes.INVALID_PARAM.getCode(), "入参请求模式非法");
        BizAssert.validParam(StringUtils.hasText(ProtocolEnum.getSupportedProtocols(requestConfigDtoV1.getProtocol())), BizCodes.INVALID_PARAM.getCode(), "请求协议非法");
        if (ProtocolEnum.enabledWS(requestConfigDtoV1.getProtocol())) {
            BizAssert.validParam(WSTypeEnum.getByCode(requestConfigDtoV1.getWsType()) != null, BizCodes.INVALID_PARAM.getCode(), "双向通信类型非法");
        }
        if (requestConfigDtoV1.getBodyFormat() != null) {
            BizAssert.validParam(BodyFormatEnum.getByCode(requestConfigDtoV1.getBodyFormat()) != null, BizCodes.INVALID_PARAM.getCode(), "入参请求体格式类型非法");
        }
    }
}
