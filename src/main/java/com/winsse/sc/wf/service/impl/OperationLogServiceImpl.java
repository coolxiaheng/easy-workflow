package com.winsse.sc.wf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.winsse.sc.wf.entity.Process;
import com.winsse.sc.wf.entity.*;
import com.winsse.sc.wf.entity.vo.OperationLogVo;
import com.winsse.sc.wf.entity.vo.ProcessVo;
import com.winsse.sc.wf.feign.IMFeignService;
import com.winsse.sc.wf.mapper.OperationLogMapper;
import com.winsse.sc.wf.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {
    private static Logger logger = LoggerFactory.getLogger(OperationLogServiceImpl.class);

    @Autowired
    private IProcessService processService;
    @Autowired
    private IOperationLogService operationLogService;
    @Autowired
    private INodeFlowService nodeFlowService;
    @Autowired
    private INodeService nodeService;
    @Autowired
    private IOperationUserService operationUserService;
    @Autowired
    private IProcessUserService processUserService;
    @Autowired
    private IOperationMediaService operationMediaService;

    @Transactional
    @Override
    public Integer saveOperation(Integer workFlowId, Integer eventId,
                                Integer operationId, String filterCode,
                                Integer userId, String suggest,
                                String userName, List<Integer> nextNodeUserIds,
                                 LocalDateTime operationDate){
        QueryWrapper<Process> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(com.winsse.sc.wf.entity.Process::getEventId, eventId)
                        .eq(Process::getWorkFlowId, workFlowId)
                        .eq(Process::getCurrentNodeFlag, 1);
        Process process = processService.getOne(wrapper);
        // 第一次开流程
        if (process == null){
            return startFlow(workFlowId, eventId, filterCode, operationId,
                    userName, operationDate, suggest);
        }else {
            // 流程流转
            return processFlow(process, filterCode,
                    userId, operationId, suggest, userName, nextNodeUserIds, operationDate);
        }
    }

    /**
     * 开启流程
     * @author xiaheng
     * @date 2021/10/15 16:55
     * @param workFlowId
     * @param eventId
     * @param filterCode
     * @param operationId
     */
    @Transactional
    public Integer startFlow(Integer workFlowId, Integer eventId, String filterCode,
                             Integer operationId, String userName,
                             LocalDateTime operationDate, String suggest){
        try{
            QueryWrapper<Node> nodeQueryWrapper = new QueryWrapper<>();
            nodeQueryWrapper.lambda().eq(Node::getWorkFlowId, workFlowId).orderByAsc(Node::getNodeId);
            List<Node> nodes = nodeService.list(nodeQueryWrapper);
            // 保存开始节点
            Process process = new Process();
            process.setEventId(eventId);
            process.setCurrentNodeFlag(0);
            process.setMoveFilterCode(filterCode);
            process.setMoveNum(1);
            process.setNodeId(nodes.get(0).getNodeId());
            process.setUpdateTime(LocalDateTime.now());
            process.setWorkFlowId(workFlowId);
            processService.save(process);

            // 保存操作记录
            OperationLog operationLog = new OperationLog();
            operationLog.setProcessId(process.getProcessId());
            operationLog.setOperationId(operationId);
            operationLog.setOperationUserName(userName);
            operationLog.setUpdateTime(operationDate);
            operationLog.setOperationSuggest(suggest);
            operationLogService.save(operationLog);

            // 保存第二次节点流转记录
            QueryWrapper<NodeFlow> nodeWrapper = new QueryWrapper<>();
            nodeWrapper.lambda().eq(NodeFlow::getFromNodeId, process.getNodeId())
                    .eq(NodeFlow::getOperationId, operationId);
            NodeFlow nodeFlow = nodeFlowService.getOne(nodeWrapper);
            process = new Process();
            process.setEventId(eventId);
            process.setCurrentNodeFlag(1);
            process.setMoveFilterCode(filterCode);
            process.setMoveNum(2);
            process.setNodeId(nodeFlow.getNextNodeId());
            process.setUpdateTime(LocalDateTime.now());
            process.setWorkFlowId(workFlowId);
            processService.save(process);

            // 保存下一个节点可以操作得用户ID
            List<OperationUser> operationUsers = operationUserService.getOperationUserId(nodeFlow.getNextNodeId(), filterCode, null);
            List<ProcessUser> temp = Lists.newArrayList();
            for (OperationUser operationUser : operationUsers){
                ProcessUser processUser = new ProcessUser();
                processUser.setProcessId(process.getProcessId());
                processUser.setUserId(operationUser.getUserId());
                temp.add(processUser);
            }
            processUserService.saveBatch(temp);

            return operationLog.getOperationLogId();
        }catch (Exception e){
            logger.error("work flow error workFlowId:{}, eventId:{}, filterCode:{}, operationId:{}, userName:{}",
                    workFlowId, eventId, filterCode, operationId, userName);
            throw new RuntimeException("开启流程失败");
        }
    }

    /**
     * 流程流转
     * @author xiaheng
     * @date 2021/10/15 16:55
     * @param process
     * @param filterCode
     * @param userId
     * @param operationId
     * @param suggest
     */
    @Transactional
    public Integer processFlow(Process process, String filterCode,
                               Integer userId, Integer operationId,
                               String suggest, String userName,
                               List<Integer> nextNodeUserIds, LocalDateTime operationDate){
        try {
            OperationLog operationLog = new OperationLog();
            operationLog.setUpdateTime(operationDate);
            operationLog.setProcessId(process.getProcessId());
            operationLog.setOperationUserId(userId);
            operationLog.setOperationId(operationId);
            operationLog.setOperationSuggest(suggest);
            operationLog.setOperationUserName(userName);
            operationLogService.save(operationLog);

            QueryWrapper<NodeFlow> nodeWrapper = new QueryWrapper<>();
            nodeWrapper.lambda().eq(NodeFlow::getFromNodeId, process.getNodeId())
                    .eq(NodeFlow::getOperationId, operationId);
            NodeFlow nodeFlow = nodeFlowService.getOne(nodeWrapper);
            // 流转得下一个节点不是当前节点
            if (!nodeFlow.getNextNodeId().equals(process.getNodeId())){
                unCurrentNode(process, filterCode, nodeFlow, nextNodeUserIds);
            }else {
                currentNode(process, filterCode, nextNodeUserIds);
            }
            return operationLog.getOperationLogId();
        }catch (Exception e){
            logger.error("work flow error processId:{}, filterCode:{}, userId:{}, operationId:{}, suggest:{}, userName:{}",
                    process.getProcessId(), filterCode,
                    userId, operationId,
                    suggest, userName);
            throw new RuntimeException("执行工作流失败");
        }
    }

    /**
     * 当前节点
     * @author xiaheng
     * @date 2021/11/2 15:19
     * @param process
     * @param filterCode
     * @param nextNodeUserIds
     */
    private void currentNode(Process process, String filterCode, List<Integer> nextNodeUserIds){
        // 流转得下一个节点是当前节点
        process.setMoveFilterCode(filterCode);
        process.setUpdateTime(LocalDateTime.now());
        processService.updateById(process);

        // 保存下一个节点可以操作得用户ID
        QueryWrapper<ProcessUser> nodeDeleteWrapper = new QueryWrapper<>();
        nodeDeleteWrapper.lambda().eq(ProcessUser::getProcessId, process.getProcessId());
        processUserService.remove(nodeDeleteWrapper);
        if (nextNodeUserIds == null || nextNodeUserIds.size() < 1){
            List<OperationUser> operationUsers = operationUserService.getOperationUserId(process.getNodeId(), filterCode, null);
            List<ProcessUser> temp = Lists.newArrayList();
            for (OperationUser operationUser : operationUsers){
                ProcessUser processUser = new ProcessUser();
                processUser.setProcessId(process.getProcessId());
                processUser.setUserId(operationUser.getUserId());
                temp.add(processUser);
            }
            processUserService.saveBatch(temp);
        }else {
            for (Integer nextNodeUserId : nextNodeUserIds){
                ProcessUser processUser = new ProcessUser();
                processUser.setProcessId(process.getProcessId());
                processUser.setUserId(nextNodeUserId);
                processUserService.save(processUser);
            }
        }
    }

    /**
     * 流转得下一个节点不是当前节点
     * @author xiaheng
     * @date 2021/11/2 15:18
     * @param process
     * @param filterCode
     * @param nodeFlow
     * @param nextNodeUserIds
     */
    private void unCurrentNode(Process process, String filterCode,
                               NodeFlow nodeFlow, List<Integer> nextNodeUserIds){
        process.setCurrentNodeFlag(0);
        processService.updateById(process);
        process.setCurrentNodeFlag(1);
        process.setMoveFilterCode(filterCode);
        process.setMoveNum(process.getMoveNum() + 1);
        process.setNodeId(nodeFlow.getNextNodeId());
        process.setUpdateTime(LocalDateTime.now());
        processService.save(process);

        if (nextNodeUserIds == null || nextNodeUserIds.size() < 1){
            // 保存下一个节点可以操作得用户ID
            List<OperationUser> operationUsers = operationUserService.getOperationUserId(nodeFlow.getNextNodeId(), filterCode, null);
            List<ProcessUser> temp = Lists.newArrayList();
            for (OperationUser operationUser : operationUsers){
                ProcessUser processUser = new ProcessUser();
                processUser.setProcessId(process.getProcessId());
                processUser.setUserId(operationUser.getUserId());
                temp.add(processUser);
            }
            processUserService.saveBatch(temp);

        }else {
            for (Integer nextNodeUserId : nextNodeUserIds){
                ProcessUser processUser = new ProcessUser();
                processUser.setProcessId(process.getProcessId());
                processUser.setUserId(nextNodeUserId);
                processUserService.save(processUser);
            }
        }
    }

    /**
     * 获得用户角色当前节点得过程流数据
     * @author xiaheng
     * @date 2021/10/15 15:21
     * @param nodeId
     * @param userId
     * @return java.util.List<com.winsse.sc.wf.entity.Process>
     */
    @Override
    public IPage<Process> getProcessByUserId(Page<?> page, Integer nodeId, Integer userId){
        return processService.getProcess(page, userId, nodeId);
    }


    /**
     * 获得操作日志
     * @author xiaheng
     * @date 2021/10/18 11:41
     * @param eventId
     * @return java.util.List<com.winsse.sc.wf.entity.vo.ProcessVo>
     */
    @Override
    public List<ProcessVo> getLog(Integer eventId, Integer workFlowId){
        List<ProcessVo> processVoList = processService.getNodeAndProcess(eventId, workFlowId);
        for (ProcessVo process : processVoList){
            process.setOperationLogVoList(baseMapper.getLog(process.getProcessId()));
            for (OperationLogVo operationLogVo : process.getOperationLogVoList()){
                QueryWrapper<OperationMedia> operationMediaQueryWrapper = new QueryWrapper<>();
                operationMediaQueryWrapper.lambda().eq(OperationMedia::getOperationId, operationLogVo.getOperationLogId());
                operationLogVo.setOperationMediaList(operationMediaService.list(operationMediaQueryWrapper));
            }
        }
        return processVoList;
    }

}
