package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.ServiceErrorCode;
import com.clsaa.janus.admin.entity.po.SnapServiceErrorCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务端可能抛出的错误码快照 Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface SnapServiceErrorCodeDao {

    /**
     * 批量添加后端服务错误码快照
     *
     * @param list 后端服务错误码快照列表
     * @return 影响记录数
     */
    int addBatch(List<SnapServiceErrorCode> list);

    /**
     * 根据API快照id获取其服务错误码
     *
     * @param snapApiId API快照id
     * @return {@link List<SnapServiceErrorCode>}
     */
    List<SnapServiceErrorCode> getListBySnapApiId(@Param("snapApiId") String snapApiId);
}