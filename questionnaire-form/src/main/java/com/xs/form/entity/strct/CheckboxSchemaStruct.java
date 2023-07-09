package com.xs.form.entity.strct;

import com.xs.common.util.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author : xs
 * @description : 选项结构
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CheckboxSchemaStruct {

    private Config config;



    @Data
    public static class Config extends OptionQuotaListStruct{
        private boolean showVoteResult;
    }





    public static CheckboxSchemaStruct builder(Map<String, Object> params) {
        return JsonUtils.objToObj(params, CheckboxSchemaStruct.class);
    }
}
