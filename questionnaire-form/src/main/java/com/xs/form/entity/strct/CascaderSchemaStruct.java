package com.xs.form.entity.strct;

import cn.hutool.core.collection.CollUtil;
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
public class CascaderSchemaStruct {


    private Config config;

    @Data
    public static class Config {
        // 0 静态数据  1 动态数据
        private String optionsType;
        private List<Option> options;

        public List<Option> getOptions() {
            if (optionsType.equals("0")) {
                return options;
            }
            return CollUtil.newArrayList();
        }
    }


    @Data
    public static class Option {
        private String label;
        private String value;
        private List<Option> children;
    }
}
