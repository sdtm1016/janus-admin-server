package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.config.JanusProperties;
import com.clsaa.janus.admin.dao.AuthDao;
import com.clsaa.janus.admin.entity.po.Auth;
import com.clsaa.janus.admin.entity.vo.AuthV1;
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
 * API认证信息,包含AccessKey和AccessSecret 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class AuthService {

    @Autowired
    private AuthDao authDao;
    @Autowired
    private JanusProperties properties;

    /**
     * 添加认证信息
     *
     * @param loginUserId
     * @param regionId     地域id
     * @param accessKey    AccessKey
     * @param accessSecret AccessSecret
     * @param name         名称
     * @param description  描述
     * @return 认证信息主键ID
     */
    public String addAuth(String loginUserId, String regionId, String accessKey, String accessSecret, String name, String description) {
        //AccessSecret在数据库存储密文
        CryptoResult cryptoResult = SlowlyAes.encrypt(properties.getAuth().getAccessKey().getAesKey().getBytes(), accessSecret);
        BizAssert.pass(cryptoResult.isOK(), "加密失败:" + cryptoResult.getStatus());

        Auth auth = new Auth(UUIDUtil.getUUID(), regionId, accessKey, cryptoResult.getContent(), name, description, TimestampUtil.now(),
                loginUserId, TimestampUtil.now(), loginUserId, Auth.STATUS_OK);
        int count = 0;
        try {
            count = this.authDao.add(auth);
        } catch (Exception e) {
            BizAssert.validParam(count == 0, BizCodes.ERROR_INSERT);
        }
        BizAssert.validParam(count == 0, BizCodes.ERROR_INSERT);
        return auth.getId();
    }


    /**
     * 删除认证信息
     *
     * @param loginUserId 登录用户id
     * @param authId      认证信息id
     * @return 是否删除成功
     */
    public boolean delAuthById(String loginUserId, String authId) {
        int count = 0;
        try {
            count = this.authDao.delById(authId);
        } catch (Exception e) {
            BizAssert.validParam(count == 0, BizCodes.ERROR_DELETE);
        }
        BizAssert.validParam(count == 0, BizCodes.ERROR_DELETE);
        return true;
    }

    /**
     * 更新认证信息
     *
     * @param loginUserId  登录用户id
     * @param authId       认证信息id
     * @param regionId     地域id
     * @param accessKey    AccessKey
     * @param accessSecret AccessSecret
     * @param name         名称
     * @param description  描述
     * @return 是否修改成功
     */
    public boolean updateAuth(String loginUserId, String authId, String regionId, String accessKey, String accessSecret, String name, String description) {
        //判断认证信息是否存在
        Auth auth = this.authDao.getById(authId);
        BizAssert.found(auth != null, BizCodes.INVALID_PARAM.getCode(), "认证信息不存在");
        //AccessSecret在数据库存储密文
        CryptoResult cryptoResult = SlowlyAes.encrypt(properties.getAuth().getAccessKey().getAesKey().getBytes(), accessSecret);
        BizAssert.pass(cryptoResult.isOK(), "加密失败:" + cryptoResult.getStatus());
        //修改数据,region不会被修改
        auth.setMtime(TimestampUtil.now());
        auth.setMuser(loginUserId);
        auth.setAccessKey(accessKey);
        auth.setAccessSecret(cryptoResult.getContent());
        auth.setName(name);
        auth.setDescription(description);
        int count = 0;
        try {
            count = this.authDao.update(auth);
        } catch (Exception e) {
            BizAssert.validParam(count == 0, BizCodes.ERROR_UPDATE);
        }
        BizAssert.validParam(count == 0, BizCodes.ERROR_UPDATE);
        return true;
    }

    /**
     * 根据id查询认证详情
     *
     * @param loginUserId 登录用户id
     * @param authId      认证信息id
     * @return {@link AuthV1}
     */
    public AuthV1 getAuthV1ById(String loginUserId, String authId) {
        Auth auth = this.authDao.getById(authId);
        CryptoResult cryptoResult = SlowlyAes.decrypt(properties.getAuth().getAccessKey().getAesKey().getBytes(), auth.getAccessSecret());
        BizAssert.pass(cryptoResult.isOK(), "解密失败:" + cryptoResult.getStatus());
        auth.setAccessSecret(cryptoResult.getContent());
        return BeanUtils.convertType(auth, AuthV1.class);
    }

    /**
     * 分页查询认证信息
     *
     * @param loginUserId 登录用户id
     * @param regionId    地域id
     * @param keyword     关键词
     * @param pageNo      页号
     * @param pageSize    页大小
     * @return {@link Pagination<AuthV1>}
     */
    public Pagination<AuthV1> getAuthV1Pagination(String loginUserId, String regionId, String keyword, Integer pageNo, Integer pageSize) {
        int count = this.authDao.getPaginationCount(regionId, keyword);
        Pagination<AuthV1> pagination = new Pagination<>();
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        pagination.setTotalCount(count);
        if (count == 0) {
            pagination.setPageList(Collections.emptyList());
            return pagination;
        }
        List<Auth> authList = this.authDao.getPaginationList(regionId, keyword, pagination.getRowOffset(), pagination.getPageSize());
        pagination.setPageList(authList.stream().map(a -> BeanUtils.convertType(a, AuthV1.class)).collect(Collectors.toList()));
        return pagination;
    }
}
