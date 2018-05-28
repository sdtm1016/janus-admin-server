package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.IpStrategyItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * IP控制策略项,包含具体要控制的IP信息 Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface IpStrategyItemDao {

    /**
     * 添加IP控制策略项
     *
     * @param ipStrategyItem IP控制策略项持久处对象
     * @return 影响记录数
     */
    int add(IpStrategyItem ipStrategyItem);

    /**
     * 根据id删除IP控制策略项
     *
     * @param id IP控制策略项id
     * @return 影响记录数
     */
    int delById(@Param("id") String id);


    /**
     * 根据id查询IP控制策略项
     *
     * @param id IP控制策略项id
     * @return {@link IpStrategyItem}
     */
    IpStrategyItem getById(@Param("id") String id);

    /**
     * 更新IP控制策略项
     *
     * @param ipStrategyItem IP控制策略项持久处对象
     * @return 影响记录数
     */
    int update(IpStrategyItem ipStrategyItem);

    /**
     * 获取分页数据总量
     *
     * @param ipStrategyId IP控制策略id
     * @return 分页数据总量
     */
    int getPaginationCount(@Param("ipStrategyId") String ipStrategyId);

    /**
     * 获取分页数据
     *
     * @param ipStrategyId IP控制策略id
     * @param rowOffset    页大小
     * @param pageSize     页偏移
     * @return {@link List<IpStrategyItem>}
     */
    List<IpStrategyItem> getPaginationList(@Param("ipStrategyId") String ipStrategyId,
                                           @Param("rowOffset") Integer rowOffset,
                                           @Param("pageSize") Integer pageSize);
}