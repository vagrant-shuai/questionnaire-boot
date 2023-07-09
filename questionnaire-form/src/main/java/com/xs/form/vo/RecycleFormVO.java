package com.xs.form.vo;

import com.xs.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @author : xs
 * @description : 回收站表单
 **/
@Data
@AllArgsConstructor
public class RecycleFormVO extends BaseEntity {
    private String key;
    /***
     * 收集数量
     */
    private Long resultCount;

    private String name;

    public RecycleFormVO(String key, Long resultCount, String name, LocalDateTime createTime, LocalDateTime updateTime) {
        this.key = key;
        this.resultCount = resultCount;
        this.name = name;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
