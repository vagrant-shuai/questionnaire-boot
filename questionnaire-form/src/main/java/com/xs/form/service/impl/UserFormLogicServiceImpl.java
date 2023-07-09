package com.xs.form.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xs.form.entity.UserFormLogicEntity;
import com.xs.form.mapper.UserFormLogicMapper;
import com.xs.form.service.UserFormLogicService;
import org.springframework.stereotype.Service;

/**
 * 表单逻辑
 */
@Service
public class UserFormLogicServiceImpl extends ServiceImpl<UserFormLogicMapper, UserFormLogicEntity> implements UserFormLogicService {
}
