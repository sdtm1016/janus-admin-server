package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.config.BizCodes;
import com.clsaa.janus.admin.dao.SnapRequestParamDao;
import com.clsaa.janus.admin.entity.po.RequestParam;
import com.clsaa.janus.admin.entity.po.SnapRequestParam;
import com.clsaa.janus.admin.entity.vo.v1.SnapRequestParamV1;
import com.clsaa.janus.admin.result.BizAssert;
import com.clsaa.janus.admin.util.BeanUtils;
import com.clsaa.janus.admin.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 客户端到网关的请求参数快照 服务实现类
 * </p>
 *
 * @author 任贵杰 812022339@qq.com
 * @since 2018-05-17
 */
@Service
public class SnapRequestParamService {
    @Autowired
    private SnapRequestParamDao snapRequestParamDao;
    @Autowired
    private RequestParamService requestParamService;

    /**
     * 批量添加请求参数快照
     *
     * @param snapApiId API快照id
     * @param apiId     要创建快照的APIid
     * @return 是否添加成功
     */
    public boolean addSnapRequestParamBatch(String snapApiId, String apiId) {
        List<RequestParam> requestParamList = this.requestParamService.getRequestParamListByApiId(apiId);
        if (requestParamList.size() == 0){
            return true;
        }
        List<SnapRequestParam> snapRequestParamList = requestParamList
                .stream()
                .map(r -> {
                    SnapRequestParam snapRequestParam = BeanUtils.convertType(r, SnapRequestParam.class);
                    snapRequestParam.setId(UUIDUtil.getUUID());
                    snapRequestParam.setSnapApiId(snapApiId);
                    return snapRequestParam;
                })
                .collect(Collectors.toList());
        int count = 0;
        try {
            count = this.snapRequestParamDao.addBatch(snapRequestParamList);
        } catch (Exception e) {
            BizAssert.pass(count == snapRequestParamList.size(),
                    BizCodes.ERROR_INSERT.getCode(), "请求参数保存失败");
        }
        BizAssert.pass(count == snapRequestParamList.size(),
                BizCodes.ERROR_INSERT.getCode(), "请求参数保存失败");
        return true;
    }


    /**
     * 根据API快照id查询其全部请求参数
     *
     * @param snapApiId API快照id
     * @return {@link List<SnapRequestParamV1>}
     */
    public List<SnapRequestParamV1> getRequestParamV1ListBySnapApiId(String snapApiId) {
        return this.snapRequestParamDao.getListBySnapApiId(snapApiId)
                .stream().map(r -> BeanUtils.convertType(r, SnapRequestParamV1.class)).collect(Collectors.toList());
    }
}
