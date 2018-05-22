package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.RequestParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 客户端到网关的请求参数 Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface RequestParamDao {

    /**
     * 批量添加请求参数
     *
     * @param list 批量添加请求参数列表
     * @return 影响记录数
     */
    int addBatch(List<RequestParam> list);

    /**
     * 根据ApiId删除某个API全部请求参数
     *
     * @param apiId ApiId
     */
    void delByApiId(@Param("apiId") String apiId);

    /**
     * 根据ApiId查询其全部请求参数
     *
     * @param apiId APIId
     * @return {@link List<RequestParam>}
     */
    List<RequestParam> getListByApiId(@Param("apiId") String apiId);
}