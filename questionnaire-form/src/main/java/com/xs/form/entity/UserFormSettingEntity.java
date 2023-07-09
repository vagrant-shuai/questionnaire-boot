package com.xs.form.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xs.common.entity.BaseEntity;
import com.xs.common.mybatis.handler.JacksonTypeHandler;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * 表单设置对象
 *
 * @author xs
 */
@Data
@FieldNameConstants
@TableName(value = "fm_user_form_setting", autoResultMap = true)
public class UserFormSettingEntity extends BaseEntity {


    @NotBlank
    private String formKey;


    /**
     * 设置具体内容
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> settings;


}
