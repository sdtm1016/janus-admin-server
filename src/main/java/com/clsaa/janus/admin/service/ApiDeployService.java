package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.dao.ApiDeployDao;
import com.clsaa.janus.admin.entity.po.ApiDeploy;
import com.clsaa.janus.admin.entity.vo.v1.ApiDeployV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.result.Pagination;
import com.clsaa.janus.admin.util.BeanUtils;
import com.clsaa.janus.admin.util.TimestampUtil;
import com.clsaa.janus.admin.util.UUIDUtil;
import com.clsaa.janus.admin.util.VersionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * API部署信息 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class ApiDeployService {
    @Autowired
    private ApiDeployDao apiDeployDao;
    @Autowired
    private SnapApiService snapApiService;

    /**
     * 创建API部署信息
     *
     * @param loginUserId   登录用户id
     * @param apiId         APIid
     * @param environmentId 环境id
     * @param description   描述
     * @return API部署信息id
     */
    @Transactional(rollbackFor = Exception.class)
    public String addApiDeploy(String loginUserId, String apiId, String environmentId, String description) {
        this.apiDeployDao.updateStatusByApiIdAndEnvId(apiId, environmentId, ApiDeploy.STATUS_OFFLINE, loginUserId);
        String snapApiId = this.snapApiService.addSnapApi(apiId);
        ApiDeploy apiDeploy = new ApiDeploy(UUIDUtil.getUUID(), apiId, environmentId, snapApiId, VersionUtil.newCode(),
                description, TimestampUtil.now(), loginUserId, TimestampUtil.now(), loginUserId, ApiDeploy.STATUS_ONLINE);
        int count = 0;
        try {
            count = this.apiDeployDao.add(apiDeploy);
        } catch (Exception e) {
            BizAssert.pass(count == 1, BizCodes.ERROR_INSERT);
        }
        BizAssert.pass(count == 1, BizCodes.ERROR_INSERT);
        return apiDeploy.getApiId();
    }

    /**
     * 切换某个API部署信息上线
     *
     * @param loginUserId   登录用户id
     * @param apiDeployId   API部署信息id
     * @param apiId         APIid
     * @param environmentId 环境id
     * @param description   描述
     * @return 是否切换成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateApiDeployToOnline(String loginUserId, String apiDeployId, String apiId, String environmentId, String description) {
        ApiDeploy apiDeploy = this.apiDeployDao.getById(apiDeployId);
        BizAssert.found(apiDeploy != null, BizCodes.INVALID_PARAM.getCode(), "无法找到API部署信息");
        BizAssert.found(apiDeploy.getApiId().equals(apiId), BizCodes.INVALID_PARAM.getCode(), "ApiId非法");
        BizAssert.found(apiDeploy.getEnvironmentId().equals(environmentId), BizCodes.INVALID_PARAM.getCode(), "API部署环境非法");
        apiDeploy.setStatus(ApiDeploy.STATUS_ONLINE);
        apiDeploy.setDescription(description);
        apiDeploy.setMtime(TimestampUtil.now());
        apiDeploy.setMuser(loginUserId);
        this.apiDeployDao.updateStatusByApiIdAndEnvId(apiId, environmentId, ApiDeploy.STATUS_OFFLINE, loginUserId);
        int count = 0;
        try {
            count = this.apiDeployDao.update(apiDeploy);
        } catch (Exception e) {
            BizAssert.pass(count == 1, BizCodes.ERROR_UPDATE);
        }
        BizAssert.pass(count == 1, BizCodes.ERROR_UPDATE);
        return true;
    }

    /**
     * 将API在某个环境下线
     *
     * @param loginUserId   登录用户id
     * @param apiId         APIid
     * @param environmentId 环境id
     * @return 是否下线成功
     * @author 任贵杰 812022339@qq.com
     * @since 2018-05-29
     */
    public boolean updateApiDeployToOffline(String loginUserId, String apiId, String environmentId) {
        try {
            this.apiDeployDao.updateStatusByApiIdAndEnvId(apiId, environmentId, ApiDeploy.STATUS_OFFLINE, loginUserId);
        } catch (Exception e) {
            BizAssert.pass(false, BizCodes.ERROR_UPDATE);
        }
        return true;
    }

    /**
     * 分页查询API信息
     *
     * @param loginUserId   登录用户id
     * @param apiId         APIid
     * @param environmentId 环境id
     * @param pageNo        页号
     * @param pageSize      页大小
     * @return {@link Pagination<ApiDeployV1>}
     */
    public Pagination<ApiDeployV1> getApiDeployV1Pagination(String loginUserId, String apiId, String environmentId, Integer pageNo, Integer pageSize) {
        int count = this.apiDeployDao.getPaginationCount(apiId, environmentId);
        Pagination<ApiDeployV1> pagination = new Pagination<>();
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        pagination.setTotalCount(count);
        if (count == 0) {
            pagination.setPageList(Collections.emptyList());
            return pagination;
        }
        List<ApiDeploy> apiDeployList = this.apiDeployDao.getPaginationList(apiId, environmentId, pagination.getRowOffset(), pagination.getPageSize());
        pagination.setPageList(apiDeployList.stream().map(s -> BeanUtils.convertType(s, ApiDeployV1.class)).collect(Collectors.toList()));
        return pagination;
    }
}
