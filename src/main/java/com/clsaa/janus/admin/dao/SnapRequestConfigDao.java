package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.SnapRequestConfig;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 客户端到网关的请求配置快照信息 Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-29
 */
public interface SnapRequestConfigDao {

    /**
     * 添加请求配置
     *
     * @param snapRequestConfig 请求配置快照持久层对象
     * @return 影响记录数
     */
    int add(SnapRequestConfig snapRequestConfig);

    /**
     * 根据API快照id获取请求配置
     *
     * @param snapApiId API快照id
     * @return {@link SnapRequestConfig}
     */
    SnapRequestConfig getBySnapApiId(@Param("snapApiId") String snapApiId);
}