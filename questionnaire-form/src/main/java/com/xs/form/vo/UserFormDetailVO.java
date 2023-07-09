package com.xs.form.vo;

import com.xs.form.entity.UserFormEntity;
import com.xs.form.entity.UserFormItemEntity;
import com.xs.form.entity.UserFormLogicEntity;
import com.xs.form.entity.UserFormThemeEntity;
import com.xs.form.entity.enums.FormTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author smalljop
 */
@Data
@AllArgsConstructor
public class UserFormDetailVO {
    /**
     * 表单基础信息
     */
    private UserForm form;

    /**
     * 表单项
     */
    private List<UserFormItemEntity> formItems;

    /**
     * 主题
     */
    private UserFormThemeEntity userFormTheme;

    /**
     * 逻辑
     */
    private UserFormLogicEntity formLogic;


    @Data
    public static class UserForm {
        private String formKey;
        /**
         * 表单名称
         */
        private String name;
        /**
         * 表单描述
         */
        private String description;


        private FormTypeEnum type;

        public UserForm(UserFormEntity entity) {
            this.formKey = entity.getFormKey();
            this.name = entity.getName();
            this.description = entity.getDescription();
            this.type = entity.getType();
        }

    }
}
