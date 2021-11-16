package com.winsse.sc.wf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

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
@TableName("t_wf_operation_log")
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value="operation_log_id",type= IdType.AUTO)
    private Integer operationLogId;
    @TableField("process_id")
    private Integer processId;
    @TableField("operation_id")
    private Integer operationId;
    @TableField("operation_suggest")
    private String operationSuggest;
    @TableField("operation_user_id")
    private Integer operationUserId;
    @TableField("operation_user_name")
    private String operationUserName;
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private LocalDateTime updateTime;

    public String getOperationUserName() {
        return operationUserName;
    }

    public void setOperationUserName(String operationUserName) {
        this.operationUserName = operationUserName;
    }

    public Integer getOperationLogId() {
        return operationLogId;
    }

    public void setOperationLogId(Integer operationLogId) {
        this.operationLogId = operationLogId;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public String getOperationSuggest() {
        return operationSuggest;
    }

    public void setOperationSuggest(String operationSuggest) {
        this.operationSuggest = operationSuggest;
    }

    public Integer getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(Integer operationUserId) {
        this.operationUserId = operationUserId;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "OperationLog{" +
        "operationLogId=" + operationLogId +
        ", moveId=" + processId +
        ", operationId=" + operationId +
        ", operationSuggest=" + operationSuggest +
        ", operationUserId=" + operationUserId +
        ", updateTime=" + updateTime +
        "}";
    }
}
