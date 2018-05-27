package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.App;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 应用信息,包含前端请求API网关的认证信息AccessKey和AccessSecret
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface AppDao {

    /**
     * 添加应用信息
     *
     * @param app 应用信息持久层对象
     * @return 影响记录数
     */
    int add(App app);

    /**
     * 删除应用信息
     *
     * @param id 应用信息id
     * @return 影响记录数
     */
    int delById(@Param("id") String id);

    /**
     * 更新应用信息
     *
     * @param app 应用信息持久层对象
     * @return 影响记录数
     */
    int update(App app);

    /**
     * 根据id查询应用信息
     *
     * @param id 应用信息id
     * @return {@link App}
     */
    App getById(@Param("id") String id);

    /**
     * 获取应用信息分页数据总量
     *
     * @param keyword 关键词
     * @return 分页数据总量
     */
    int getPaginationCount(@Param("keyword") String keyword);

    /**
     * 获取应用信息分页数据
     *
     * @param keyword   关键词
     * @param rowOffset 页偏移
     * @param pageSize  页大小
     * @return {@link List<App>}
     */
    List<App> getPaginationList(@Param("keyword") String keyword,
                                @Param("rowOffset") Integer rowOffset,
                                @Param("pageSize") Integer pageSize);
}