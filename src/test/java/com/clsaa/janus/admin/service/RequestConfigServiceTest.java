package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.BaseTest;
import com.clsaa.janus.admin.entity.vo.RequestConfigV1;
import com.clsaa.janus.admin.util.UUIDUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;


/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/22
 */
public class RequestConfigServiceTest extends BaseTest {

    @Autowired
    private RequestConfigService requestConfigService;
    /**
     * 主键id
     */
    private String id = UUIDUtil.getUUID();
    /**
     * ApiId,t_api.id
     */
    private String apiId = UUIDUtil.getUUID();
    /**
     * Http方法,1为GET,2为POST,3为PUT,4为DELETE,5为PATCH,6为HEAD
     */
    private Integer httpMethod = 1;
    /**
     * 入参请求模式,1为映射,2为透传
     */
    private Integer mode = 1;
    /**
     * 请求路径
     */
    private String path = "/user";
    /**
     * 支持协议,大写且用逗号分隔
     */
    private String protocol = "HTTP";
    /**
     * 双向通信API类别,1为普通,2为注册,3为注销,4为下行通知
     */
    private Integer wsType = 1;
    /**
     * Body格式,1为FORM,2为STREAM
     */
    private Integer bodyFormat = 1;
    /**
     * Body描述
     */
    private String bodyDescription = "bodyDescription";

    @Test
    public void addRequestConfig() {
        this.requestConfigService.addRequestConfig(apiId, httpMethod, mode, path, protocol, wsType, bodyFormat, bodyDescription);
        RequestConfigV1 requestConfigV1 = this.requestConfigService.getRequestConfigV1ByApiId(apiId);
        assertNotNull(requestConfigV1);
        assertEquals(httpMethod, requestConfigV1.getHttpMethod());
        assertEquals(mode, requestConfigV1.getMode());
        assertEquals(path, requestConfigV1.getPath());
        assertEquals(protocol, requestConfigV1.getProtocol());
        assertEquals(wsType, requestConfigV1.getWsType());
        assertEquals(bodyFormat, requestConfigV1.getBodyFormat());
        assertEquals(bodyDescription, requestConfigV1.getBodyDescription());
    }

    @Test
    public void delRequestConfigByApiId() {
        this.requestConfigService.addRequestConfig(apiId, httpMethod, mode, path, protocol, wsType, bodyFormat, bodyDescription);
        RequestConfigV1 requestConfigV1 = this.requestConfigService.getRequestConfigV1ByApiId(apiId);
        assertNotNull(requestConfigV1);
        this.requestConfigService.delRequestConfigByApiId(apiId);
        requestConfigV1 = this.requestConfigService.getRequestConfigV1ByApiId(apiId);
        assertNull(requestConfigV1);
    }

    @Test
    public void updateRequestConfig() {
        this.requestConfigService.addRequestConfig(apiId, httpMethod, mode, path, protocol, wsType, bodyFormat, bodyDescription);
        RequestConfigV1 requestConfigV1 = this.requestConfigService.getRequestConfigV1ByApiId(apiId);
        assertNotNull(requestConfigV1);
        Integer newHttpMethod = httpMethod + 1;
        Integer newMode = mode + 1;
        String newPath = path + path;
        String newProtocol = "HTTP,WS";
        Integer newWSType = 2;
        Integer newBodyFormat = 2;
        String newBodyDescription = "123";
        this.requestConfigService.updateRequestConfigByApiId(apiId, newHttpMethod, newMode, newPath, newProtocol, newWSType, newBodyFormat, newBodyDescription);
        requestConfigV1 = this.requestConfigService.getRequestConfigV1ByApiId(apiId);
        assertEquals(newHttpMethod, requestConfigV1.getHttpMethod());
        assertEquals(newMode, requestConfigV1.getMode());
        assertEquals(newPath, requestConfigV1.getPath());
        assertEquals(newProtocol, requestConfigV1.getProtocol());
        assertEquals(newWSType, requestConfigV1.getWsType());
        assertEquals(newBodyFormat, requestConfigV1.getBodyFormat());
        assertEquals(newBodyDescription, requestConfigV1.getBodyDescription());
    }

    @Test
    public void getRequestConfigV1ByApiId() {
        this.requestConfigService.addRequestConfig(apiId, httpMethod, mode, path, protocol, wsType, bodyFormat, bodyDescription);
        RequestConfigV1 requestConfigV1 = this.requestConfigService.getRequestConfigV1ByApiId(apiId);
        assertNotNull(requestConfigV1);
    }
}