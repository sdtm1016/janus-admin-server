package com.clsaa.janus.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.clsaa.janus.admin.constant.common.XHeaders;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseControllerTest {
    protected WebTestClient rest;
    @LocalServerPort
    private int port = 8080;

    @Before
    public void setUp() {
        this.rest = WebTestClient.bindToServer().responseTimeout(Duration.ofSeconds(30))
                .baseUrl("http://localhost:" + this.port)
                .defaultHeader(XHeaders.X_LOGIN_USER_ID, "3ee73f38497a498e93a7fee6badb298b")
                .build();
    }
}
