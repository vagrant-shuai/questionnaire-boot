package com.xs.form.request;

import com.xs.common.entity.PageRequest;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class PublicQueryRequest extends PageRequest {
    private String formKey;

    private Long queryId;

    private Map<String, Object> queryParams = new HashMap<>();
}
