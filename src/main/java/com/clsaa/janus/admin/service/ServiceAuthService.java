package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.config.JanusProperties;
import com.clsaa.janus.admin.dao.ServiceAuthDao;
import com.clsaa.janus.admin.entity.po.ServiceAuth;
import com.clsaa.janus.admin.entity.vo.v1.ServiceAuthV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.result.Pagination;
import com.clsaa.janus.admin.security.crypto.CryptoResult;
import com.clsaa.janus.admin.security.crypto.SlowlyAes;
import com.clsaa.janus.admin.util.BeanUtils;
import com.clsaa.janus.admin.util.TimestampUtil;
import com.clsaa.janus.admin.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * API后端服务认证信息,包含AccessKey和AccessSecret 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class ServiceAuthService {

    @Autowired
    private ServiceAuthDao serviceAuthDao;
    @Autowired
    private JanusProperties properties;

    /**
     * 添加后端服务认证信息
     *
     * @param loginUserId
     * @param regionId     地域id
     * @param accessKey    AccessKey
     * @param accessSecret AccessSecret
     * @param name         名称
     * @param description  描述
     * @return 后端服务认证信息主键ID
     */
    public String addServiceAuth(String loginUserId, String regionId, String accessKey, String accessSecret, String name, String description) {
        //AccessKey在数据库存储密文
        CryptoResult akResult = SlowlyAes.encrypt(properties.getService().getAuth().getAesKey().getAccessKey().getBytes(), accessKey);
        BizAssert.pass(akResult.isOK(), "加密失败:" + akResult.getStatus().name());
        //AccessSecret在数据库存储密文
        CryptoResult asResult = SlowlyAes.encrypt(properties.getService().getAuth().getAesKey().getAccessSecret().getBytes(), accessSecret);
        BizAssert.pass(asResult.isOK(), "加密失败:" + asResult.getStatus().name());
        //保存后端认证信息
        ServiceAuth serviceAuth = new ServiceAuth(UUIDUtil.getUUID(), regionId, akResult.getContent(),
                asResult.getContent(), name, description, TimestampUtil.now(), loginUserId, TimestampUtil.now(), loginUserId);
        int count = 0;
        try {
            count = this.serviceAuthDao.add(serviceAuth);
        } catch (Exception e) {
            BizAssert.pass(count == 1, BizCodes.ERROR_INSERT);
            e.printStackTrace();
        }
        BizAssert.pass(count == 1, BizCodes.ERROR_INSERT);
        return serviceAuth.getId();
    }


    /**
     * 删除后端服务认证信息
     *
     * @param loginUserId   登录用户id
     * @param serviceAuthId 后端服务认证信息id
     * @return 是否删除成功
     */
    public boolean delServiceAuthById(String loginUserId, String serviceAuthId) {
        int count = 0;
        try {
            count = this.serviceAuthDao.delById(serviceAuthId);
        } catch (Exception e) {
            BizAssert.validParam(count == 1, BizCodes.ERROR_DELETE);
        }
        BizAssert.validParam(count == 1, BizCodes.ERROR_DELETE);
        return true;
    }

    /**
     * 更新后端服务认证信息
     *
     * @param loginUserId   登录用户id
     * @param serviceAuthId 后端服务认证信息id
     * @param regionId      地域id
     * @param accessKey     AccessKey
     * @param accessSecret  AccessSecret
     * @param name          名称
     * @param description   描述
     * @return 是否修改成功
     */
    public boolean updateServiceAuth(String loginUserId, String serviceAuthId, String regionId, String accessKey, String accessSecret, String name, String description) {
        //判断后端服务认证信息是否存在
        ServiceAuth serviceAuth = this.serviceAuthDao.getById(serviceAuthId);
        BizAssert.found(serviceAuth != null, BizCodes.INVALID_PARAM.getCode(), "后端服务认证信息不存在");
        //AccessKey在数据库存储密文
        CryptoResult akResult = SlowlyAes.encrypt(properties.getService().getAuth().getAesKey().getAccessKey().getBytes(), accessKey);
        BizAssert.pass(akResult.isOK(), "加密失败:" + akResult.getStatus().name());
        //AccessSecret在数据库存储密文
        CryptoResult asResult = SlowlyAes.encrypt(properties.getService().getAuth().getAesKey().getAccessSecret().getBytes(), accessSecret);
        BizAssert.pass(asResult.isOK(), "加密失败:" + asResult.getStatus().name());
        //修改数据,region不会被修改
        serviceAuth.setMtime(TimestampUtil.now());
        serviceAuth.setMuser(loginUserId);
        serviceAuth.setAccessKey(akResult.getContent());
        serviceAuth.setAccessSecret(asResult.getContent());
        serviceAuth.setName(name);
        serviceAuth.setDescription(description);
        int count = 0;
        try {
            count = this.serviceAuthDao.update(serviceAuth);
        } catch (Exception e) {
            BizAssert.validParam(count == 1, BizCodes.ERROR_UPDATE);
        }
        BizAssert.validParam(count == 1, BizCodes.ERROR_UPDATE);
        return true;
    }

    /**
     * 根据id查询认证详情
     *
     * @param loginUserId   登录用户id
     * @param serviceAuthId 后端服务认证信息id
     * @return {@link ServiceAuthV1}
     */
    public ServiceAuthV1 getServiceAuthV1ById(String loginUserId, String serviceAuthId) {
        ServiceAuth serviceAuth = this.serviceAuthDao.getById(serviceAuthId);
        if (serviceAuth == null) {
            return null;
        }
        //AccessKey在数据库存储密文
        CryptoResult akResult = SlowlyAes.decrypt(properties.getService().getAuth().getAesKey().getAccessKey().getBytes(), serviceAuth.getAccessKey());
        BizAssert.pass(akResult.isOK(), "解密失败:" + akResult.getStatus().name());
        //AccessSecret在数据库存储密文
        CryptoResult asResult = SlowlyAes.decrypt(properties.getService().getAuth().getAesKey().getAccessSecret().getBytes(), serviceAuth.getAccessSecret());
        BizAssert.pass(asResult.isOK(), "解密失败:" + asResult.getStatus().name());
        serviceAuth.setAccessKey(akResult.getContent());
        serviceAuth.setAccessSecret(asResult.getContent());
        return BeanUtils.convertType(serviceAuth, ServiceAuthV1.class);
    }

    /**
     * 分页查询后端服务认证信息
     *
     * @param loginUserId 登录用户id
     * @param regionId    地域id
     * @param keyword     关键词
     * @param pageNo      页号
     * @param pageSize    页大小
     * @return {@link Pagination< ServiceAuthV1 >}
     */
    public Pagination<ServiceAuthV1> getServiceAuthV1Pagination(String loginUserId, String regionId, String keyword, Integer pageNo, Integer pageSize) {
        int count = this.serviceAuthDao.getPaginationCount(regionId, keyword);
        Pagination<ServiceAuthV1> pagination = new Pagination<>();
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        pagination.setTotalCount(count);
        if (count == 0) {
            pagination.setPageList(Collections.emptyList());
            return pagination;
        }
        List<ServiceAuth> serviceAuthList = this.serviceAuthDao.getPaginationList(regionId, keyword, pagination.getRowOffset(), pagination.getPageSize());
        pagination.setPageList(serviceAuthList.stream()
                .map(a -> BeanUtils.convertType(a, ServiceAuthV1.class))
                .peek(a -> a.setAccessKey("******"))
                .peek(a -> a.setAccessSecret("******"))
                .collect(Collectors.toList()));
        return pagination;
    }
}
