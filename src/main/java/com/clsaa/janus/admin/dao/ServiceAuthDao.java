package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.ServiceAuth;

import java.util.List;

/**
 * <p>
 * API后端服务认证信息,包含AccessKey和AccessSecret Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface ServiceAuthDao {

    /**
     * 添加后端服务认证信息
     *
     * @param serviceAuth 后端服务认证信息持久层对象
     * @return 影响记录数
     */
    int add(ServiceAuth serviceAuth);

    /**
     * 删除后端服务认证信息
     *
     * @param id 后端服务认证信息id
     * @return 影响记录数
     */
    int delById(String id);

    /**
     * 更新后端服务认证信息
     *
     * @param serviceAuth 后端服务认证信息持久层对象
     * @return 影响记录数
     */
    int update(ServiceAuth serviceAuth);

    /**
     * 根据id查询后端服务认证信息
     *
     * @param id 后端服务认证信息id
     * @return {@link ServiceAuth}
     */
    ServiceAuth getById(String id);

    /**
     * 获取后端服务认证信息分页数据总量
     *
     * @param regionId 地域id
     * @param keyword  关键词
     * @return 分页数据总量
     */
    int getPaginationCount(String regionId, String keyword);

    /**
     * 获取后端服务认证信息分页数据
     * @param regionId 地域id
     * @param keyword   关键词
     * @param rowOffset    页偏移
     * @param pageSize  页大小
     * @return
     */
    List<ServiceAuth> getPaginationList(String regionId, String keyword, Integer rowOffset, Integer pageSize);
}