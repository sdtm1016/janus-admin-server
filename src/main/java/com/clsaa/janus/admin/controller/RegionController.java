package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.entity.vo.v1.RegionV1;
import com.clsaa.janus.admin.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * <p>
 * 地域接口实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class RegionController {
    @Autowired
    private RegionService regionService;

    /**
     * 获取全部地域信息
     *
     * @return {@link Flux<RegionV1>}
     * @summary 获取全部地域信息
     * @author 任贵杰 812022339@qq.com
     * @since 2018/5/18
     */
    @GetMapping(value = "/v1/region/all")
    public Flux<RegionV1> getAllRegion() {
        return Flux.create(fluxSink -> {
            this.regionService.getAllRegion().forEach(fluxSink::next);
            fluxSink.complete();
        });
    }
}
