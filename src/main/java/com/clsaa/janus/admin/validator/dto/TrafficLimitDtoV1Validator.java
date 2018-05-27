package com.clsaa.janus.admin.validator.dto;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.constant.request.TrafficLimitUnitEnum;
import com.clsaa.janus.admin.entity.dto.v1.TrafficLimitDtoV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.validator.commen.IDValidator;
import com.clsaa.janus.admin.validator.commen.NormalDescriptionValidator;
import com.clsaa.janus.admin.validator.commen.NormalNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author 任贵杰
 * @version v1
 * @summary 限流策略参数校验器
 * @since 2018/5/27
 */
@Component
public class TrafficLimitDtoV1Validator implements Validator {
    @Autowired
    private IDValidator idValidator;
    @Autowired
    private NormalNameValidator normalNameValidator;
    @Autowired
    private NormalDescriptionValidator normalDescriptionValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return TrafficLimitDtoV1.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BizAssert.validParam(target != null, BizCodes.INVALID_PARAM.getCode(), "传入参数为空");
        TrafficLimitDtoV1 trafficLimitDtoV1 = (TrafficLimitDtoV1) target;
        this.idValidator.validate(trafficLimitDtoV1.getRegionId(), errors);
        this.normalNameValidator.validate(trafficLimitDtoV1.getName(), errors);
        this.normalDescriptionValidator.validate(trafficLimitDtoV1.getDescription(), errors);
        BizAssert.validParam(TrafficLimitUnitEnum.getByCode(trafficLimitDtoV1.getUnit()) != null,
                BizCodes.INVALID_PARAM.getCode(), "限流策略时间单位非法");
        if (trafficLimitDtoV1.getApiLimit() == null) {
            trafficLimitDtoV1.setApiLimit(Integer.MAX_VALUE);
        }
        if (trafficLimitDtoV1.getAppLimit() == null) {
            trafficLimitDtoV1.setAppLimit(Integer.MAX_VALUE);
        }
        BizAssert.validParam(trafficLimitDtoV1.getApiLimit() > 0,
                BizCodes.INVALID_PARAM.getCode(), "限流策略-API限流数非法,必须为0-2147483647(Integer.MAX_VALUE)");
        BizAssert.validParam(trafficLimitDtoV1.getAppLimit() > 0,
                BizCodes.INVALID_PARAM.getCode(), "限流策略-APP限流数非法,必须为0-2147483647(Integer.MAX_VALUE)");
        BizAssert.validParam(trafficLimitDtoV1.getApiLimit() > trafficLimitDtoV1.getAppLimit(),
                BizCodes.INVALID_PARAM.getCode(), "限流策略-限流数非法,API限流数应大于APP限流数");
    }
}
