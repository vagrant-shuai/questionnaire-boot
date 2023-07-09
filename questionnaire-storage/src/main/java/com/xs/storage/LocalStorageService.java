package com.xs.storage;


import cn.hutool.core.io.FileUtil;
import com.xs.storage.OssStorageConfig;
import com.xs.storage.OssStorageService;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.InputStream;

/**
 * 本地文件存储
 *
 * @author : xs
 */
@Slf4j
public class LocalStorageService extends OssStorageService {
    public LocalStorageService(OssStorageConfig config) {
        this.config = config;
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        log.info("上传文件到本地服务器{}",config.getUploadFolder());
        File file = FileUtil.file(config.getUploadFolder() + File.separator + path);
        //目录是否存在
        if (!FileUtil.exist(file.getParent())) {
            FileUtil.mkdir(file.getParent());
        }
        FileUtil.writeFromStream(inputStream, file);
        return config.getDomain() + "/" + path;
    }

    @Override
    public String upload(byte[] data, String path) {
        File file = FileUtil.file(config.getUploadFolder() + File.separator + path);
        //目录是否存在
        if (!FileUtil.exist(file.getParent())) {
            FileUtil.mkdir(file.getParent());
        }
        FileUtil.writeBytes(data, file);
        return config.getDomain() + "/" + path;
    }

    @Override
    public void delete(String path) {
        FileUtil.del(config.getUploadFolder() + File.separator + path);
    }
}
