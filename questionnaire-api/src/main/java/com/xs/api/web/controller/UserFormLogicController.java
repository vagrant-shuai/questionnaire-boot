package com.xs.api.web.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xs.common.util.Result;
import com.xs.form.entity.UserFormLogicEntity;
import com.xs.form.service.UserFormLogicService;
import com.xs.form.util.FormAuthUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * 表单逻辑
 *
 * @author : xs
 * @description : 表单逻辑
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserFormLogicController {

    private final UserFormLogicService formLogicService;


    /**
     * 保存表单逻辑
     *
     * @param userFormLogicEntity 表单逻辑
     * @return 表单逻辑
     */
    @PostMapping("/user/form/logic/save")
    public Result saveUserFormLogic(@RequestBody UserFormLogicEntity userFormLogicEntity) {
        FormAuthUtils.hasPermission(userFormLogicEntity.getFormKey());
        UserFormLogicEntity formLogic = formLogicService.getOne(Wrappers.<UserFormLogicEntity>lambdaQuery().eq(UserFormLogicEntity::getFormKey, userFormLogicEntity.getFormKey()));
        if (ObjectUtil.isNotNull(formLogic)) {
            userFormLogicEntity.setId(formLogic.getId());
            formLogicService.updateById(userFormLogicEntity);
        } else {
            formLogicService.save(userFormLogicEntity);
        }
        return Result.success(userFormLogicEntity);
    }


    /**
     * 查询表单逻辑
     *
     * @param formKey 表单key
     * @return 表单逻辑
     */
    @GetMapping("/user/form/logic")
    public Result queryFormLogic(@RequestParam @NotBlank String formKey) {
        FormAuthUtils.hasPermission(formKey);
        return Result.success(formLogicService.getOne(Wrappers.<UserFormLogicEntity>lambdaQuery().eq(UserFormLogicEntity::getFormKey, formKey)));
    }
}
