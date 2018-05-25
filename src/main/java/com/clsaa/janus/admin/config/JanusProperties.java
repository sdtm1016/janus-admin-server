package com.clsaa.janus.admin.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author 任贵杰
 * @version v1
 * @summary Janus配置类
 * @since 2018/5/25
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "janus")
@Validated
public class JanusProperties {
    @NotNull
    @Valid
    private Auth auth;

    /**
     * 认证信息相关配置
     */
    @Getter
    @Setter
    public static class Auth {
        @NotNull
        @Valid
        private AccessKey accessKey;

        /**
         * AccessKey相关信息
         */
        @Getter
        @Setter
        public static class AccessKey {
            /**
             * AccessSecret数据库存储加密密匙
             */
            @NotEmpty
            @Valid
            private String aesKey;
        }
    }
}
