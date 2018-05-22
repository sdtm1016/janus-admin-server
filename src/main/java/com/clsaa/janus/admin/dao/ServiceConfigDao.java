package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.ServiceConfig;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 网关到服务端的请求配置信息 Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface ServiceConfigDao {

    /**
     * 添加后端服务配置
     *
     * @param serviceConfig 后端服务配置信息
     */
    int add(ServiceConfig serviceConfig);

    /**
     * 根据APIId删除其对应后端服务配置
     *
     * @param apiId APIId
     * @return 影响记录数
     */
    int delByApiId(@Param("apiId") String apiId);

    /**
     * 根据APIId查询其后端服务配置
     *
     * @param apiId APIId
     * @return {@link ServiceConfig}
     */
    ServiceConfig getByApiId(String apiId);
}