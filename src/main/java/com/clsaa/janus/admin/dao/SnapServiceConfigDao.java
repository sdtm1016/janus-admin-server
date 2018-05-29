package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.SnapServiceConfig;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 网关到服务端的请求配置快照信息 Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface SnapServiceConfigDao {

    /**
     * 添加后端服务配置快照
     *
     * @param snapServiceConfig 后端服务配置快照信息
     * @return 影响记录数
     */
    int add(SnapServiceConfig snapServiceConfig);

    /**
     * 根据API快照id查询其后端服务配置快照
     *
     * @param snapApiId API快照id
     * @return {@link SnapServiceConfig}
     */
    SnapServiceConfig getBySnapApiId(@Param("snapApiId") String snapApiId);
}