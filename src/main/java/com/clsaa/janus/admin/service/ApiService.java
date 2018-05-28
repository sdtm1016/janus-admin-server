package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.constant.request.ApiVisibilityEnum;
import com.clsaa.janus.admin.constant.request.AuthTypeEnum;
import com.clsaa.janus.admin.constant.request.ParamModeEnum;
import com.clsaa.janus.admin.constant.request.SignMethodEnum;
import com.clsaa.janus.admin.constant.response.ResultTypeEnum;
import com.clsaa.janus.admin.dao.ApiDao;
import com.clsaa.janus.admin.entity.dto.v1.ApiDtoV1;
import com.clsaa.janus.admin.entity.dto.v1.RequestParamDtoV1;
import com.clsaa.janus.admin.entity.dto.v1.ServiceParamDtoV1;
import com.clsaa.janus.admin.entity.po.*;
import com.clsaa.janus.admin.entity.vo.v1.ApiV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.result.Pagination;
import com.clsaa.janus.admin.util.BeanUtils;
import com.clsaa.janus.admin.util.TimestampUtil;
import com.clsaa.janus.admin.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * API信息 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class ApiService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private ApiDao apiDao;
    @Autowired
    private RequestConfigService requestConfigService;
    @Autowired
    private RequestParamService requestParamService;
    @Autowired
    private ServiceConfigService serviceConfigService;
    @Autowired
    private ServiceConstParamService serviceConstParamService;
    @Autowired
    private ServiceErrorCodeService serviceErrorCodeService;
    @Autowired
    private ServiceParamService serviceParamService;

    /**
     * 参数校验
     */
    private void doValidation(Integer authType, String signMethod, Integer visibility, Integer resultType) {
        BizAssert.validParam(AuthTypeEnum.getByCode(authType) != null,
                BizCodes.INVALID_PARAM.getCode(), "认证方式非法");
        if (AuthTypeEnum.getByCode(authType) != AuthTypeEnum.无认证) {
            BizAssert.validParam(SignMethodEnum.getByCode(signMethod) != null,
                    BizCodes.INVALID_PARAM.getCode(), "签名方法非法");
        }
        BizAssert.validParam(ApiVisibilityEnum.getByCode(visibility) != null,
                BizCodes.INVALID_PARAM.getCode(), "API可见性非法");
        BizAssert.validParam(ResultTypeEnum.getByCode(resultType) != null,
                BizCodes.INVALID_PARAM.getCode(), "API返回类型非法");
    }

    /**
     * 添加API信息
     *
     * @param apiDtoV1 API数据传输层对象
     * @return APIId
     */
    @Transactional(rollbackFor = Exception.class)
    public String addApi(String loginUserId, ApiDtoV1 apiDtoV1) {
        this.doValidation(apiDtoV1.getAuthType(), apiDtoV1.getSignMethod(), apiDtoV1.getVisibility(), apiDtoV1.getResultType());
        //添加API信息
        Api api = BeanUtils.convertType(apiDtoV1, Api.class);
        api.setId(UUIDUtil.getUUID());
        api.setMuser(loginUserId);
        api.setCuser(loginUserId);
        api.setCtime(TimestampUtil.now());
        api.setMtime(TimestampUtil.now());
        api.setStatus(Api.STATUS_OK);
        Mono.create(monoSink -> monoSink.success(this.apiDao.add(api)))
                .doOnError(e -> logger.error(e.getMessage()))
                .map(count -> {
                    BizAssert.pass((int) count == 1, BizCodes.ERROR_INSERT);
                    return null;
                })
                .onErrorMap(original -> {
                    BizAssert.justFailed(BizCodes.ERROR_INSERT);
                    return null;
                });

        //添加RequestConfig信息
        RequestConfig requestConfig = BeanUtils.convertType(apiDtoV1.getRequestConfig(), RequestConfig.class);
        Mono.create(sink -> sink.success(this.requestConfigService.addRequestConfig(api.getId(), requestConfig.getHttpMethod(),
                requestConfig.getMode(), requestConfig.getPath(), requestConfig.getProtocol(),
                requestConfig.getWsType(), requestConfig.getBodyFormat(), requestConfig.getBodyDescription())));

        //添加ServiceConfig信息
        ServiceConfig serviceConfig = BeanUtils.convertType(apiDtoV1.getServiceConfig(), ServiceConfig.class);
        Mono.create(sink -> sink.success(this.serviceConfigService.addServiceConfig(serviceConfig.getApiId(), serviceConfig.getType(),
                serviceConfig.getContentTypeCategory(), serviceConfig.getContentTypeValue(), serviceConfig.getAddress(),
                serviceConfig.getHttpMethod(), serviceConfig.getPath(), serviceConfig.getTimeout(), serviceConfig.getMock(),
                serviceConfig.getMockResult())));

        //添加ServiceConstParam信息
        if (apiDtoV1.getServiceConstParams() != null && apiDtoV1.getServiceConstParams().size() > 0) {
            List<ServiceConstParam> serviceConstParamList = apiDtoV1.getServiceConstParams()
                    .stream()
                    .map(scp -> BeanUtils.convertType(scp, ServiceConstParam.class))
                    .peek(scp -> scp.setId(UUIDUtil.getUUID()))
                    .peek(scp -> scp.setApiId(api.getId()))
                    .collect(Collectors.toList());
            Mono.create(sink -> sink.success(this.serviceConstParamService.addServiceConstParamBatch(serviceConstParamList)));
        }

        //添加ServiceErrorCode信息
        if (apiDtoV1.getServiceErrorCodes() != null && apiDtoV1.getServiceErrorCodes().size() > 0) {
            List<ServiceErrorCode> serviceErrorCodeList = apiDtoV1.getServiceErrorCodes()
                    .stream()
                    .map(sec -> BeanUtils.convertType(sec, ServiceErrorCode.class))
                    .peek(sec -> sec.setId(UUIDUtil.getUUID()))
                    .peek(sec -> sec.setApiId(api.getId()))
                    .collect(Collectors.toList());
            Mono.create(sink -> sink.success(this.serviceErrorCodeService.addServiceErrorCodeBatch(serviceErrorCodeList)));
        }

        List<RequestParam> requestParamList = null;
        //添加RequestParam信息
        if (apiDtoV1.getRequestParams() != null && apiDtoV1.getRequestParams().size() > 0) {
            requestParamList = apiDtoV1.getRequestParams()
                    .stream()
                    .map(rp -> BeanUtils.convertType(rp, RequestParam.class))
                    .peek(rp -> rp.setId(UUIDUtil.getUUID()))
                    .peek(rp -> rp.setApiId(api.getId()))
                    .collect(Collectors.toList());
            this.requestParamService.addRequestParamBatch(requestParamList);
        }
        //添加ServiceParam信息
        if (requestParamList != null) {
            if (ParamModeEnum.getByCode(apiDtoV1.getRequestConfig().getMode()) == ParamModeEnum.映射) {
                //准备RequestParamName->RequestParamId的Map
                Map<String, String> requestParamNameIdMap = requestParamList.stream()
                        .collect(Collectors.toMap(RequestParam::getName, RequestParam::getId));
                BizAssert.validParam(apiDtoV1.getServiceParams().size() == requestParamNameIdMap.size(),
                        BizCodes.INVALID_PARAM.getCode(), "请求参数与后端服务参数数量不匹配");
                long serviceParamInMapCount = apiDtoV1.getRequestParams()
                        .stream()
                        .map(RequestParamDtoV1::getName)
                        .filter(StringUtils::hasText)
                        .distinct()
                        .filter(requestParamNameIdMap::containsKey)
                        .count();
                BizAssert.validParam(serviceParamInMapCount == requestParamNameIdMap.size(),
                        BizCodes.INVALID_PARAM.getCode(), "后端服务参数的请求参数名未与请求参数列表中参数名对应");
                //准备ServiceParamName->RequestParamName的Map,ServiceParamName->RequestParamName->RequestParamId
                Map<String, String> serviceRequestParamNameMap = apiDtoV1.getServiceParams()
                        .stream()
                        .collect(Collectors.toMap(ServiceParamDtoV1::getName, ServiceParamDtoV1::getRequestParamName));
                //构造ServiceParamList,RequestParamId通过两个Map,ServiceParamName->RequestParamName->RequestParamId获得
                List<ServiceParam> serviceParamList = apiDtoV1.getServiceParams()
                        .stream()
                        .map(sp -> BeanUtils.convertType(sp, ServiceParam.class))
                        .peek(sp -> sp.setId(UUIDUtil.getUUID()))
                        .peek(sp -> sp.setApiId(api.getId()))
                        .peek(sp -> sp.setRequestParamId(
                                requestParamNameIdMap.get(serviceRequestParamNameMap.get(sp.getName()))
                        )).collect(Collectors.toList());
                Mono.create(sink -> sink.success(this.serviceParamService.addServiceParamBatch(serviceParamList)));
            }
            if (ParamModeEnum.getByCode(apiDtoV1.getRequestConfig().getMode()) == ParamModeEnum.透传) {
                BizAssert.validParam(apiDtoV1.getServiceParams() == null || apiDtoV1.getServiceParams().size() == 0,
                        BizCodes.INVALID_PARAM.getCode(), "请求配置为透传模式,服务参数也必须为空");
            }
        } else {
            BizAssert.validParam(apiDtoV1.getServiceParams() == null || apiDtoV1.getServiceParams().size() == 0,
                    BizCodes.INVALID_PARAM.getCode(), "请求参数列表为空,服务参数也必须为空");
        }
        return api.getId();
    }

    /**
     * 删除API信息
     *
     * @param loginUserId 登录用户id
     * @param apiId       APIId
     * @return 是否删除成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean delApiById(String loginUserId, String apiId) {
        int count = 0;
        try {
            count = this.apiDao.delById(apiId);
            this.requestParamService.delReuqstParamByApiId(apiId);
            this.requestConfigService.delRequestConfigByApiId(apiId);
            this.serviceConfigService.delServiceConfigByApiId(apiId);
            this.serviceErrorCodeService.delServiceErrorCodeByApiId(apiId);
            this.serviceConstParamService.delServiceConstParamByApiId(apiId);
            this.serviceParamService.delServiceParamByApiId(apiId);
        } catch (Exception e) {
            BizAssert.pass(count == 1, BizCodes.ERROR_DELETE);
        }
        return true;
    }

    /**
     * 修改API
     *
     * @param loginUserId 登录用户ID
     * @param apiId       APIId
     * @param apiDtoV1    API传输层对象
     * @return 是否修改成功
     */
    public boolean updateApi(String loginUserId, String apiId, ApiDtoV1 apiDtoV1) {
        this.doValidation(apiDtoV1.getAuthType(), apiDtoV1.getSignMethod(), apiDtoV1.getVisibility(), apiDtoV1.getResultType());
        Api existApi = this.apiDao.getById(apiId);
        BizAssert.found(existApi != null, BizCodes.INVALID_PARAM.getCode(), "API不存在");
        //修改API信息
        org.springframework.beans.BeanUtils.copyProperties(apiDtoV1, existApi);
        int count = 0;
        try {
            count = this.apiDao.update(existApi);
        } catch (Exception e) {
            BizAssert.pass(count == 1, BizCodes.ERROR_UPDATE);
        }
        //删除API相关信息
        this.requestParamService.delReuqstParamByApiId(apiId);
        this.requestConfigService.delRequestConfigByApiId(apiId);
        this.serviceConfigService.delServiceConfigByApiId(apiId);
        this.serviceErrorCodeService.delServiceErrorCodeByApiId(apiId);
        this.serviceConstParamService.delServiceConstParamByApiId(apiId);
        this.serviceParamService.delServiceParamByApiId(apiId);

        //添加RequestConfig信息
        RequestConfig requestConfig = BeanUtils.convertType(apiDtoV1.getRequestConfig(), RequestConfig.class);
        this.requestConfigService.addRequestConfig(apiId, requestConfig.getHttpMethod(),
                requestConfig.getMode(), requestConfig.getPath(), requestConfig.getProtocol(),
                requestConfig.getWsType(), requestConfig.getBodyFormat(), requestConfig.getBodyDescription());

        //添加ServiceConfig信息
        ServiceConfig serviceConfig = BeanUtils.convertType(apiDtoV1.getServiceConfig(), ServiceConfig.class);
        this.serviceConfigService.addServiceConfig(apiId, serviceConfig.getType(),
                serviceConfig.getContentTypeCategory(), serviceConfig.getContentTypeValue(), serviceConfig.getAddress(),
                serviceConfig.getHttpMethod(), serviceConfig.getPath(), serviceConfig.getTimeout(), serviceConfig.getMock(),
                serviceConfig.getMockResult());

        //添加ServiceConstParam信息
        if (apiDtoV1.getServiceConstParams() != null && apiDtoV1.getServiceConstParams().size() > 0) {
            List<ServiceConstParam> serviceConstParamList = apiDtoV1.getServiceConstParams()
                    .stream()
                    .map(scp -> BeanUtils.convertType(scp, ServiceConstParam.class))
                    .peek(scp -> scp.setId(UUIDUtil.getUUID()))
                    .peek(scp -> scp.setApiId(apiId))
                    .collect(Collectors.toList());
            this.serviceConstParamService.addServiceConstParamBatch(serviceConstParamList);
        }

        //添加ServiceErrorCode信息
        if (apiDtoV1.getServiceErrorCodes() != null && apiDtoV1.getServiceErrorCodes().size() > 0) {
            List<ServiceErrorCode> serviceErrorCodeList = apiDtoV1.getServiceErrorCodes()
                    .stream()
                    .map(sec -> BeanUtils.convertType(sec, ServiceErrorCode.class))
                    .peek(sec -> sec.setId(UUIDUtil.getUUID()))
                    .peek(sec -> sec.setApiId(apiId))
                    .collect(Collectors.toList());
            this.serviceErrorCodeService.addServiceErrorCodeBatch(serviceErrorCodeList);
        }

        List<RequestParam> requestParamList = null;
        //添加RequestParam信息
        if (apiDtoV1.getRequestParams() != null && apiDtoV1.getRequestParams().size() > 0) {
            requestParamList = apiDtoV1.getRequestParams()
                    .stream()
                    .map(rp -> BeanUtils.convertType(rp, RequestParam.class))
                    .peek(rp -> rp.setId(UUIDUtil.getUUID()))
                    .peek(rp -> rp.setApiId(apiId))
                    .collect(Collectors.toList());
            this.requestParamService.addRequestParamBatch(requestParamList);
        }
        //添加ServiceParam信息
        if (requestParamList != null) {
            if (ParamModeEnum.getByCode(apiDtoV1.getRequestConfig().getMode()) == ParamModeEnum.映射) {
                //准备RequestParamName->RequestParamId的Map
                Map<String, String> requestParamNameIdMap = requestParamList.stream()
                        .collect(Collectors.toMap(RequestParam::getName, RequestParam::getId));
                BizAssert.validParam(apiDtoV1.getServiceParams().size() == requestParamNameIdMap.size(),
                        BizCodes.INVALID_PARAM.getCode(), "请求参数与后端服务参数数量不匹配");
                long serviceParamInMapCount = apiDtoV1.getRequestParams()
                        .stream()
                        .map(RequestParamDtoV1::getName)
                        .filter(StringUtils::hasText)
                        .distinct()
                        .filter(requestParamNameIdMap::containsKey)
                        .count();
                BizAssert.validParam(serviceParamInMapCount == requestParamNameIdMap.size(),
                        BizCodes.INVALID_PARAM.getCode(), "后端服务参数的请求参数名未与请求参数列表中参数名对应");
                //准备ServiceParamName->RequestParamName的Map,ServiceParamName->RequestParamName->RequestParamId
                Map<String, String> serviceRequestParamNameMap = apiDtoV1.getServiceParams()
                        .stream()
                        .collect(Collectors.toMap(ServiceParamDtoV1::getName, ServiceParamDtoV1::getRequestParamName));
                //构造ServiceParamList,RequestParamId通过两个Map,ServiceParamName->RequestParamName->RequestParamId获得
                List<ServiceParam> serviceParamList = apiDtoV1.getServiceParams()
                        .stream()
                        .map(sp -> BeanUtils.convertType(sp, ServiceParam.class))
                        .peek(sp -> sp.setId(UUIDUtil.getUUID()))
                        .peek(sp -> sp.setApiId(apiId))
                        .peek(sp -> sp.setRequestParamId(
                                requestParamNameIdMap.get(serviceRequestParamNameMap.get(sp.getName()))
                        )).collect(Collectors.toList());
                Mono.create(sink -> sink.success(this.serviceParamService.addServiceParamBatch(serviceParamList)));
            }
            if (ParamModeEnum.getByCode(apiDtoV1.getRequestConfig().getMode()) == ParamModeEnum.透传) {
                BizAssert.validParam(apiDtoV1.getServiceParams() == null || apiDtoV1.getServiceParams().size() == 0,
                        BizCodes.INVALID_PARAM.getCode(), "请求配置为透传模式,服务参数也必须为空");
            }
        } else {
            BizAssert.validParam(apiDtoV1.getServiceParams() == null || apiDtoV1.getServiceParams().size() == 0,
                    BizCodes.INVALID_PARAM.getCode(), "请求参数列表为空,服务参数也必须为空");
        }
        return true;
    }


    /**
     * 根据id查询API详情
     *
     * @param apiId APIId
     * @return {@link ApiV1}
     */
    public ApiV1 getApiV1ById(String apiId) {
        Api api = this.apiDao.getById(apiId);
        ApiV1 apiV1 = BeanUtils.convertType(api, ApiV1.class);

        apiV1.setRequestConfig(this.requestConfigService.getRequestConfigV1ByApiId(apiId));
        apiV1.setRequestParams(this.requestParamService.getRequestParamV1ListByApiId(apiId));
        apiV1.setServiceConfig(this.serviceConfigService.getServiceConfigV1ByApiId(apiId));
        apiV1.setServiceConstParams(this.serviceConstParamService.getServiceConstParamV1ListByApiId(apiId));
        apiV1.setServiceErrorCodes(this.serviceErrorCodeService.getServiceErrorCodeV1ListByApiId(apiId));
        apiV1.setServiceParams(this.serviceParamService.getServiceParamV1ListByApiId(apiId));

        return apiV1;
    }

    /**
     * 根据id查询API持久层对象
     *
     * @param apiId APIId
     * @return {@link Api}
     */
    public Api getApiById(String apiId) {
        return this.apiDao.getById(apiId);
    }

    /**
     * 分页查询API信息
     *
     * @param loginUserId 登录用户id
     * @param regionId    地域id
     * @param groupId     分组id
     * @param keyword     关键词
     * @param pageNo      页号
     * @param pageSize    页大小
     * @return {@link Pagination<ApiV1>}
     */
    public Pagination<ApiV1> getApiV1Pagination(String loginUserId, String regionId, String groupId, String keyword, Integer pageNo, Integer pageSize) {
        int count = this.apiDao.getPaginationCount(regionId, groupId, keyword);
        Pagination<ApiV1> pagination = new Pagination<>();
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        pagination.setTotalCount(count);
        if (count == 0) {
            pagination.setPageList(Collections.emptyList());
            return pagination;
        }
        List<ApiV1> apiV1List = this.apiDao.getPaginationList(regionId, groupId, keyword, pagination.getRowOffset(), pageSize);
        pagination.setPageList(apiV1List);
        return pagination;
    }
}
