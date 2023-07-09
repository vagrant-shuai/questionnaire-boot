package com.xs.storage;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.xs.common.envconfig.constant.ConfigConstants;
import com.xs.common.envconfig.service.SysEnvConfigService;
import com.xs.common.util.JsonUtils;
import com.xs.common.util.ServletUtils;
import com.xs.common.util.SpringContextUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.system.ApplicationHome;


/**
 * 文件上传Factory
 *
 * @author xs
 */
@Slf4j
public final class OssStorageFactory {

    private static OssStorageService storageService;
    @Getter
    private static OssStorageConfig config;

    static {
        build();
    }

    public static synchronized void build() {
        try {
            OssStorageConfig config = JsonUtils.jsonToObj(SpringContextUtils.getBean(SysEnvConfigService.class).getValueByKey(ConfigConstants.FILE_ENV_CONFIG), OssStorageConfig.class);
            if (ObjectUtil.isNull(config)) {
                return;
            }
            OssStorageFactory.config = config;

                // 本地存储默认配置
                if (StrUtil.isBlank(config.getUploadFolder())) {
                    ApplicationHome ah = new ApplicationHome(OssStorageFactory.class);
                    config.setUploadFolder(ah.getDir().getAbsolutePath() + "/upload");
                }
                if (StrUtil.isBlank(config.getDomain())) {
                    String domain = ServletUtils.getDomain(ServletUtils.getRequest());
                    config.setDomain(domain + "/u");
                }
                storageService = new LocalStorageService(config);

            log.info("更新存储配置完成:{}", JsonUtils.objToJson(config));
        } catch (Exception exception) {
            log.error("更新存储配置失败:{}", exception.getMessage());
        }
    }

    public static OssStorageService getStorageService() {
        if (null == storageService) {
            build();
        }
        return storageService;
    }

}
