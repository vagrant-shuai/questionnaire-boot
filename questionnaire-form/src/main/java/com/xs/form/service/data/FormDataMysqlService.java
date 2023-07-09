package com.xs.form.service.data;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.xs.common.entity.BaseEntity;
import com.xs.form.entity.UserFormDataEntity;
import com.xs.form.mapper.UserFormDataMapper;
import com.xs.form.request.QueryFormResultRequest;
import com.xs.form.vo.FormDataTableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author : xs
 * @description : 表单数据基础服务
 **/
@Service
@Slf4j
public class FormDataMysqlService extends FormDataBaseService {

    @Autowired
    private UserFormDataMapper userFormDataMapper;

    @Autowired(required = false)
    private TduckMongoTemplate mongoTemplate;


    @Override
    public Boolean valueExist(String formKey, String formItemId, Object value) {
        return userFormDataMapper.selectOriginalDataValueCount(formKey, formItemId, value) > 0;
    }

    @Override
    public Boolean syncSaveData(UserFormDataEntity result) {
        if (null == mongoTemplate) {
            return true;
        }
        mongoTemplate.save(convertDocument(result), result.getFormKey());
        return true;
    }


    @Override
    public Boolean asyncUpdateData(UserFormDataEntity result) {
        if (null == mongoTemplate) {
            return true;
        }
        mongoTemplate.updateById(convertDocument(result), result.getId(), result.getFormKey());
        return true;
    }

    @Override
    public void asyncDeleteData(List<String> idList, String formKey) {
        if (null == mongoTemplate) {
            return;
        }
        mongoTemplate.deleteByIds(idList, formKey);
    }


    @Override
    public FormDataTableVO search(QueryFormResultRequest request) {
        // 拼接sql
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select * from fm_user_form_data where form_key = '").append(request.getFormKey()).append("'");
        //1. 拼接条件 查询条件 用大括号包起来 里面的条件会拼接 OR 或者 AND 不能影响其他默认附带条件 比如form_key 否则会错误查询
        StringBuilder whereBuilder = new StringBuilder();
        // 查询指定id数据
        if (ObjectUtil.isNotNull(request.getDataIds()) && 0 != request.getDataIds().size()) {
            whereBuilder.append(" and id in (").append(CollUtil.join(request.getDataIds(), ",")).append(")");
        }
        // 先查询总数，查询总数后再进行拼接order by 及 limit 语句
        StringBuilder countBuilder = new StringBuilder("select count(1) from fm_user_form_data where form_key = '").append(request.getFormKey()).append("'");
        countBuilder.append(whereBuilder);
        Long total = userFormDataMapper.selectCountBySql(countBuilder.toString());

        whereBuilder.append(" ORDER BY id DESC");
        // 分页
        if (ObjectUtil.isNotNull(request.getCurrent()) && ObjectUtil.isNotNull(request.getSize())) {
            whereBuilder.append(" limit ").append(request.getCurrent() * request.getSize()).append(",").append(request.getSize());
        }
        sqlBuilder.append(whereBuilder);
        List<UserFormDataEntity> userFormDataEntities = userFormDataMapper.selectRowsBySql(sqlBuilder.toString());

        // 过滤指定字段
        List<Map> maps = expandData(userFormDataEntities, request.getFilterFields());
        return new FormDataTableVO(maps, total);
    }


    @Override
    public List<Map> searchAll(QueryFormResultRequest request) {
        // 拼接sql
        List<UserFormDataEntity> userFormDataEntities = userFormDataMapper.selectRowsBySql("select * from fm_user_form_data where form_key = '" + request.getFormKey() + "'");
        return expandData(userFormDataEntities, null);
    }


    /**
     * 展开数据为一级
     */
    public List<Map> expandData(List<UserFormDataEntity> userFormDataEntities, String[] filterFields) {
        return userFormDataEntities.stream().map(item -> {
            Map<String, Object> processData = item.getOriginalData();
            Map<String, Object> resultMap = BeanUtil.beanToMap(item);
            resultMap.remove(UserFormDataEntity.Fields.originalData);
            resultMap.put(BaseEntity.Fields.createTime, LocalDateTimeUtil.formatNormal(item.getCreateTime()));
            resultMap.put(BaseEntity.Fields.updateTime, LocalDateTimeUtil.formatNormal(item.getUpdateTime()));
            processData.putAll(resultMap);
            // 只过滤指定字段
            if (filterFields != null) {
                Map<String, Object> filterMap = MapUtil.newHashMap();
                for (String filterField : filterFields) {
                    filterMap.put(filterField, processData.get(filterField));
                }
                return filterMap;
            }
            return processData;
        }).collect(Collectors.toList());
    }


}
