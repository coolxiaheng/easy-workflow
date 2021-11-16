package com.winsse.sc.wf.service.impl;

import com.winsse.sc.wf.entity.Operation;
import com.winsse.sc.wf.entity.OperationUser;
import com.winsse.sc.wf.mapper.OperationUserMapper;
import com.winsse.sc.wf.service.IOperationUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaheng
 * @since 2021-10-15
 */
@Service
public class OperationUserServiceImpl extends ServiceImpl<OperationUserMapper, OperationUser> implements IOperationUserService {

    @Override
    public List<OperationUser> getOperationUserId(Integer nodeId, String filterCode, Integer operationId) {
        return baseMapper.getOperationUserId(nodeId, filterCode, operationId);
    }

    @Override
    public List<Operation> getUserOperationPermisstion(Integer nodeId, Integer userId) {
        return baseMapper.getUserOperationPermisstion(nodeId, userId);
    }
}
