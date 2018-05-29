package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.SnapServiceConstParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 网关请求到服务端的常量参数快照 Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface SnapServiceConstParamDao {

    /**
     * 批量添加后端服务常量参数快照
     *
     * @param list 后端服务常量参数快照列表
     * @return 是否添加成功
     */
    int addBatch(List<SnapServiceConstParam> list);

    /**
     * 根据API快照Id查询其常量参数列表
     *
     * @param snapApiId API快照Id
     * @return {@link List<SnapServiceConstParam>}
     */
    List<SnapServiceConstParam> getListBySnapApiId(@Param("snapApiId") String snapApiId);
}