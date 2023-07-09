package com.xs.api.web.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.IdUtil;
import com.xs.common.util.AsyncProcessUtils;
import com.xs.common.util.Result;
import com.xs.storage.OssStorageFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author : xs
 * @description : 通用
 **/
@RestController
@RequestMapping("/common/")
public class CommonController {


    /**
     * 获取异步处理进度
     */
    @GetMapping("/process")
    public Result getProcess(@RequestParam String key) {
        return Result.success(AsyncProcessUtils.getProcess(key));
    }


    /**
     * 上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result avatar(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String path = new StringBuffer(IdUtil.simpleUUID())
                    .append(CharUtil.DOT)
                    .append(FileUtil.extName(file.getOriginalFilename())).toString();
            String url = OssStorageFactory.getStorageService().upload(file.getInputStream(), path);
            return Result.success(url);
        }
        return Result.failed("上传文件异常，请联系管理员");
    }



}
