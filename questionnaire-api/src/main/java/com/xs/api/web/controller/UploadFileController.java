package com.xs.api.web.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.xs.common.util.Result;
import com.xs.storage.OssStorageFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.security.PermitAll;
import java.io.IOException;

/**
 * 表单文件上传
 *
 * @author : xs
 * @description : 上传文件
 **/
@RestController
@RequiredArgsConstructor
public class UploadFileController {


    /**
     * 上传用户文件
     * <p>
     * 用户Id MD5加密 同一个用户的文件放在一个目录下
     *
     * @param file
     * @param userId
     * @return
     * @throws IOException
     */
    @PostMapping("/user/file/upload")
    public Result<String> uploadUserFile(@RequestParam("file") MultipartFile file, @RequestAttribute Long userId) throws IOException {
        String path = SecureUtil.md5(String.valueOf(userId)) +
                CharUtil.SLASH +
                IdUtil.simpleUUID() +
                CharUtil.DOT +
                FileUtil.extName(file.getOriginalFilename());
        String url = OssStorageFactory.getStorageService().upload(file.getInputStream(), path);
        return Result.success(url);
    }


    /**
     * 表单文件上传
     *
     * @param file    文件
     * @param formKey 表单key
     * @return 文件地址
     * @throws IOException IO异常
     */
    @PostMapping("/form/file/upload/{formKey}")
    @PermitAll
    public Result<String> uploadFormFile(@RequestParam("file") MultipartFile file, @PathVariable("formKey") String formKey) throws IOException {
        String path = SecureUtil.md5(formKey) +
                CharUtil.SLASH +
                IdUtil.simpleUUID() +
                CharUtil.DOT +
                FileUtil.extName(file.getOriginalFilename());
        String url = OssStorageFactory.getStorageService().upload(file.getInputStream(), path);
        return Result.success(url);
    }

}
