package com.xs.form.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xs.form.entity.FormThemeCategoryEntity;
import com.xs.form.entity.FormThemeEntity;
import com.xs.form.mapper.FormThemeCategoryMapper;
import com.xs.form.mapper.FormThemeMapper;
import com.xs.form.service.FormThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 表单主题外观模板(FormTheme)表服务实现类
 *
 * @author xs
 */
@Service
@RequiredArgsConstructor
public class FormThemeServiceImpl extends ServiceImpl<FormThemeMapper, FormThemeEntity> implements FormThemeService {

    private final FormThemeCategoryMapper formThemeCategoryMapper;

    @Override
    public List<FormThemeCategoryEntity> listThemeCategories() {
        return formThemeCategoryMapper.selectList(null);
    }

    @Override
    public FormThemeCategoryEntity getThemeCategory(Long categoryId) {
        return formThemeCategoryMapper.selectById(categoryId);
    }

    @Override
    public Boolean saveThemeCategory(FormThemeCategoryEntity entity) {
        return formThemeCategoryMapper.insert(entity) > 0;
    }

    @Override
    public Boolean updateThemeCategory(FormThemeCategoryEntity entity) {
        return formThemeCategoryMapper.updateById(entity) > 0;
    }

    @Override
    public Boolean deleteThemeCategory(List<Long> categoryIds) {
        return formThemeCategoryMapper.deleteBatchIds(categoryIds) > 0;
    }
}