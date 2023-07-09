package com.xs.form.data;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.stream.Collectors;

/**
 * 部门选择类型格式化数据处理
 *
 * @author xs
 */
public class DeptSelectFormatDataHandler implements BaseFormatDataHandler {

    @Override
    public Object formatData(String key, Object value, Object scheme) {
        return JSONUtil.parseArray(JSONUtil.toJsonStr(value)).toList(JSONObject.class)
                .stream().map(item -> item.getStr("name")).collect(Collectors.joining(","));
    }
}
