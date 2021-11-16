package com.winsse.sc.wf.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sc.commomlib.ge.utils.media.MediaUtils;
import com.sc.commomlib.ge.utils.support.ResultBean;
import com.winsse.sc.wf.entity.OperationMedia;
import com.winsse.sc.wf.service.IOperationMediaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaheng
 * @since 2021-10-22
 */
@RestController
@RequestMapping("/operationMedia")
public class OperationMediaController {

    @Autowired
    private IOperationMediaService operationMediaService;

    /**
     * 保存工作流操作的多媒体文件
     *
     * @param operationId
     * @return com.sc.commomlib.ge.utils.support.ResultBean<?>
     * @author xiaheng
     * @date 2021/10/20 9:10
     */
    @PostMapping(value = "media")
    public ResultBean<?> saveFlowMedia(String files, String pathRule, Integer operationId) {
        if (StringUtils.isNotEmpty(files)) {
            JSONArray fileArray = JSONArray.parseArray(files);
            JSONObject obj = fileArray.getJSONObject(0);
            String mediaName = obj.getString("fileName");
            String fileSuffix = obj.getString("fileSuffix");
            OperationMedia operationMedia = new OperationMedia();
            String mediaType = MediaUtils.getMediaTypeByFileSuffix(fileSuffix);
            operationMedia.setMediaType(mediaType);
            operationMedia.setUpdateTime(LocalDateTime.now());
            operationMedia.setMediaPath(pathRule + mediaName + fileSuffix);
            operationMedia.setOperationId(operationId);
            operationMediaService.save(operationMedia);
            return ResultBean.ok().data(operationMedia);
        }
        return ResultBean.failure();
    }
}

