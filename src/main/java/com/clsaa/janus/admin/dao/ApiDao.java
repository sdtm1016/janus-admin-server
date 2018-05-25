package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.Api;
import com.clsaa.janus.admin.entity.vo.ApiV1;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * API信息 Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface ApiDao {

    /**
     * 添加API信息
     *
     * @param api API持久层独享
     * @return 影响记录数
     */
    int add(Api api);

    /**
     * 删除API信息
     *
     * @param id APIId
     * @return 影响记录数
     */
    int delById(@Param("id") String id);

    /**
     * 更新API信息
     *
     * @param api API持久层对象
     * @return 影响记录数
     */
    int update(Api api);

    /**
     * 根据APIId查询API信息
     *
     * @param id APIId
     * @return {@link Api}
     */
    Api getById(@Param("id") String id);


    /**
     * 获取API分页数据总量
     *
     * @param regionId 地域id
     * @param groupId  分组id
     * @param keyword  关键词
     * @return API分页数据总量
     */
    int getPaginationCount(@Param("regionId") String regionId,
                           @Param("groupId") String groupId,
                           @Param("keyword") String keyword);

    /**
     * 获取分页数据
     *
     * @param regionId  地域id
     * @param groupId   分组id
     * @param keyword   关键词
     * @param rowOffset 偏移量
     * @param pageSize  页大小
     * @return {@link List<ApiV1>}
     */
    List<ApiV1> getPaginationList(@Param("regionId") String regionId,
                                  @Param("groupId") String groupId,
                                  @Param("keyword") String keyword,
                                  @Param("rowOffset") Integer rowOffset,
                                  @Param("pageSize") Integer pageSize);
}