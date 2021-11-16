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
@TableName("t_wf_node")
public class Node implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="node_id",type= IdType.AUTO)
    private Integer nodeId;
    @TableField("work_flow_Id")
    private Integer workFlowId;
    @TableField("node_name")
    private String nodeName;
    @TableField("remarks")
    private String remarks;

    public Integer getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(Integer workFlowId) {
        this.workFlowId = workFlowId;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Node{" +
        "nodeId=" + nodeId +
        ", nodeName=" + nodeName +
        ", remarks=" + remarks +
        "}";
    }
}
