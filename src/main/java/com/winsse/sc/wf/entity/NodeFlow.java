package com.winsse.sc.wf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaheng
 * @since 2021-10-15
 */
@TableName("t_wf_node_flow")
public class NodeFlow implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value="flow_id",type= IdType.AUTO)
    private Integer flowId;
    @TableField("work_flow_id")
    private Integer workFlowId;
    @TableField("from_node_id")
    private Integer fromNodeId;
    @TableField("next_node_id")
    private Integer nextNodeId;
    @TableField("operation_id")
    private Integer operationId;
    @TableField("remarks")
    private String remarks;


    public Integer getFlowId() {
        return flowId;
    }

    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }

    public Integer getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(Integer workFlowId) {
        this.workFlowId = workFlowId;
    }

    public Integer getFromNodeId() {
        return fromNodeId;
    }

    public void setFromNodeId(Integer fromNodeId) {
        this.fromNodeId = fromNodeId;
    }

    public Integer getNextNodeId() {
        return nextNodeId;
    }

    public void setNextNodeId(Integer nextNodeId) {
        this.nextNodeId = nextNodeId;
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "NodeFlow{" +
        "flowId=" + flowId +
        ", workFlowId=" + workFlowId +
        ", fromNodeId=" + fromNodeId +
        ", nextNodeId=" + nextNodeId +
        ", operationId=" + operationId +
        ", remarks=" + remarks +
        "}";
    }
}
