package com.xs.api.web.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import com.xs.api.util.HttpUtils;
import com.xs.common.util.CacheUtils;
import com.xs.common.util.JsonUtils;
import com.xs.common.util.Result;
import com.xs.form.entity.UserFormSettingEntity;
import com.xs.form.entity.strct.FormSettingSchemaStruct;
import com.xs.form.service.UserFormSettingService;
import com.xs.form.util.FormAuthUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 表单设置
 *
 * @author : xs
 * @description : 表单设置
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserFormSettingController {

    private final UserFormSettingService userFormSettingService;
    private final CacheUtils cacheUtils;


    /**
     * 保存表单设置
     */
    @PostMapping("/user/form/setting/save")
    public Result<Boolean> saveFormSetting(@RequestBody Map<String, Object> setting) {
        String formKey = setting.get("formKey").toString();
        FormAuthUtils.hasPermission(formKey);
        return Result.success(userFormSettingService.saveFormSetting(setting));
    }

    /**
     * 表单提交设置查询
     */
    @GetMapping("/user/form/setting/{key}")
    public Result<Map<String, Object>> queryFormSettingByKey(@PathVariable("key") String formKey) {
        UserFormSettingEntity setting = userFormSettingService.getFormSettingByKey(formKey);
        if (ObjectUtil.isNull(setting)) {
            return Result.success();
        }
        Map<String, Object> settings = setting.getSettings();
        settings.put(UserFormSettingEntity.Fields.formKey, formKey);
        return Result.success(settings);
    }


    /**
     * 当前填写设置的状态
     *
     * @param formKey  表单key
     * @param wxOpenId 微信openid
     * @param type     类型 1公开填写 2.指定填写
     */
    @GetMapping("/user/form/setting-status")
    @PermitAll
    public Result<Boolean> querySettingStatus(@RequestParam String formKey, @RequestParam(required = false) String wxOpenId, @RequestParam(required = false) Integer type, HttpServletRequest request) {
        return userFormSettingService.getUserFormWriteSettingStatus(formKey, HttpUtils.getIpAddr(request), wxOpenId, type);
    }


    /**
     * 公开接口
     * 表单填写时需要的设置
     */
    @GetMapping("/user/form/public/settings/{key}")
    @PermitAll
    public Result queryPublicFormSettingByKey(@PathVariable("key") String formKey) {
        FormSettingSchemaStruct formSettingSchema = userFormSettingService.getFormSettingSchema(formKey);
        return Result.success(formSettingSchema);
    }


}
