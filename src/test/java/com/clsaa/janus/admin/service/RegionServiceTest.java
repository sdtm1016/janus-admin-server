package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.BaseTest;
import com.clsaa.janus.admin.entity.po.Region;
import com.clsaa.janus.admin.entity.vo.RegionV1;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author 任贵杰
 * @version v1
 * @since 2018/5/18
 */
public class RegionServiceTest extends BaseTest {

    @Autowired
    private RegionService regionService;

    @Test
    public void getAllRegion() {
        List<RegionV1> regionV1List = this.regionService.getAllRegion();
        assertEquals(2, regionV1List.size());
    }

    @Test
    public void getRegionById() {
        Region region = this.regionService.getRegionById("1");
        assertNotNull(region);
        assertEquals("1", region.getId());
    }
}