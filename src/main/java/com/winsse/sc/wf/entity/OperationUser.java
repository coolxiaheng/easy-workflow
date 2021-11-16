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
@TableName("t_wf_operation_user")
public class OperationUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableField("user_id")
      private Integer userId;
    @TableField("operation_id")
    private Integer operationId;
    @TableField("move_filter_code")
    private String moveFilterCode;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMoveFilterCode() {
        return moveFilterCode;
    }

    public void setMoveFilterCode(String moveFilterCode) {
        this.moveFilterCode = moveFilterCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

}
