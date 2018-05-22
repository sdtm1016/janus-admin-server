package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.ServiceErrorCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务端可能抛出的错误码 Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface ServiceErrorCodeDao {

    /**
     * 批量添加后端服务错误码
     *
     * @param list 后端服务错误码列表
     * @return
     */
    int addBatch(List<ServiceErrorCode> list);

    /**
     * 根据APIId删除其错误码
     *
     * @param apiId APIId
     * @return 影响记录数
     */
    int delByApiId(@Param("apiId") String apiId);

    /**
     * 根据APIId获取其服务错误码
     *
     * @param apiId APIId
     * @return {@link List<ServiceErrorCode>}
     */
    List<ServiceErrorCode> getListByApiId(@Param("apiId") String apiId);
}