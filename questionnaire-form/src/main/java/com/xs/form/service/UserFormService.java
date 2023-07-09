package com.xs.form.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xs.form.entity.UserFormEntity;

/**
 * 表单表(Form)表服务接口
 *
 * @author xs
 */
public interface UserFormService extends IService<UserFormEntity> {


    /**
     * 根据key获取
     *
     * @param key key
     * @return UserFormEntity
     */
    UserFormEntity getByKey(final String key);


}