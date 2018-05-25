package com.clsaa.janus.admin.security.crypto;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/25
 */
public class FastAesTest {

    private String key = "renguijie";
    private String content = "mima";
    @Test
    public void encrypt() {
        CryptoResult cryptoResult1 = FastAes.encrypt(key.getBytes(),content);
        System.out.println(cryptoResult1.getContent());
        CryptoResult cryptoResult2 = FastAes.encrypt(key.getBytes(),content);
        System.out.println(cryptoResult2.getContent());
    }

    @Test
    public void decrypt() {
    }
}