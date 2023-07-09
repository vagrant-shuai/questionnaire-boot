package com.xs.form.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xs.form.entity.UserFormThemeEntity;

/**
 * 表单表单项(UserFormTheme)表服务接口
 *
 * @author xs
 */
public interface UserFormThemeService extends IService<UserFormThemeEntity> {

    /**
     * 获取表单主题详情
     *
     * @param key
     * @return
     */
    UserFormThemeEntity getByKey(String key);
}