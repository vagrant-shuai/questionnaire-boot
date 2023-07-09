package com.xs.form.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author : xs
 * @description : 查询表单问题
 **/
@Data
public class QueryFormItemRequest {

    /**
     * 表单key
     */
    @NotBlank
    private String key;
    /**
     * 是显示类型
     */
    private Boolean displayType;

}
