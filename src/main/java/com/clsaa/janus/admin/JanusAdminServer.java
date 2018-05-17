package com.clsaa.janus.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author 任贵杰
 * @version v1
 * @summary Janus网关控制台服务端
 * @since 2018/5/17
 */
@SpringBootApplication
@MapperScan("com.clsaa.janus.admin.dao")
@EnableConfigurationProperties
public class JanusAdminServer {

    private static final Logger logger = LoggerFactory.getLogger(JanusAdminServer.class);

    public static void main(String[] args) {
        SpringApplication.run(JanusAdminServer.class, args);
    }
}
