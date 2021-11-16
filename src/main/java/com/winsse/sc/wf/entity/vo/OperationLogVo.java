package com.winsse.sc.wf.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.winsse.sc.wf.entity.OperationMedia;

import javax.naming.ldap.PagedResultsControl;
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
public class OperationLogVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer operationLogId;

    private Integer processId;

    private Integer operationId;

    private String operationSuggest;

    private Integer operationUserId;

    private String operationUserName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    private Integer nodeId;

    private String nodeName;

    private String operationName;

    private String roleName;

    private List<OperationMedia> operationMediaList;

    public List<OperationMedia> getOperationMediaList() {
        return operationMediaList;
    }

    public void setOperationMediaList(List<OperationMedia> operationMediaList) {
        this.operationMediaList = operationMediaList;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getOperationUserName() {
        return operationUserName;
    }

    public void setOperationUserName(String operationUserName) {
        this.operationUserName = operationUserName;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
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
