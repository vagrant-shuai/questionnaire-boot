package com.xs.form.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xs.form.entity.UserFormViewCountEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 用户表单查看次数Mapper接口
 *
 * @author xs
 */
public interface UserFormViewCountMapper extends BaseMapper<UserFormViewCountEntity> {


    /**
     * 查看次数自增
     *
     * @param formKey 表单key
     */
    @Update("update fm_user_form_view_count set count = count + 1 , update_time=now() where form_key = #{formKey}")
    void incrementCount(@Param("formKey") String formKey);
}
