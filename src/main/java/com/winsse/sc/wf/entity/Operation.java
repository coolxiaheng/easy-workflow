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
@TableName("t_wf_operation")
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value="operation_id",type= IdType.AUTO)
    private Integer operationId;
    @TableField("operation_name")
    private String operationName;
    @TableField("node_id")
    private Integer nodeId;


    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
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

    @Override
    public String toString() {
        return "Operation{" +
        "operationId=" + operationId +
        ", operationName=" + operationName +
        ", nodeId=" + nodeId +
        "}";
    }
}
