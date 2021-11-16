package com.winsse.sc.wf.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sc.commomlib.ge.utils.support.ResultBean;
import com.winsse.sc.wf.service.IOperationLogService;
import com.winsse.sc.wf.service.IOperationUserService;
import com.winsse.sc.wf.service.IProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaheng
 * @since 2021-10-15
 */
@RestController
public class OperationLogController {

    @Autowired
    private IOperationLogService operationLogService;
    @Autowired
    private IOperationUserService operationUserService;
    @Autowired
    private IProcessService processService;

    /**
     * 运行工作流
     * @author xiaheng
     * @date 2021/10/19 14:06
     * @param workFlowId
     * @param eventId
     * @param operationId
     * @param filterCode
     * @param userId
     * @param suggest
     * @param userName
     * @return com.sc.commomlib.ge.utils.support.ResultBean<?>
     */
    @PostMapping(value = "work/flow")
    public ResultBean<?> saveFlowOperation(Integer workFlowId, Integer eventId,
                                           Integer operationId, String filterCode,
                                           Integer userId, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime operationDate,
                                           String suggest, String userName, List<Integer> nextNodeUserIds){
        return ResultBean.ok().data(operationLogService.saveOperation(workFlowId, eventId, operationId,
                filterCode, userId, suggest, userName, nextNodeUserIds, operationDate));
    }

    /**
     * 获得用户角色当前节点得过程流数据
     * @author xiaheng
     * @date 2021/10/15 15:21
     * @param nodeId
     * @param userId
     * @return java.util.List<com.winsse.sc.wf.entity.Process>
     */
    @GetMapping(value = "work/flow/process")
    public ResultBean<?> getProcess(Page<?> page, Integer nodeId, Integer userId){
        return ResultBean.success().data(operationLogService.getProcessByUserId(page, nodeId, userId));
    }

    /**
     * 获得日志
     * @author xiaheng
     * @date 2021/10/18 11:44
     * @param eventId
     * @return com.sc.commomlib.ge.utils.support.ResultBean<?>
     */
    @GetMapping(value = "work/flow/log")
    public ResultBean<?> getLog(Integer eventId, Integer workFlowId){
        return ResultBean.success().data(operationLogService.getLog(eventId, workFlowId));
    }

    /**
     * 获得可操作得用户ID
     * @author xiaheng
     * @date 2021/10/19 16:09
     * @param nodeId
     * @param filterCode
     * @param operationId
     * @return com.sc.commomlib.ge.utils.support.ResultBean<?>
     */
    @GetMapping(value = "/operation/user")
    public ResultBean<?> getOperationUsers(Integer nodeId, String filterCode, Integer operationId){
        return ResultBean.success().data(operationUserService.getOperationUserId(nodeId, filterCode, operationId));
    }

    /**
     * 获得可操作得用户ID
     * @author xiaheng
     * @date 2021/10/19 16:09
     * @param nodeId
     * @return com.sc.commomlib.ge.utils.support.ResultBean<?>
     */
    @GetMapping(value = "/user/permisstion")
    public ResultBean<?> getPermisstion(Integer nodeId, Integer userId){
        return ResultBean.success().data(operationUserService.getUserOperationPermisstion(nodeId, userId));
    }

    /**
     * 获得当前案件所在的节点
     * @author xiaheng
     * @date 2021/10/21 10:10
     * @param eventId
     * @return com.sc.commomlib.ge.utils.support.ResultBean<?>
     */
    @GetMapping(value = "/current/node")
    public ResultBean<?> getCurrentNodeId(Integer eventId, Integer workFlowId){
        QueryWrapper<com.winsse.sc.wf.entity.Process> processQueryWrapper = new QueryWrapper<>();
        processQueryWrapper.lambda().eq(com.winsse.sc.wf.entity.Process::getEventId, eventId)
                .eq(com.winsse.sc.wf.entity.Process::getCurrentNodeFlag, 1)
                .eq(com.winsse.sc.wf.entity.Process::getWorkFlowId, workFlowId);
        com.winsse.sc.wf.entity.Process process = processService.getOne(processQueryWrapper);
        return ResultBean.success().data(process.getNodeId());
    }

    /**
     * 发送通知消息
     * @author xiaheng
     * @date 2021/11/2 16:00
     * @param eventId
     * @param eventName
     * @return com.sc.commomlib.ge.utils.support.ResultBean<?>
     */
    @PostMapping(value = "/notice/message")
    public ResultBean<?> sendNoticeMessage(Integer eventId, String eventName){
        processService.sendNoticeMessage(eventId, eventName);
        return ResultBean.success();
    }

}

