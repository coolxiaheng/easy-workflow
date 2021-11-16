package com.winsse.sc.wf.entity.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.winsse.sc.wf.entity.OperationLog;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaheng
 * @since 2021-10-15
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer processId;

    private Integer eventId;

    private Integer nodeId;

    private String nodeName;

    private Integer currentNodeFlag;

    private Integer moveNum;

    private String moveFilterCode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    private List<OperationLogVo> operationLogVoList;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public List<OperationLogVo> getOperationLogVoList() {
        return operationLogVoList;
    }

    public void setOperationLogVoList(List<OperationLogVo> operationLogVoList) {
        this.operationLogVoList = operationLogVoList;
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

    @Override
    public String toString() {
        return "Process{" +
        "processId=" + processId +
        ", eventId=" + eventId +
        ", nodeId=" + nodeId +
        ", currentNodeFlag=" + currentNodeFlag +
        ", moveNum=" + moveNum +
        ", moveFilterCode=" + moveFilterCode +
        ", updateTime=" + updateTime +
        "}";
    }
}
