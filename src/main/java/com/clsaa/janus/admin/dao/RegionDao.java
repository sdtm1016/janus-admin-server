package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.Region;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface RegionDao {

    /**
     * 获取全部未删除的地域
     *
     * @return {@link List<Region>}
     */
    List<Region> getAllRegion();
}