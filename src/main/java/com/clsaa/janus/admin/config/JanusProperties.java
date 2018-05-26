package com.clsaa.janus.admin.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

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
    private App app;
    private Service service;

    @Getter
    @Setter
    public static class App {
        private Auth auth;

        @Getter
        @Setter
        public static class Auth {
            private AesKey aesKey;

            @Getter
            @Setter
            public static class AesKey {
                private String accessKey;
                private String accessSecret;
            }
        }
    }

    @Getter
    @Setter
    public static class Service {
        private Auth auth;
        @Getter
        @Setter
        public static class Auth {
            private AesKey aesKey;

            @Getter
            @Setter
            public static class AesKey {
                private String accessKey;
                private String accessSecret;
            }
        }
    }
}
