package com.xs.storage;

import com.xs.storage.enums.OssTypeEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Oss配置
 *
 * @author : xs
 **/
@Data
@Slf4j
public class OssStorageConfig {


    /**
     * oss 类型
     * 参考 OssTypeEnum.java
     */
    private OssTypeEnum ossType;


    /**
     * 阿里云：endpoint
     */
    private String endpoint;


    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;


    /**
     * 桶名
     */
    private String bucketName;


    /**
     * 预览域名
     */
    private String domain;


    /**
     * 本地存储文件存放地址
     */
    private String uploadFolder;


    /**
     * 本地存储文件访问路径
     */
    private String accessPathPattern = "/u/**";


}
