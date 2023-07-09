package com.xs.common.envconfig.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xs.common.envconfig.entity.SysEnvConfigEntity;
import com.xs.common.envconfig.entity.SystemInfoConfig;

/**
 * 系统环境配置Service接口
 *
 * @author xs
 */
public interface SysEnvConfigService extends IService<SysEnvConfigEntity> {

    /**
     * 获取配置
     *
     * @param key 配置key
     * @return {@link SysEnvConfigEntity}
     */
    SysEnvConfigEntity getByKey(String key);


    /**
     * 获取系统配置
     * @return {@link SystemInfoConfig}
     */
    SystemInfoConfig getSystemEnvConfig();

    /**
     * 获取配置值
     *
     * @param key 配置key
     * @return 配置值
     */
    String getValueByKey(String key);

    /**
     * 保存配置
     *
     * @param config 配置
     */
    void saveConfig(SysEnvConfigEntity config);
}
