package com.xs.form.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xs.form.entity.UserFormThemeEntity;
import com.xs.form.mapper.UserFormThemeMapper;
import com.xs.form.service.UserFormThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 项目表单项(UserFormTheme)表服务实现类
 * @author xs
 */
@Service
@RequiredArgsConstructor
public class UserFormThemeServiceImpl extends ServiceImpl<UserFormThemeMapper, UserFormThemeEntity> implements UserFormThemeService {


    @Override
    public UserFormThemeEntity getByKey(String key) {
        UserFormThemeEntity userFormThemeEntity = this.getOne(Wrappers.<UserFormThemeEntity>lambdaQuery().eq(UserFormThemeEntity::getFormKey, key));
        return userFormThemeEntity;
    }
}