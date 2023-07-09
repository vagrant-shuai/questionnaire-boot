package com.xs.common.envconfig.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xs.common.envconfig.constant.ConfigConstants;
import com.xs.common.envconfig.entity.SysEnvConfigEntity;
import com.xs.common.envconfig.entity.SystemInfoConfig;
import com.xs.common.envconfig.entity.event.EnvConfigRefreshEvent;
import com.xs.common.envconfig.mapper.SysEnvConfigMapper;
import com.xs.common.envconfig.service.SysEnvConfigService;
import com.xs.common.util.JsonUtils;
import com.xs.common.util.SpringContextUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 系统环境配置Service业务层处理
 *
 * @author xs
 */
@Service
@RequiredArgsConstructor
public class SysEnvConfigServiceImpl extends ServiceImpl<SysEnvConfigMapper, SysEnvConfigEntity> implements SysEnvConfigService {

    /**
     * 本地缓存
     */
    private final Map<String, String> cacheMap = new ConcurrentHashMap<>();



    @PostConstruct
    public void initCache() {
        List<SysEnvConfigEntity> list = this.list();
        list.forEach(config -> {
            cacheMap.put(config.getEnvKey(), JsonUtils.objToJson(config.getEnvValue()));
        });

    }

    @Override
    public SysEnvConfigEntity getByKey(String key) {
        return baseMapper.selectOne(Wrappers.<SysEnvConfigEntity>lambdaQuery().eq(SysEnvConfigEntity::getEnvKey, key));
    }

    @Override
    public SystemInfoConfig getSystemEnvConfig() {
        return JsonUtils.jsonToObj(getValueByKey(ConfigConstants.SYSTEM_INFO_CONFIG), SystemInfoConfig.class);
    }


    @Override
    public String getValueByKey(String key) {
        String cacheValue = cacheMap.get(key);
        if (StringUtils.isNotEmpty(cacheValue)) {
            return cacheValue;
        }
        SysEnvConfigEntity config = getByKey(key);
        if (ObjectUtil.isNotNull(config)) {
            return JsonUtils.objToJson(config.getEnvValue());
        }
        return StringUtils.EMPTY;
    }


    @Override
    public void saveConfig(SysEnvConfigEntity config) {
        SysEnvConfigEntity envConfig = getByKey(config.getEnvKey());
        if (ObjectUtil.isNull(envConfig)) {
            envConfig = new SysEnvConfigEntity();
        }
        envConfig.setEnvKey(config.getEnvKey());
        envConfig.setEnvValue(config.getEnvValue());
        this.saveOrUpdate(envConfig);
        cacheMap.put(config.getEnvKey(), JsonUtils.objToJson(config.getEnvValue()));
        SpringContextUtils.publishEvent(new EnvConfigRefreshEvent(this, config));
    }
}
