package com.xs.form.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xs.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户表单查看次数对象 fm_user_form_view_count
 *
 * @author xs
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("fm_user_form_view_count")
public class UserFormViewCountEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 表单唯一标识
     */
    private String formKey;
    /**
     *
     */
    private Long count;

}
