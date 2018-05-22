package com.clsaa.janus.admin.util.validator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/22
 */
public class AddressValidatorTest {

    @Test
    public void isAddress() {
        String addr1 = "http://www.baidu.com";
        String addr2 = "www.baidu.com";
        String addr3 = "http://www.xxx.baidu.com";
        String addr4 = "#www#.baidu.com";
        String addr5 = "https://#www#.baidu.com";
        assertTrue(AddressValidator.isAddress(addr1));
        assertFalse(AddressValidator.isAddress(addr2));
        assertTrue(AddressValidator.isAddress(addr3));
        assertFalse(AddressValidator.isAddress(addr4));
        assertTrue(AddressValidator.isAddress(addr5));
    }
}