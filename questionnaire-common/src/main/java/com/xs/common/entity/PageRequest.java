package com.xs.common.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author : xs
 * @description : 分页
 **/
@Data
public class PageRequest {

    private long current = 1;
    private long size = 50;


    public Page toMybatisPage() {
        return new Page(current, size);
    }
}
