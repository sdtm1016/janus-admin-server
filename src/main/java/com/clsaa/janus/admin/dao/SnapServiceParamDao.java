package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.SnapServiceParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 网关请求到服务端的参数快照 Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface SnapServiceParamDao {

    /**
     * 批量添加后端服务请求参数快照
     *
     * @param list 后端服务请求参数快照列表
     * @return 影响记录数
     */
    int addBatch(List<SnapServiceParam> list);

    /**
     * 根据API快照id获取其后端服务参数
     *
     * @param snapApiId API快照id
     * @return {@link List<SnapServiceParam>}
     */
    List<SnapServiceParam> getListBySnapApiId(@Param("snapApiId") String snapApiId);
}