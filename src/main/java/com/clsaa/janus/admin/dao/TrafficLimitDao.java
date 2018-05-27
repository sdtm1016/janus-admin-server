package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.TrafficLimit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 限流信息Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface TrafficLimitDao {

    /**
     * 添加限流策略
     *
     * @param trafficLimit 限流策略持久层对象
     * @return 影像记录数
     */
    int add(TrafficLimit trafficLimit);

    /**
     * 删除限流策略
     *
     * @param id 限流策略id
     * @return 影像记录数
     */
    int delById(@Param("id") String id);

    /**
     * 根据id查询限流策略
     *
     * @param id 限流策略id
     * @return {@link TrafficLimit}
     */
    TrafficLimit getTrafficLimitById(@Param("id") String id);

    /**
     * 修改限流策略
     *
     * @param trafficLimit 限流策略持久层对象
     * @return 影像记录数
     */
    int update(TrafficLimit trafficLimit);

    /**
     * 获取分页数据总量
     *
     * @param keyword 关键词
     * @return 分页数据总量
     */
    int getPaginationCount(@Param("keyword") String keyword);

    /**
     * 获取分页数据
     *
     * @param keyword   关键词
     * @param rowOffset 页偏移量
     * @param pageSize  分页大小
     * @return {@link List<TrafficLimit>}
     */
    List<TrafficLimit> getPaginationList(@Param("keyword") String keyword,
                                         @Param("rowOffset") int rowOffset,
                                         @Param("pageSize") int pageSize);
}