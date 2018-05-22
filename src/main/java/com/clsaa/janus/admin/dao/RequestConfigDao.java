package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.RequestConfig;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 客户端到网关的请求配置信息 Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface RequestConfigDao {

    /**
     * 添加请求配置
     *
     * @param requestConfig 请求配置持久层对象
     * @return 影响记录数
     */
    int add(RequestConfig requestConfig);

    /**
     * 根据ApiId删除请求配置
     *
     * @param apiId
     * @return 影响记录数
     */
    int delByApiId(String apiId);

    /**
     * 更新请求配置
     *
     * @param requestConfig 请求配置视图层对象
     * @return 影响记录数
     */
    int update(RequestConfig requestConfig);

    /**
     * 根据ApiId获取请求配置
     *
     * @param apiId APIid
     * @return {@link RequestConfig}
     */
    RequestConfig getByApiId(@Param("apiId") String apiId);
}