package com.xs.form.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xs.common.util.Result;
import com.xs.form.entity.UserFormSettingEntity;
import com.xs.form.entity.strct.FormSettingSchemaStruct;

import java.util.Map;

/**
 * 表单设置
 *
 * @author xs
 */
public interface UserFormSettingService extends IService<UserFormSettingEntity> {
    /**
     * 保存表单设置
     */
    Boolean saveFormSetting(Map<String, Object> params);


    /**
     * 表单设置
     *
     * @param formKey 表单key
     * @return 设置项
     */
    UserFormSettingEntity getFormSettingByKey(String formKey);

    /**
     * 设置具体定义设置项
     *
     * @param formKey 表单key
     * @return 设置项
     */
    FormSettingSchemaStruct getFormSettingSchema(String formKey);

    /**
     * 获取当前项目设置的状态
     * 是否可以填写等
     *
     * @param formKey   表单key
     * @param requestIp 请求ip
     * @param wxOpenId  微信openid
     * @param type      类型 1公开填写 2.指定填写
     * @return 是否可以填写
     */
    Result<Boolean> getUserFormWriteSettingStatus(String formKey, String requestIp, String wxOpenId, Integer type);


    /**
     * 删除表单所有设置
     *
     * @param key 表单key
     * @return 是否删除成功
     */
    Boolean deleteAllSetting(String key);
}