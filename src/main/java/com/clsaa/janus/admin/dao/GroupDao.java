package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * API分组信息 Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface GroupDao {
    /**
     * 插入API分组
     *
     * @param group
     * @return 添加记录条数
     */
    int add(Group group);

    /**
     * 删除API分组
     *
     * @param id API分组id
     * @return 更新记录数
     */
    int delById(@Param("id") String id);

    /**
     * 更新API分组
     *
     * @param group API分组持久层对象
     * @return 更新记录数
     */
    int update(Group group);

    /**
     * 根据id查询API分组
     *
     * @param id API分组id
     * @return {@link Group}
     */
    Group getById(@Param("id") String id);

    /**
     * 根据名称查询API分组
     *
     * @param name API分组名称
     * @return {@link Group}
     */
    Group getByName(@Param("name") String name);

    /**
     * 获取分页数据量
     *
     * @param regionId 地域id
     * @param keyword  关键词
     * @return 分页数据量
     */
    int getPaginationCount(@Param("regionId") String regionId, @Param("keyword") String keyword);

    /**
     * 获取分页数据
     *
     * @param regionId  地域id
     * @param keyword   关键词
     * @param rowOffset 页偏移量
     * @param pageSize  页大小
     * @return {@link List<Group>}
     */
    List<Group> getPaginationList(@Param("regionId") String regionId, @Param("keyword") String keyword,
                                  @Param("rowOffset") int rowOffset, @Param("pageSize") int pageSize);
}