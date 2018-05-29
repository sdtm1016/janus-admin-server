package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.SnapRequestParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 客户端到网关的请求参数快照 Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface SnapRequestParamDao {

    /**
     * 批量添加请求参数快照
     *
     * @param list 批量添加请求参数快照列表
     * @return 影响记录数
     */
    int addBatch(List<SnapRequestParam> list);

    /**
     * 根据API快照id查询其全部请求参数快照
     *
     * @param snapApiId API快照id
     * @return {@link List<SnapRequestParam>}
     */
    List<SnapRequestParam> getListBySnapApiId(@Param("snapApiId") String snapApiId);
}