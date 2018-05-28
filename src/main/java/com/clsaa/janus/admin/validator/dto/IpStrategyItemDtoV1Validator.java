package com.clsaa.janus.admin.validator.dto;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.entity.dto.v1.IpStrategyItemDtoV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.validator.commen.IDValidator;
import com.clsaa.janus.admin.validator.commen.IPV4Validator;
import com.clsaa.janus.admin.validator.commen.NormalNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/28
 */
@Component
public class IpStrategyItemDtoV1Validator implements Validator {
    @Autowired
    private IDValidator idValidator;
    @Autowired
    private NormalNameValidator normalNameValidator;
    @Autowired
    private IPV4Validator ipv4Validator;

    @Override
    public boolean supports(Class<?> clazz) {
        return IpStrategyItemDtoV1.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BizAssert.validParam(target != null, BizCodes.INVALID_PARAM.getCode(), "参数为空");
        IpStrategyItemDtoV1 ipStrategyItemDtoV1 = (IpStrategyItemDtoV1) target;
        this.normalNameValidator.validate(ipStrategyItemDtoV1.getName(), errors);
        this.idValidator.validate(ipStrategyItemDtoV1.getIpStrategyId(), errors);
        List<String> ipSegList = Arrays.asList(ipStrategyItemDtoV1.getCidrIp().split(";"));
        BizAssert.validParam(ipSegList != null && ipSegList.size() > 0, BizCodes.INVALID_PARAM.getCode(), "参数为空");

        for (String ipSeg : ipSegList) {
            if (ipSeg.indexOf('/') != -1) {
                this.ipv4Validator.validate(ipSeg.substring(0, ipSeg.indexOf('/')), errors);
                BizAssert.validParam(ipSeg.length() > ipSeg.indexOf('/'), BizCodes.INVALID_PARAM.getCode(), "ip段格式错误");
                try {
                    String lenStr = ipSeg.substring(ipSeg.indexOf('/') + 1);
                    Integer len = new Integer(lenStr);
                    BizAssert.validParam(len > 0 && len < 64, BizCodes.INVALID_PARAM.getCode(), "ip段格式错误");
                } catch (Exception e) {
                    BizAssert.validParam(false, BizCodes.INVALID_PARAM.getCode(), "ip段格式错误");
                }
            } else {
                this.ipv4Validator.validate(ipSeg, errors);
            }
        }
    }
}
