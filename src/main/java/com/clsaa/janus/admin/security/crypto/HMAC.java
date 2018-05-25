package com.clsaa.janus.admin.security.crypto;

import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;

import java.nio.charset.StandardCharsets;

/**
 * HMAC算法类,内部会使用HmacSHA256进行摘要
 *
 * @author 任贵杰 812022339@qq.com
 * @summary Hmac算法类
 * @since 2018-05-25
 */
public final class HMAC {

    private HMAC() {
        throw new UnsupportedOperationException();
    }

    /**
     * HMAC-SHA256摘要，返回BASE64URL编码的加密结果
     *
     * @param secret 密匙
     * @param data   数据
     * @return {@link CryptoResult}
     */
    public static CryptoResult SHA256(String secret, String data) {
        try {
            String base64URLResult = BaseEncoding.base64Url()
                    .encode(Hashing.hmacSha256(secret.getBytes(StandardCharsets.UTF_8)).hashString(data, StandardCharsets.UTF_8).asBytes());
            return new CryptoResult(CryptoResult.Status.OK, base64URLResult);
        } catch (Exception ignored) {
            return new CryptoResult(CryptoResult.Status.Exception);
        }
    }
}
