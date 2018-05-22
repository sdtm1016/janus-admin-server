package com.clsaa.janus.admin.util;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/22
 */
public class BeanUtilsTest {

    @Test
    public void convertType() {
        Girl girl = new Girl();
        girl.setName("Andy");
        Boy boy = BeanUtils.convertType(girl,Boy.class);
        assertEquals(girl.getName(),boy.getName());
    }
}