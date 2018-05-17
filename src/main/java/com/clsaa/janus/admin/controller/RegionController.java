package com.clsaa.janus.admin.controller;

import com.clsaa.janus.admin.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Ren Gui Jie 812022339@qq.com
 * @since 2018-05-17
 */
@RestController
@CrossOrigin
public class RegionController {
    @Autowired
    private RegionService regionService;
}
