package com.winsse.sc.wf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.winsse.sc.wf.entity.OperationLog;
import com.winsse.sc.wf.entity.Process;
import com.winsse.sc.wf.entity.vo.ProcessVo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * OperationLogService
 * @author xiaheng
 * @date 2021/10/20 9:54
 */
public interface IOperationLogService extends IService<OperationLog> {

    /**
     * 保存工作流操作
     * @author xiaheng
     * @date 2021/10/15 17:13
     * @param workFlowId
     * @param eventId
     * @param operationId
     * @param filterCode
     * @param userId
     * @param suggest
     */
    Integer saveOperation(Integer workFlowId, Integer eventId,
                          Integer operationId, String filterCode,
                          Integer userId, String suggest,
                          String userName, List<Integer> nextNodeUserIds,
                          LocalDateTime operationDate);

    /**
     * 获得用户角色当前节点得过程流数据
     * @author xiaheng
     * @date 2021/10/15 15:21
     * @param nodeId
     * @param userId
     * @return java.util.List<com.winsse.sc.wf.entity.Process>
     */
    IPage<Process> getProcessByUserId(Page<?> page, Integer nodeId, Integer userId);

    /**
     * 获得操作日志
     * @author xiaheng
     * @date 2021/10/18 11:41
     * @param eventId
     * @return java.util.List<com.winsse.sc.wf.entity.vo.ProcessVo>
     */
    List<ProcessVo> getLog(Integer eventId, Integer workFlowId);
}
