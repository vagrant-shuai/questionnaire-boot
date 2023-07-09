package com.xs.common.envconfig.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xs.common.entity.BaseEntity;
import com.xs.common.mybatis.handler.JacksonTypeHandler;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * 系统环境配置对象 sys_env_config
 *
 * @author xs
 */
@Data
@TableName(value = "sys_env_config", autoResultMap = true)
public class SysEnvConfigEntity extends BaseEntity {


    /**
     * 配置key
     */
    @NotBlank
    private String envKey;

    /**
     * 参数键值
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> envValue;


}
