package com.winsse.sc.wf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winsse.sc.wf.entity.Operation;
import com.winsse.sc.wf.entity.OperationUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaheng
 * @since 2021-10-15
 */
public interface OperationUserMapper extends BaseMapper<OperationUser> {

    /**
     * 获得用户ID
     * @param nodeId
     * @param filterCode
     * @return
     */
    List<OperationUser> getOperationUserId(@Param("nodeId")Integer nodeId,
                                           @Param("filterCode")String filterCode,
                                           @Param("operationId")Integer operationId);

    /**
     * 获得用户权限
     * @author xiaheng
     * @date 2021/10/19 16:29
     * @param nodeId
     * @param userId
     * @return java.util.List<com.winsse.sc.wf.entity.Operation>
     */
    List<Operation> getUserOperationPermisstion(@Param("nodeId")Integer nodeId,
                                                @Param("userId")Integer userId);
}
