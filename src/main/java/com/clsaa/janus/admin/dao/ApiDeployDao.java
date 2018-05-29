package com.clsaa.janus.admin.dao;


import com.clsaa.janus.admin.entity.po.ApiDeploy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * API部署信息 Mapper 接口
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
public interface ApiDeployDao {

    /**
     * 创建API部署信息
     *
     * @param apiDeploy API部署信息持久层对象
     * @return 影响记录数
     */
    int add(ApiDeploy apiDeploy);

    /**
     * 更新API部署信息状态
     *
     * @param apiId         APIid
     * @param environmentId 环境id
     * @param statusTo      目标状态
     * @param muser         修改人
     * @return 影响记录数
     */
    int updateStatusByApiIdAndEnvId(@Param("apiId") String apiId,
                                    @Param("environmentId") String environmentId,
                                    @Param("statusTo") int statusTo,
                                    @Param("muser") String muser);

    /**
     * 更新API部署信息,只会更新状态及描述
     *
     * @param apiDeploy API部署信息持久层对象
     * @return 影响记录数
     */
    int update(ApiDeploy apiDeploy);

    /**
     * 查询分页数据总量
     *
     * @param apiId         APIid
     * @param environmentId 环境id
     * @return 分页数据总量
     */
    int getPaginationCount(@Param("apiId") String apiId,
                           @Param("environmentId") String environmentId);

    /**
     * 查询分页数据
     *
     * @param apiId         APIid
     * @param environmentId 环境id
     * @param rowOffset     偏移量
     * @param pageSize      页大小
     * @return 分页数据总量
     */
    List<ApiDeploy> getPaginationList(@Param("apiId") String apiId,
                                      @Param("environmentId") String environmentId,
                                      @Param("rowOffset") Integer rowOffset,
                                      @Param("pageSize") Integer pageSize);

    /**
     * 根据id查询API部署信息
     *
     * @param id API部署信息id
     * @return {@link ApiDeploy}
     */
    ApiDeploy getById(@Param("id") String id);
}