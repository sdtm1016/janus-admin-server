package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.dao.RegionDao;
import com.clsaa.janus.admin.entity.po.Region;
import com.clsaa.janus.admin.entity.vo.v1.RegionV1;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 地域信息 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class RegionService {
    @Autowired
    private RegionDao regionDao;

    /**
     * 将持久层对象转为视图层对象
     */
    private static RegionV1 valueOf(Region region) {
        if (region == null) {
            return null;
        } else {
            RegionV1 regionV1 = new RegionV1();
            BeanUtils.copyProperties(region, regionV1);
            return regionV1;
        }
    }

    /**
     * 获取全部地域信息
     *
     * @return {@link List<RegionV1>}
     */
    public List<RegionV1> getAllRegion() {
        return this.regionDao.getAllRegion()
                .stream().map(RegionService::valueOf).collect(Collectors.toList());
    }

    /**
     * 根据id获取region信息
     *
     * @param regionId 地域id
     * @return {@link RegionV1}
     */
    public Region getRegionById(String regionId) {
        return this.regionDao.getRegionByID(regionId);
    }
}
