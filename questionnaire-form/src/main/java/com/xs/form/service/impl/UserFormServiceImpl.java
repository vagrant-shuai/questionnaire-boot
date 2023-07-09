package com.xs.form.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xs.form.entity.UserFormEntity;
import com.xs.form.mapper.UserFormMapper;
import com.xs.form.service.UserFormService;
import org.springframework.stereotype.Service;

/**
 * 表单主表(Form)表服务实现类
 *
 * @author xs
 */
@Service
public class UserFormServiceImpl extends ServiceImpl<UserFormMapper, UserFormEntity> implements UserFormService {

    @Override
    public UserFormEntity getByKey(final String key) {
        if (StrUtil.isBlank(key)) {
            return null;
        }
        return this.getOne(Wrappers.<UserFormEntity>lambdaQuery().eq(UserFormEntity::getFormKey, key));
    }


}