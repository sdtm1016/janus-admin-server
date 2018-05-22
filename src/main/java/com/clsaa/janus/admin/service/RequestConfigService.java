package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.constant.common.HttpMethodEnum;
import com.clsaa.janus.admin.constant.common.ProtocolEnum;
import com.clsaa.janus.admin.constant.request.*;
import com.clsaa.janus.admin.dao.RequestConfigDao;
import com.clsaa.janus.admin.entity.po.RequestConfig;
import com.clsaa.janus.admin.entity.vo.RequestConfigV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.util.BeanUtils;
import com.clsaa.janus.admin.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 客户端到网关的请求配置信息 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class RequestConfigService {
    @Autowired
    private RequestConfigDao requestConfigDao;

    /**
     * 参数校验函数
     */
    private void doValidation(Integer httpMethod, Integer mode, String path, String protocol, Integer wsType, Integer bodyFormat, String bodyDescription) {
        BizAssert.validParam(StringUtils.hasText(bodyDescription) && bodyDescription.length() <= 180, BizCodes.INVALID_PARAM.getCode(), "bodyDescription非法");
        BizAssert.validParam(HttpMethodEnum.getByCode(httpMethod) != null, BizCodes.INVALID_PARAM.getCode(), "HTTPMethod非法");
        BizAssert.validParam(ParamModeEnum.getByCode(mode) != null, BizCodes.INVALID_PARAM.getCode(), "入参请求模式非法");
        BizAssert.validParam(StringUtils.hasText(ProtocolEnum.getSupportedProtocols(protocol)), BizCodes.INVALID_PARAM.getCode(), "请求协议非法");
        if (ProtocolEnum.enabledWS(protocol)) {
            BizAssert.validParam(WSTypeEnum.getByCode(wsType) != null, BizCodes.INVALID_PARAM.getCode(), "双向通信类型非法");
        }
        BizAssert.validParam(BodyFormatEnum.getByCode(bodyFormat) != null, BizCodes.INVALID_PARAM.getCode(), "入参请求体格式类型非法");
    }

    /**
     * 创建前端到网关请求配置
     *
     * @param apiId           APIid
     * @param httpMethod      http方法,1为GET,2为POST,3为PUT,4为DELETE,5为PATCH,6为HEAD
     * @param mode            入参请求模式,1为映射,2为透传
     * @param path            请求路径,以大括号标识变量
     * @param protocol        协议,大写以逗号分隔
     * @param wsType          双向通信API类别,1为普通,2为注册,3为注销,4为下行通知
     * @param bodyFormat      请求Body格式,1为FORM,2为STREAM
     * @param bodyDescription 请求Body描述
     * @return 创建的请求配置的id
     */
    public String addRequestConfig(String apiId, Integer httpMethod, Integer mode, String path, String protocol, Integer wsType, Integer bodyFormat, String bodyDescription) {
        //参数校验
        this.doValidation(httpMethod, mode, path, protocol, wsType, bodyFormat, bodyDescription);
        //创建对象
        RequestConfig requestConfig = new RequestConfig(UUIDUtil.getUUID(), apiId, httpMethod, mode, path,
                ProtocolEnum.getSupportedProtocols(protocol), wsType, bodyFormat, bodyDescription);
        //存入数据库
        int count = 0;
        try {
            count = this.requestConfigDao.add(requestConfig);
        } catch (Exception e) {
            BizAssert.pass(count == 1, BizCodes.ERROR_INSERT);
        }
        BizAssert.pass(count == 1, BizCodes.ERROR_INSERT);
        return requestConfig.getId();
    }

    /**
     * 根据APIid删除前端到网关请求配置
     *
     * @param apiId APIid
     * @return 删除是否成功
     */
    public boolean delRequestConfigByApiId(String apiId) {
        int count = 0;
        try {
            count = this.requestConfigDao.delByApiId(apiId);
        } catch (Exception e) {
            BizAssert.pass(count == 1, BizCodes.ERROR_DELETE);
        }
        BizAssert.pass(count == 1, BizCodes.ERROR_DELETE);
        return true;
    }

    /**
     * 根据APIid更新前端到网关请求配置,APIid不会被更新
     *
     * @param apiId           APIid
     * @param httpMethod      http方法,1为GET,2为POST,3为PUT,4为DELETE,5为PATCH,6为HEAD
     * @param mode            入参请求模式,1为映射,2为透传
     * @param path            请求路径,以大括号标识变量
     * @param protocol        协议,大写以逗号分隔
     * @param wsType          双向通信API类别,1为普通,2为注册,3为注销,4为下行通知
     * @param bodyFormat      请求Body格式,1为FORM,2为STREAM
     * @param bodyDescription 请求Body描述
     * @return 更新是否成功
     */
    public boolean updateRequestConfigByApiId(String apiId, Integer httpMethod, Integer mode, String path, String protocol, Integer wsType, Integer bodyFormat, String bodyDescription) {
        //参数校验
        this.doValidation(httpMethod, mode, path, protocol, wsType, bodyFormat, bodyDescription);
        RequestConfig existRequestConfig = this.requestConfigDao.getByApiId(apiId);
        BizAssert.found(existRequestConfig != null, BizCodes.INVALID_PARAM.getCode(), "无法找到API对应请求配置");
        //创建对象
        RequestConfig requestConfig = new RequestConfig(existRequestConfig.getId(), apiId, httpMethod, mode, path,
                ProtocolEnum.getSupportedProtocols(protocol), wsType, bodyFormat, bodyDescription);
        //更新数据库
        int count = 0;
        try {
            count = this.requestConfigDao.update(requestConfig);
        } catch (Exception e) {
            BizAssert.pass(count == 1, BizCodes.ERROR_UPDATE);
        }
        BizAssert.pass(count == 1, BizCodes.ERROR_UPDATE);
        return true;
    }

    /**
     * 根据ApiId查询请求配置
     *
     * @param apiId APIId
     * @return {@link RequestConfigV1}
     */
    public RequestConfigV1 getRequestConfigV1ByApiId(String apiId) {
        RequestConfig requestConfig = this.requestConfigDao.getByApiId(apiId);
        return BeanUtils.convertType(requestConfig, RequestConfigV1.class);
    }
}
