package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.Api;
import com.clsaa.janus.admin.entity.po.SnapApi;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * API快照信息 Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface SnapApiDao {

    /**
     * 添加API快照信息
     *
     * @param api API快照持久层对象
     * @return 影响记录数
     */
    int add(SnapApi api);

    /**
     * 根据id查询API快照信息
     *
     * @param id API快照id
     * @return {@link SnapApi}
     */
    SnapApi getById(@Param("id") String id);
}