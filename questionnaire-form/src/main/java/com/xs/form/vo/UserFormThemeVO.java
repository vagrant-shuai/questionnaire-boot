package com.xs.form.vo;

import com.xs.form.entity.UserFormThemeEntity;
import lombok.Data;

/**
 * @author : xs
 * @description : 表单主题
 **/
@Data
public class UserFormThemeVO extends UserFormThemeEntity {

    /**
     * 头部图片
     */
    private String headImgUrl;


    /**
     * 按钮颜色
     */
    private String btnsColor;


}
