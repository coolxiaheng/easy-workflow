package com.winsse.sc.wf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winsse.sc.wf.entity.OperationLog;
import com.winsse.sc.wf.entity.vo.OperationLogVo;
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
public interface OperationLogMapper extends BaseMapper<OperationLog> {

    /**
     * 获得操作日志
     * @author xiaheng
     * @date 2021/10/18 13:51
     * @param processId 
     * @return java.util.List<com.winsse.sc.wf.entity.vo.ProcessVo>
     */
    List<OperationLogVo> getLog(@Param("processId")Integer processId);
}
