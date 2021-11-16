package com.winsse.sc.wf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaheng
 * @since 2021-10-15
 */
@TableName("t_wf_work_flow")
public class WorkFlow implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value="work_flow_id",type= IdType.AUTO)
      private Integer workFlowId;
    @TableField("work_flow_name")
    private String workFlowName;
    @TableField("create_time")
    private LocalDateTime createTime;


    public Integer getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(Integer workFlowId) {
        this.workFlowId = workFlowId;
    }

    public String getWorkFlowName() {
        return workFlowName;
    }

    public void setWorkFlowName(String workFlowName) {
        this.workFlowName = workFlowName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "WorkFlow{" +
        "workFlowId=" + workFlowId +
        ", workFlowName=" + workFlowName +
        ", createTime=" + createTime +
        "}";
    }
}
