package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.IpStrategy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * IP控制策略信息,主要包含IP控制策略基本信息及控制类型 Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface IpStrategyDao {

    /**
     * 添加IP访问策略
     *
     * @param ipStrategy IP访问策略持久层对象
     * @return 影响记录数
     */
    int add(IpStrategy ipStrategy);

    /**
     * 根据id删除IP访问策略
     *
     * @param id IP访问策略id
     * @return 影像记录数
     */
    int delById(String id);

    /**
     * 更新IP访问策略
     *
     * @param ipStrategy IP访问策略持久层对象
     * @return 影响记录数
     */
    int update(IpStrategy ipStrategy);

    /**
     * 根据id查询IP访问策略
     *
     * @param id IP访问策略id
     * @return {@link IpStrategy}
     */
    IpStrategy getById(String id);


    /**
     * 获取分页数据总量
     *
     * @param regionId 地域id
     * @param keyword  关键词
     * @return 分页数据总量
     */
    int getPaginationCount(@Param("regionId") String regionId,
                           @Param("keyword") String keyword);

    /**
     * 获取分页数据
     *
     * @param regionId  地域id
     * @param keyword   关键词
     * @param rowOffset 页偏移
     * @param pageSize  页大小
     * @return {@link List<IpStrategy>}
     */
    List<IpStrategy> getPaginationList(@Param("regionId") String regionId,
                                       @Param("keyword") String keyword,
                                       @Param("rowOffset") int rowOffset,
                                       @Param("pageSize") int pageSize);
}