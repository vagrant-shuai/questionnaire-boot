package com.xs.form.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : xs
 * @description : 排序表单项
 **/
@Data
public class SortFormItemRequest {
    /**
     * 表单Id
     */
    @NotNull(message = "key请求异常")
    private String formKey;


    private Long beforePosition;

    private Long afterPosition;
    @NotBlank(message = "formItemId请求异常")
    private String formItemId;

}
