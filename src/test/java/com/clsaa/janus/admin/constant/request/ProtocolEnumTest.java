package com.clsaa.janus.admin.constant.request;

import com.clsaa.janus.admin.constant.common.ProtocolEnum;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/19
 */
public class ProtocolEnumTest {

    @Test
    public void getSupportedProtocols() {
        String str1 = "ws,http";
        String str1Result = ProtocolEnum.getSupportedProtocols(str1);
        assertEquals("HTTP,WS",str1Result);
        String str2 = "";
        String str2Result = ProtocolEnum.getSupportedProtocols(str2);
        assertEquals("",str2Result);
    }

    @Test
    public void enabledWS() {
        String str1 = "http";
        boolean str1Result = ProtocolEnum.enabledWS(str1);
        assertFalse(str1Result);
        String str2 = "http,ws";
        boolean str2Result = ProtocolEnum.enabledWS(str2);
        assertTrue(str2Result);
    }
}