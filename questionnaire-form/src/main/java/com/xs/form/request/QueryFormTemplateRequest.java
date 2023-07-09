package com.xs.form.request;
import com.xs.common.entity.PageRequest;
import lombok.Data;

/**
 * @author : xs
 * @description : 查询表单模板
 **/
@Data
public class QueryFormTemplateRequest {


    /**
     * 分页查询
     */
    @Data
    public static class Page extends PageRequest {

        private String name;

        private Long type;
    }

}
