package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.constant.common.ParamLocationEnum;
import com.clsaa.janus.admin.constant.common.ParamTypeEnum;
import com.clsaa.janus.admin.dao.RequestParamDao;
import com.clsaa.janus.admin.entity.po.RequestParam;
import com.clsaa.janus.admin.entity.vo.RequestParamV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 客户端到网关的请求参数 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class RequestParamService {
    @Autowired
    private RequestParamDao requestParamDao;

    /**
     * 参数校验
     */
    private void doValidation(List<RequestParam> requestParamList) {
        long size = requestParamList.size();
        long diffIdCount = requestParamList.stream().map(RequestParam::getId).distinct().count();
        long diffApiIdCount = requestParamList.stream().map(RequestParam::getApiId).distinct().count();
        long diffNameCount = requestParamList.stream().map(RequestParam::getName).distinct().count();
        long diffSortCount = requestParamList.stream().map(RequestParam::getSort).distinct().count();
        BizAssert.validParam(diffIdCount == size, BizCodes.INVALID_PARAM.getCode(), "请求参数有重复ID");
        BizAssert.validParam(diffApiIdCount == 1, BizCodes.INVALID_PARAM.getCode(), "请求参数ApiId不同");
        BizAssert.validParam(diffNameCount == size, BizCodes.INVALID_PARAM.getCode(), "请求参数名相同");
        BizAssert.validParam(diffSortCount == size, BizCodes.INVALID_PARAM.getCode(), "请求参数排序值相同");
        for (RequestParam requestParam : requestParamList) {
            BizAssert.validParam(ParamLocationEnum.getByCode(requestParam.getLocation()) != null,
                    BizCodes.INVALID_PARAM.getCode(), "参数位置非法");
            BizAssert.validParam(ParamTypeEnum.getByCode(requestParam.getType()) != null,
                    BizCodes.INVALID_PARAM.getCode(), "参数类型非法");
            BizAssert.validParam(StringUtils.hasText(requestParam.getName()) && requestParam.getName().length() < 80,
                    BizCodes.INVALID_PARAM.getCode(), "参数名称非法");
            BizAssert.validParam(StringUtils.hasText(requestParam.getDescription()) && requestParam.getDescription().length() < 180,
                    BizCodes.INVALID_PARAM.getCode(), "参数描述非法");
        }
    }

    /**
     * 批量添加请求参数,此方法由调用方负责参数校验
     *
     * @param requestParamList 请求参数列表
     * @return 是否添加成功
     */
    public boolean addRequestParamBatch(List<RequestParam> requestParamList) {
        if (requestParamList.size() == 0) {
            return true;
        }
        this.doValidation(requestParamList);
        String apiId = requestParamList.get(0).getApiId();
        BizAssert.validParam(this.requestParamDao.getListByApiId(apiId).size() == 0,
                BizCodes.INVALID_PARAM.getCode(), "必须先清空Api请求参数列表才可以添加");
        int count = 0;
        try {
            count = this.requestParamDao.addBatch(requestParamList);
        } catch (Exception e) {
            BizAssert.pass(count == requestParamList.size(), BizCodes.ERROR_INSERT);
        }
        BizAssert.pass(count == requestParamList.size(), BizCodes.ERROR_INSERT);
        return true;
    }

    /**
     * 根据ApiId删除其全部请求参数
     *
     * @param apiId APIid
     * @return 是否删除成功
     */
    public boolean delReuqstParamByApiId(String apiId) {
        try {
            this.requestParamDao.delByApiId(apiId);
        } catch (Exception e) {
            BizAssert.justFailed(BizCodes.ERROR_DELETE);
        }
        return true;
    }

    /**
     * 根据ApiId查询其全部请求参数
     *
     * @param apiId APIId
     * @return {@link List<RequestParamV1>}
     */
    public List<RequestParamV1> getRequestParamV1ListByApiId(String apiId) {
        return this.requestParamDao.getListByApiId(apiId)
                .stream().map(r -> BeanUtils.convertType(r, RequestParamV1.class)).collect(Collectors.toList());
    }
}
