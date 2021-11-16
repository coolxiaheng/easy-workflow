package com.winsse.sc.wf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.winsse.sc.wf.entity.Process;
import com.baomidou.mybatisplus.extension.service.IService;
import com.winsse.sc.wf.entity.vo.ProcessVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaheng
 * @since 2021-10-15
 */
public interface IProcessService extends IService<Process> {
    /**
     * 获得用户角色当前节点得过程流数据
     * @author xiaheng
     * @date 2021/10/15 15:18
     * @param userId
     * @param nodeId
     * @return java.util.List<com.winsse.sc.wf.entity.Process>
     */
    IPage<Process> getProcess(Page<?> page, Integer userId, Integer nodeId);

    /**
     * 获得过程和节点信息
     * @author xiaheng
     * @date 2021/10/18 12:04
     * @param eventId
     * @return java.util.List<com.winsse.sc.wf.entity.vo.ProcessVo>
     */
    List<ProcessVo> getNodeAndProcess(Integer eventId, Integer workFlowId);

    /**
     * 发送通知消息
     * @author xiaheng
     * @date 2021/11/2 15:53
     * @param eventId
     * @param eventName
     */
    void sendNoticeMessage(Integer eventId, String eventName);
}
