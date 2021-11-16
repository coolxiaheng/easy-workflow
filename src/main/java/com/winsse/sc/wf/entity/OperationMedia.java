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
 * @since 2021-10-22
 */
@TableName("t_wf_operation_media")
public class OperationMedia implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "media_id", type = IdType.AUTO)
    private Integer mediaId;
    @TableField("media_type")
    private String mediaType;
    @TableField("media_path")
    private String mediaPath;
    @TableField("update_time")
    private LocalDateTime updateTime;
    @TableField("operation_id")
    private Integer operationId;


    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    @Override
    public String toString() {
        return "OperationMedia{" +
        "mediaId=" + mediaId +
        ", mediaType=" + mediaType +
        ", mediaPath=" + mediaPath +
        ", updateTime=" + updateTime +
        ", operationId=" + operationId +
        "}";
    }
}
