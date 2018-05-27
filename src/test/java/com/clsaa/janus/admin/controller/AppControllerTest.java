package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.entity.dto.v1.AppDtoV1;
import org.junit.Test;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/27
 */
public class AppControllerTest extends BaseControllerTest {
    private static final String BASE_URL_V1 = "/v1/app/";

    @Test
    public void addAppV1() {
        AppDtoV1 appDtoV1 = new AppDtoV1();
        appDtoV1.setName("name1");
        appDtoV1.setDescription("description");
        this.rest.post()
                .uri(BASE_URL_V1)
                .syncBody(appDtoV1)
                .exchange()
                .expectStatus()
                .isOk();
        AppDtoV1 appDtoV11 = new AppDtoV1();
        appDtoV11.setName("123");
        this.rest.post()
                .uri(BASE_URL_V1)
                .syncBody(appDtoV11)
                .exchange()
                .expectStatus()
                .is4xxClientError();
        AppDtoV1 appDtoV12 = new AppDtoV1();
        appDtoV12.setName("name2");
        this.rest.post()
                .uri(BASE_URL_V1)
                .syncBody(appDtoV12)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void delAuthByIdV1() {
        this.rest.delete()
                .uri(BASE_URL_V1 + "235dce01cba34e7dbbf45396cbb1f9f3")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void updateAppByIdV1() {
        AppDtoV1 appDtoV1 = new AppDtoV1();
        appDtoV1.setName("updateName");
        appDtoV1.setDescription("");
        this.rest.put()
                .uri(BASE_URL_V1 + "c85bdb162fb74c31b797175f2a89ac48")
                .syncBody(appDtoV1)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void updateAppAccessSecretByIdV1() {
        this.rest.put()
                .uri(BASE_URL_V1 + "c85bdb162fb74c31b797175f2a89ac48" + "/AccessSecret")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void getAppByIdV1() {
        this.rest.get()
                .uri(BASE_URL_V1 + "c85bdb162fb74c31b797175f2a89ac48")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void getPaginationV1() {
        this.rest.get()
                .uri(BASE_URL_V1 + "/pagination")
                .exchange()
                .expectStatus()
                .isOk();
        this.rest.get()
                .uri(BASE_URL_V1 + "/pagination"+"?keyword=name2")
                .exchange()
                .expectStatus()
                .isOk();
    }
}