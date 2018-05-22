package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.ServiceConstParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 网关请求到服务端的常量参数 Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface ServiceConstParamDao {

    /**
     * 批量添加后端服务常量参数
     *
     * @param serviceConstParamList 后端服务常量参数列表
     * @return 是否添加成功
     */
    int addBatch(List<ServiceConstParam> serviceConstParamList);

    /**
     * 通过APIId删除其后端常量参数
     *
     * @param apiId APIId
     * @return 影响记录数
     */
    int delByApiId(@Param("apiId") String apiId);

    /**
     * 根据APIId查询其常量参数列表
     *
     * @param apiId
     * @return {@link List<ServiceConstParam>}
     */
    List<ServiceConstParam> getListByApiId(@Param("apiId") String apiId);
}