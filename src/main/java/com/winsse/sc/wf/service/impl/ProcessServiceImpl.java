package com.winsse.sc.wf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winsse.sc.wf.entity.Constants;
import com.winsse.sc.wf.entity.Process;
import com.winsse.sc.wf.entity.ProcessUser;
import com.winsse.sc.wf.entity.vo.ProcessVo;
import com.winsse.sc.wf.feign.IMFeignService;
import com.winsse.sc.wf.mapper.ProcessMapper;
import com.winsse.sc.wf.service.IProcessService;
import com.winsse.sc.wf.service.IProcessUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProcessServiceImpl extends ServiceImpl<ProcessMapper, Process> implements IProcessService {

    @Autowired
    private IProcessUserService processUserService;
    @Autowired
    private IMFeignService imFeignService;

    private static final String NOTICE_MESSAGE= "案件已到达，请及时处理";

    /**
     * 获得用户角色当前节点得过程流数据
     * @author xiaheng
     * @date 2021/10/15 15:18
     * @param userId
     * @param nodeId
     * @return java.util.List<com.winsse.sc.wf.entity.Process>
     */
    @Override
    public IPage<Process> getProcess(Page<?> page, Integer userId, Integer nodeId){
        return baseMapper.getProcess(page, userId, nodeId);
    }


    /**
     * 获得过程和节点信息
     * @author xiaheng
     * @date 2021/10/18 12:04
     * @param eventId
     * @return java.util.List<com.winsse.sc.wf.entity.vo.ProcessVo>
     */
    @Override
    public List<ProcessVo> getNodeAndProcess(Integer eventId, Integer workFlowId){
        return baseMapper.getNodeAndProcess(eventId, workFlowId);
    }

    /**
     * 发送通知消息
     * @author xiaheng
     * @date 2021/11/2 15:53
     * @param eventId
     * @param eventName
     */
    @Override
    public void sendNoticeMessage(Integer eventId, String eventName){
        QueryWrapper<Process> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Process::getEventId, eventId)
                .eq(Process::getCurrentNodeFlag, 1);
        Process process = baseMapper.selectOne(queryWrapper);

        QueryWrapper<ProcessUser> processUserQueryWrapper = new QueryWrapper<>();
        processUserQueryWrapper.lambda().eq(ProcessUser::getProcessId, process.getProcessId());
        List<ProcessUser> processUserList = processUserService.list(processUserQueryWrapper);
        // 发送通知信息
            new Thread(){
                @Override
                public void run() {
                    for (ProcessUser processUser : processUserList){
                        imFeignService.sendMessage(processUser.getUserId(), Constants.PC,
                                eventName.concat(NOTICE_MESSAGE),
                                99, 99);
                    }
                }
            }.start();
    }
}
