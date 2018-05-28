package com.clsaa.janus.admin.validator.dto;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.constant.request.IPStrategyTypeEnum;
import com.clsaa.janus.admin.entity.dto.v1.IpStrategyDtoV1;
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
 * @summary
 * @since 2018/5/28
 */
@Component
public class IpStrategyDtoV1Validator implements Validator {
    @Autowired
    private IDValidator idValidator;
    @Autowired
    private NormalNameValidator normalNameValidator;
    @Autowired
    private NormalDescriptionValidator normalDescriptionValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return IpStrategyDtoV1.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BizAssert.validParam(target != null, BizCodes.INVALID_PARAM.getCode(), "参数为空");
        IpStrategyDtoV1 ipStrategyDtoV1 = (IpStrategyDtoV1) target;
        this.idValidator.validate(ipStrategyDtoV1.getRegionId(), errors);
        this.normalNameValidator.validate(ipStrategyDtoV1.getDescription(), errors);
        this.normalDescriptionValidator.validate(ipStrategyDtoV1.getDescription(), errors);
        BizAssert.validParam(IPStrategyTypeEnum.getByCode(ipStrategyDtoV1.getType()) != null,
                BizCodes.INVALID_PARAM.getCode(), "IP访问策略类型非法");
    }
}
