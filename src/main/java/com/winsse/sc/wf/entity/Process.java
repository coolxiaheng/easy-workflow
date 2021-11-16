package com.winsse.sc.wf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("t_wf_process")
public class Process implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="process_id",type= IdType.AUTO)
    private Integer processId;
    @TableField("event_id")
    private Integer eventId;
    @TableField("node_id")
    private Integer nodeId;
    @TableField("current_node_flag")
    private Integer currentNodeFlag;
    @TableField("move_num")
    private Integer moveNum;
    @TableField("move_filter_code")
    private String moveFilterCode;
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private LocalDateTime updateTime;
    @TableField("work_flow_id")
    private Integer workFlowId;

    public Integer getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(Integer workFlowId) {
        this.workFlowId = workFlowId;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getCurrentNodeFlag() {
        return currentNodeFlag;
    }

    public void setCurrentNodeFlag(Integer currentNodeFlag) {
        this.currentNodeFlag = currentNodeFlag;
    }

    public Integer getMoveNum() {
        return moveNum;
    }

    public void setMoveNum(Integer moveNum) {
        this.moveNum = moveNum;
    }

    public String getMoveFilterCode() {
        return moveFilterCode;
    }

    public void setMoveFilterCode(String moveFilterCode) {
        this.moveFilterCode = moveFilterCode;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

}
