package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.config.JanusProperties;
import com.clsaa.janus.admin.dao.AppDao;
import com.clsaa.janus.admin.entity.po.App;
import com.clsaa.janus.admin.entity.vo.v1.AppV1;
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
 * 应用信息,包含前端请求API网关的认证信息AccessKey和AccessSecret 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class AppService {

    @Autowired
    private AppDao appDao;
    @Autowired
    private JanusProperties properties;

    /**
     * 添加应用信息
     *
     * @param loginUserId 登录用户id
     * @param name        名称
     * @param description 描述
     * @return 应用信息主键ID
     */
    public String addApp(String loginUserId, String name, String description) {
        String accessKey = UUIDUtil.getUUID();
        String accessSecret = UUIDUtil.getUUID();
        //AccessSecret在数据库存储密文
        CryptoResult cryptoResult = SlowlyAes.encrypt(properties.getApp().getAuth().getAesKey().getAccessSecret().getBytes(), accessSecret);
        BizAssert.pass(cryptoResult.isOK(), "加密失败:" + cryptoResult.getStatus());
        //保存应用信息
        App app = new App(UUIDUtil.getUUID(), accessKey, cryptoResult.getContent(), name, description, TimestampUtil.now(),
                loginUserId, TimestampUtil.now(), loginUserId);
        int count = 0;
        try {
            count = this.appDao.add(app);
        } catch (Exception e) {
            BizAssert.pass(count == 1, BizCodes.ERROR_INSERT);
            e.printStackTrace();
        }
        BizAssert.pass(count == 1, BizCodes.ERROR_INSERT);
        return app.getId();
    }


    /**
     * 删除应用信息
     *
     * @param loginUserId 登录用户id
     * @param appId       应用信息id
     * @return 是否删除成功
     */
    public boolean delAppById(String loginUserId, String appId) {
        int count = 0;
        try {
            count = this.appDao.delById(appId);
        } catch (Exception e) {
            BizAssert.validParam(count == 1, BizCodes.ERROR_DELETE);
        }
        BizAssert.validParam(count == 1, BizCodes.ERROR_DELETE);
        return true;
    }

    /**
     * 更新应用信息
     *
     * @param loginUserId 登录用户id
     * @param appId       应用信息id
     * @param name        名称
     * @param description 描述
     * @return 是否修改成功
     */
    public boolean updateApp(String loginUserId, String appId, String name, String description) {
        //判断后端服务认证信息是否存在
        App app = this.appDao.getById(appId);
        BizAssert.found(app != null, BizCodes.INVALID_PARAM.getCode(), "应用信息不存在");
        //修改数据
        app.setMtime(TimestampUtil.now());
        app.setMuser(loginUserId);
        app.setName(name);
        app.setDescription(description);
        int count = 0;
        try {
            count = this.appDao.update(app);
        } catch (Exception e) {
            BizAssert.validParam(count == 1, BizCodes.ERROR_UPDATE);
        }
        BizAssert.validParam(count == 1, BizCodes.ERROR_UPDATE);
        return true;
    }

    /**
     * 更新应用AccessSecret
     *
     * @param loginUserId 登录用户id
     * @param appId       应用信息id
     * @return 是否更新成功
     */
    public boolean updateAppAccessSecret(String loginUserId, String appId) {
        //判断后端服务认证信息是否存在
        App app = this.appDao.getById(appId);
        BizAssert.found(app != null, BizCodes.INVALID_PARAM.getCode(), "应用信息不存在");
        String accessSecret = UUIDUtil.getUUID();
        CryptoResult cryptoResult = SlowlyAes.encrypt(properties.getApp().getAuth().getAesKey().getAccessSecret().getBytes(), accessSecret);
        BizAssert.pass(cryptoResult.isOK(), BizCodes.ERROR_UPDATE.getCode(), "加密失败:" + cryptoResult.getStatus().name());
        //修改数据
        app.setAccessSecret(cryptoResult.getContent());
        app.setMtime(TimestampUtil.now());
        app.setMuser(loginUserId);
        int count = 0;
        try {
            count = this.appDao.update(app);
        } catch (Exception e) {
            BizAssert.validParam(count == 1, BizCodes.ERROR_UPDATE);
        }
        BizAssert.validParam(count == 1, BizCodes.ERROR_UPDATE);
        return true;
    }

    /**
     * 根据id查询应用详情
     *
     * @param loginUserId 登录用户id
     * @param appId       应用信息id
     * @return {@link AppV1}
     */
    public AppV1 getAppV1ById(String loginUserId, String appId) {
        App app = this.appDao.getById(appId);
        if (app == null) {
            return null;
        }
        CryptoResult cryptoResult = SlowlyAes.decrypt(properties.getApp().getAuth().getAesKey().getAccessSecret().getBytes(), app.getAccessSecret());
        BizAssert.pass(cryptoResult.isOK(), "解密失败:" + cryptoResult.getStatus());
        app.setAccessSecret(cryptoResult.getContent());
        return BeanUtils.convertType(app, AppV1.class);
    }

    /**
     * 分页查询应用信息
     *
     * @param loginUserId 登录用户id
     * @param keyword     关键词
     * @param pageNo      页号
     * @param pageSize    页大小
     * @return {@link Pagination<AppV1>}
     */
    public Pagination<AppV1> getAppV1Pagination(String loginUserId, String keyword, Integer pageNo, Integer pageSize) {
        int count = this.appDao.getPaginationCount(keyword);
        Pagination<AppV1> pagination = new Pagination<>();
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        pagination.setTotalCount(count);
        if (count == 0) {
            pagination.setPageList(Collections.emptyList());
            return pagination;
        }
        List<App> appList = this.appDao.getPaginationList(keyword, pagination.getRowOffset(), pagination.getPageSize());
        pagination.setPageList(appList.stream()
                .map(a -> BeanUtils.convertType(a, AppV1.class))
                .peek(a -> a.setAccessSecret("******"))
                .collect(Collectors.toList()));
        return pagination;
    }


}
