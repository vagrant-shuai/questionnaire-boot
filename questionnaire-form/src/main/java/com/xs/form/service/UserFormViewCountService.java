package com.xs.form.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xs.form.entity.UserFormViewCountEntity;

/**
 * 用户表单查看次数Service接口
 *
 * @author xs
 */
public interface UserFormViewCountService extends IService<UserFormViewCountEntity> {

    /**
     * 查看次数自增
     *
     * @param formKey 表单key
     */
    void increment(String formKey);

    /**
     * 查看次数
     *
     * @param formKey 表单key
     */
    Long count(String formKey);
}
