package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.Auth;

import java.util.List;

/**
 * <p>
 * API认证信息,包含AccessKey和AccessSecret Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface AuthDao {

    /**
     * 添加认证信息
     *
     * @param auth 认证信息持久层对象
     * @return 影响记录数
     */
    int add(Auth auth);

    /**
     * 删除认证信息
     *
     * @param id 认证信息id
     * @return 影响记录数
     */
    int delById(String id);

    /**
     * 更新认证信息
     *
     * @param auth 认证信息持久层对象
     * @return 影响记录数
     */
    int update(Auth auth);

    /**
     * 根据id查询认证信息
     *
     * @param id 认证信息id
     * @return {@link Auth}
     */
    Auth getById(String id);

    /**
     * 获取认证信息分页数据总量
     *
     * @param regionId 地域id
     * @param keyword  关键词
     * @return 分页数据总量
     */
    int getPaginationCount(String regionId, String keyword);

    /**
     * 获取认证信息分页数据
     * @param regionId 地域id
     * @param keyword   关键词
     * @param rowOffset    页偏移
     * @param pageSize  页大小
     * @return
     */
    List<Auth> getPaginationList(String regionId, String keyword, Integer rowOffset, Integer pageSize);
}