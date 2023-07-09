package com.xs.storage.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;

/**
 * Oss类型枚举
 *
 * @author xs
 **/
@Getter
@AllArgsConstructor
public enum OssTypeEnum {

    /**
     * 本地存储
     */
    LOCAL(3);

    @EnumValue
    @JsonValue
    private final int value;

    @JsonCreator
    public static OssTypeEnum fromString(String code) {
        OssTypeEnum status = Arrays.stream(OssTypeEnum.values()).filter(item -> item.toString().equals(code)).findFirst().get();
        return status;
    }
}
