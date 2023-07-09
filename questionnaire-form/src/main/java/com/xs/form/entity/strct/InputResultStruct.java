package com.xs.form.entity.strct;

import com.xs.common.util.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

/**
 * @author : xs
 * @description : 上传收集结果
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputResultStruct {

    /**
     * 不允许重复
     */
    private boolean notRepeat;


    public static InputResultStruct builder(Map<String, Object> params) {
        return JsonUtils.objToObj(params, InputResultStruct.class);
    }
}
