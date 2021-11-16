package com.winsse.sc.wf.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.winsse.sc.wf.entity.Process;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winsse.sc.wf.entity.vo.ProcessVo;
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
public interface ProcessMapper extends BaseMapper<Process> {

    /**
     * 获得用户角色当前节点得过程流数据
     * @author xiaheng
     * @date 2021/10/15 15:18
     * @param userId
     * @param nodeId
     * @return java.util.List<com.winsse.sc.wf.entity.Process>
     */
    IPage<Process> getProcess(Page<?> page, @Param("userId")Integer userId, @Param("nodeId")Integer nodeId);

    /**
     * 获得过程和节点信息
     * @author xiaheng
     * @date 2021/10/18 12:04
     * @param eventId
     * @return java.util.List<com.winsse.sc.wf.entity.vo.ProcessVo>
     */
    List<ProcessVo> getNodeAndProcess(@Param("eventId")Integer eventId, @Param("workFlowId")Integer workFlowId);
}
