package com.winsse.sc.wf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winsse.sc.wf.entity.Operation;
import com.winsse.sc.wf.entity.OperationUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaheng
 * @since 2021-10-15
 */
public interface IOperationUserService extends IService<OperationUser> {

    /**
     * 获得用户ID
     * @param nodeId
     * @param filterCode
     * @return
     */
    List<OperationUser> getOperationUserId(Integer nodeId, String filterCode, Integer operationId);

    /**
     * 获得用户权限
     * @author xiaheng
     * @date 2021/10/19 16:29
     * @param nodeId
     * @param userId
     * @return java.util.List<com.winsse.sc.wf.entity.Operation>
     */
    List<Operation> getUserOperationPermisstion(Integer nodeId,
                                                Integer userId);
}
