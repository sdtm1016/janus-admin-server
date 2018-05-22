package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.ServiceParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 网关请求到服务端的参数 Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface ServiceParamDao {

    /**
     * 批量添加后端服务请求参数
     *
     * @param serviceParamList 后端服务请求参数列表
     * @return 影响记录数
     */
    int addBatch(List<ServiceParam> serviceParamList);

    /**
     * 根据APIId删除其后端服务请求参数
     *
     * @param apiId APIId
     * @return 影响记录数
     */
    int delByApiId(@Param("apiId") String apiId);

    /**
     * 根据APIId获取其后端服务参数
     *
     * @param apiId APIId
     * @return {@link List<ServiceParam>}
     */
    List<ServiceParam> getListByApiId(@Param("apiId") String apiId);
}