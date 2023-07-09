package com.xs.form.request;

import com.xs.form.entity.strct.FormDataFilterStruct;
import lombok.Data;
import java.util.List;

/**
 * @author : xs
 * @description : 导出请求
 **/
@Data
public class ExportRequest {

    /**
     * 表单数据
     */
    @Data
    public static class FormData {
        private Integer currentPage;
        private Integer pageSize;
        private String mode;
        private String filename;
        private String type;
        private String sheetName;
        /**
         * 导出列
         */
        private List<String> columns;
        /**
         * 选中数据Id
         */
        private List<String> checkboxIds;
        private String formKey;

        /**
         * 权限组
         */
        private Long authGroupId;
        /**
         * 过滤字段
         */
        private FormDataFilterStruct filter;
    }

}
