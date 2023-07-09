package com.xs.common.envconfig.entity.event;

import com.xs.common.envconfig.entity.SysEnvConfigEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 系统配置更新之后 更新对应的类
 *
 * @author xs
 */
public class EnvConfigRefreshEvent extends ApplicationEvent {

    @Getter
    private final SysEnvConfigEntity config;


    public EnvConfigRefreshEvent(Object source, SysEnvConfigEntity config) {
        super(source);
        this.config = config;
    }
}
