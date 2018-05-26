package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.entity.dto.v1.ServiceAuthDtoV1;
import com.clsaa.janus.admin.util.UUIDUtil;
import org.junit.Test;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/25
 */
public class ServiceServiceAuthControllerTest extends BaseControllerTest {

    private static final String BASE_URL_V1 = "/v1/auth/";
    private static final String AUTH_ID = "1";

    @Test
    public void addAuthV1() {
        ServiceAuthDtoV1 serviceAuthDtoV1 = new ServiceAuthDtoV1();
        serviceAuthDtoV1.setRegionId(UUIDUtil.getUUID());
        serviceAuthDtoV1.setAccessKey("renguijie");
        serviceAuthDtoV1.setAccessSecret("xiaojie1996");
        serviceAuthDtoV1.setName("name");
        serviceAuthDtoV1.setDescription("description");
        this.rest.post()
                .uri(BASE_URL_V1)
                .syncBody(serviceAuthDtoV1)
                .exchange()
                .expectStatus()
                .isOk();
        serviceAuthDtoV1.setRegionId("123");
        this.rest.post()
                .uri(BASE_URL_V1)
                .syncBody(serviceAuthDtoV1)
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }

    @Test
    public void delAuthByIdV1() {
    }

    @Test
    public void updateAuthByIdV1() {
    }

    @Test
    public void getAuthByIdV1() {
        this.rest.get()
                .uri(BASE_URL_V1+AUTH_ID)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void getPaginationV1() {
    }
}