package com.xs.form.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.xs.common.entity.IDictEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : xs
 * @description : 表单来源
 * @create : 2020-12-04 13:35
 **/

@Getter
@AllArgsConstructor
public enum FormSourceTypeEnum implements IDictEnum {

    BLANK(1, "空白创建"),
    TEMPLATE(2, "模板");


    @EnumValue
    @JsonValue
    private Integer value;

    private String desc;


}
