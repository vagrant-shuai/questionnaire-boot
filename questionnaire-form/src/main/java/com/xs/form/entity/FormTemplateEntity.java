package com.xs.form.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xs.common.entity.BaseEntity;
import com.xs.common.mybatis.handler.JacksonTypeHandler;
import com.xs.common.validator.group.AddGroup;
import com.xs.common.validator.group.UpdateGroup;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 表单模板(FormTemplate)表实体类
 *
 * @author xs
 */

@Data
@TableName(value = "fm_form_template", autoResultMap = true)
public class FormTemplateEntity extends BaseEntity<FormTemplateEntity> {
    /**
     * 模板唯一标识
     */
    @NotBlank(message = "错误请求", groups = {UpdateGroup.class})
    private String formKey;
    /**i1b
     * 表单名称
     */
    @NotBlank(message = "表单名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    /**
     * 封面图
     */
    private String coverImg;

    /**
     * 表单描述
     */
    private String description;
    /**
     * 表单类型
     */
    @NotNull(message = "表单类型不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long categoryId;
    /**
     * 状态
     */
    private Long status;


    /**
     * 模板内容定义
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Definition scheme;

    @Data
    public static class Definition {

        private Integer formType;
        private List<UserFormItemEntity> formItems;
        private UserFormThemeEntity userFormTheme;
        private UserFormLogicEntity userFormLogic;
    }


}
