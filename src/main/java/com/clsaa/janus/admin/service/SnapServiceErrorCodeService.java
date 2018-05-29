package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.dao.SnapServiceErrorCodeDao;
import com.clsaa.janus.admin.entity.po.ServiceErrorCode;
import com.clsaa.janus.admin.entity.po.SnapServiceErrorCode;
import com.clsaa.janus.admin.entity.vo.v1.SnapServiceErrorCodeV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.util.BeanUtils;
import com.clsaa.janus.admin.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务端可能抛出的错误码快照 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class SnapServiceErrorCodeService {
    @Autowired
    private SnapServiceErrorCodeDao snapServiceErrorCodeDao;
    @Autowired
    private ServiceErrorCodeService serviceErrorCodeService;

    /**
     * 批量添加后端服务错误码快照
     *
     * @param snapApiId API快照id
     * @param apiId     APIid
     * @return 是否添加成功
     */
    public boolean addSnapServiceErrorCodeBatch(String snapApiId, String apiId) {
        List<ServiceErrorCode> serviceErrorCodeList = this.serviceErrorCodeService.getServiceErrorCodeListByApiId(apiId);
        if (serviceErrorCodeList.size() == 0) {
            return true;
        }
        List<SnapServiceErrorCode> snapServiceErrorCodeList = serviceErrorCodeList
                .stream()
                .map(e -> {
                    SnapServiceErrorCode snapServiceErrorCode = BeanUtils.convertType(e, SnapServiceErrorCode.class);
                    snapServiceErrorCode.setId(UUIDUtil.getUUID());
                    snapServiceErrorCode.setSnapApiId(snapApiId);
                    return snapServiceErrorCode;
                }).collect(Collectors.toList());
        int count = 0;
        try {
            count = this.snapServiceErrorCodeDao.addBatch(snapServiceErrorCodeList);
        } catch (Exception e) {
            BizAssert.pass(count == snapServiceErrorCodeList.size(), BizCodes.ERROR_INSERT);
        }
        BizAssert.pass(count == snapServiceErrorCodeList.size(), BizCodes.ERROR_INSERT);
        return true;
    }

    /**
     * 根据API快照id获取其服务错误码快照
     *
     * @param snapApiId API快照id
     * @return {@link List<SnapServiceErrorCodeV1>}
     */
    public List<SnapServiceErrorCodeV1> getSnapServiceErrorCodeV1ListBySnapApiId(String snapApiId) {
        return this.snapServiceErrorCodeDao.getListBySnapApiId(snapApiId)
                .stream().map(s -> BeanUtils.convertType(s, SnapServiceErrorCodeV1.class)).collect(Collectors.toList());
    }
}
