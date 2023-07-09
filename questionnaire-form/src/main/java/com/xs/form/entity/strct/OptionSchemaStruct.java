package com.xs.form.entity.strct;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : xs
 * @description : 选项结构
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionSchemaStruct {

    private Config config;

    @Data
    public static class Config {
        // 0 静态数据  1 动态数据 2 字典数据
        private String optionsType;
        private List<Option> options;
        // 字典类型
        private String dictOptionType;

        public List<Option> getOptions() {
            if (StrUtil.isBlank(optionsType) || optionsType.equals("0")) {
                return options;
            }
            return CollUtil.newArrayList();
        }
    }


    @Data
    public static class Option {
        private String label;
        private String value;
    }
}
