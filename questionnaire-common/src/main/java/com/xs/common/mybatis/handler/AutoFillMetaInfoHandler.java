package com.xs.common.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.xs.common.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author xs
 * mybatis 自动填充插件
 * @link https://baomidou.com/guide/typehandler.html
 */
@Slf4j
@Component
public class AutoFillMetaInfoHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName(BaseEntity.Fields.createTime, LocalDateTime.now(), metaObject);
        this.setFieldValByName(BaseEntity.Fields.updateTime, LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(BaseEntity.Fields.updateTime, LocalDateTime.now(), metaObject);
    }
}
