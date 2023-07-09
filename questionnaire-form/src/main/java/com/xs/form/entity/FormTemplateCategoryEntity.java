package com.xs.form.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xs.common.entity.BaseEntity;
import lombok.Data;


/**
 * 表单主题分类
 *
 * @author xs
 */

@Data
@TableName(value = "fm_form_template_category", autoResultMap = true)
public class FormTemplateCategoryEntity extends BaseEntity<FormTemplateCategoryEntity> {
    /**
     * 主题名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;

}