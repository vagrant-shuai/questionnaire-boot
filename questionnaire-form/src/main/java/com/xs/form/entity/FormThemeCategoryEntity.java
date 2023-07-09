package com.xs.form.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xs.common.entity.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * 表单模板分类(FormTemplateType)表实体类
 *
 * @author xs
 */

@Data
@TableName(value = "fm_form_theme_category", autoResultMap = true)
public class FormThemeCategoryEntity extends BaseEntity<FormThemeCategoryEntity> {
    /**
     * 主题名称
     */
    @NotBlank
    private String name;
    /**
     * 排序
     */
    private Integer sort;

}