package com.xs.form.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.xs.common.constant.CommonConstants;
import com.xs.common.util.*;
import com.xs.form.entity.UserFormDataEntity;
import com.xs.form.entity.UserFormItemEntity;
import com.xs.form.entity.enums.FormItemTypeEnum;
import com.xs.form.entity.strct.UploadResultStruct;
import com.xs.form.mapper.UserFormDataMapper;
import com.xs.form.request.QueryFormResultRequest;
import com.xs.form.service.UserFormDataService;
import com.xs.form.service.UserFormItemService;
import com.xs.form.util.FormDataUtils;
import com.xs.form.vo.FormDataTableVO;
import com.xs.form.vo.FormFieldVO;
import com.xs.storage.OssStorageFactory;
import com.xs.storage.util.StorageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xs.form.constant.FormRedisKeyConstants.FORM_RESULT_NUMBER;


/**
 * 表单表单项(FormResult)表服务实现类
 *
 * @author xs
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserFormDataServiceImpl extends ServiceImpl<UserFormDataMapper, UserFormDataEntity> implements UserFormDataService {

    private final UserFormItemService userFormItemService;
    private final CacheUtils redisUtils;
    private final FormDataUtils formDataUtils;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> saveFormResult(UserFormDataEntity entity) {
        HashMap<String, Object> result = MapUtil.newHashMap();
        String formKey = entity.getFormKey();
        entity.setSubmitAddress(AddressUtils.getRealAddressByIP(entity.getSubmitRequestIp()));
        entity.setSerialNumber(redisUtils.incr(StrUtil.format(FORM_RESULT_NUMBER, formKey), CommonConstants.ConstantNumber.ONE));
        this.save(entity);
        formDataUtils.syncSaveFormData(entity);
        result.put("id", entity.getId());
        return result;
    }


    /**
     * 下载表单结果中的附件
     *
     * @param request
     * @return
     */
    @Override
    public Result downloadFormResultFile(QueryFormResultRequest request) {
        String uuid = IdUtil.simpleUUID();
        List<UserFormItemEntity> userFormItemEntityList = userFormItemService.list(Wrappers.<UserFormItemEntity>lambdaQuery().eq(UserFormItemEntity::getFormKey, request.getFormKey()).in(UserFormItemEntity::getType, CollUtil.newArrayList(FormItemTypeEnum.UPLOAD.toString(), FormItemTypeEnum.IMAGE_UPLOAD.toString())));
        //结果
        List<Map> rows = null;
        if (ObjectUtil.isNull(request.getCurrent()) && ObjectUtil.isNull(request.getSize())) {
            rows = formDataUtils.searchAll(request);
        } else {
            FormDataTableVO formDataTableVO = this.listFormDataTable(request);
            rows = formDataTableVO.getRows();
        }
        if (CollectionUtil.isEmpty(rows) || CollectionUtil.isEmpty(userFormItemEntityList)) {
            return Result.failed("暂无收集附件，无法下载");
        }
        List<Map> finalRows = rows;
        ThreadUtil.execAsync(() -> {
            TimeInterval timer = DateUtil.timer();
            List<String> paths = new ArrayList<>();
            List<InputStream> ins = new ArrayList<>();
            try {
                finalRows.forEach(result -> {
                    int index = 0;
                    userFormItemEntityList.forEach(item -> {
                        List<UploadResultStruct> uploadResults = JsonUtils.jsonToList(JsonUtils.objToJson(MapUtil.get(result, item.getFormItemId(), List.class)), UploadResultStruct.class);
                        if (CollectionUtil.isNotEmpty(uploadResults)) {
                            uploadResults.forEach(uFile -> {
                                if (StrUtil.isNotBlank(uFile.getUrl())) {
                                    paths.add(FileNameUtil.getName(uFile.getUrl()));
                                    byte[] bytes = HttpUtil.downloadBytes(uFile.getUrl());
                                    ins.add(IoUtil.toStream(bytes));
                                }
                            });
                        }
                    });
                    AsyncProcessUtils.setProcess(uuid, ++index / finalRows.size());
                });
                // 压缩上传oss
                ByteArrayOutputStream zipOutputStream = new ByteArrayOutputStream();
                ZipUtil.zip(zipOutputStream, paths.toArray(new String[]{}), ins.toArray(new InputStream[]{}));
                String downloadUrl = OssStorageFactory.getStorageService().upload(zipOutputStream.toByteArray(), StorageUtils.generateFileName("download", ".zip"));
                AsyncProcessUtils.setProcess(uuid, downloadUrl);
                log.info("export file cost time: {}", timer.interval());
            } catch (Exception e) {
                log.error("download file", e);
            }
        });
        return Result.success(uuid);
    }


    @Override
    public FormDataTableVO listFormDataTable(QueryFormResultRequest request) {
        return formDataUtils.search(request);
    }


    @Override
    public Boolean deleteByIds(List<String> dataIdList, String formKey) {
        baseMapper.deleteBatchIds(dataIdList);
        formDataUtils.asyncDeleteEsDocument(dataIdList, formKey);
        return true;
    }

    @Override
    public Boolean updateFormResult(UserFormDataEntity entity) {
        UserFormDataEntity dataEntity = this.getById(entity.getId());
        dataEntity.setOriginalData(entity.getOriginalData());
        dataEntity.setUpdateBy(entity.getUpdateBy());
        boolean update = this.updateById(dataEntity);
        // 查询数据 同步到es 避免数据变空被覆盖
        formDataUtils.asyncUpdateEsDocument(dataEntity);
        return update;
    }

    @Override
    public Result getFormDataDetails(String dataId) {
        Map<String, Object> result = Maps.newHashMap();
        UserFormDataEntity dataEntity = this.getById(dataId);
        List<FormFieldVO> formFields = userFormItemService.listFormFields(dataEntity.getFormKey());
        // 表单字段
        result.put("formFields", formFields);
        // 表单填写数据
        if (ObjectUtil.isNotNull(dataEntity)) {
            Map<String, Object> originalData = dataEntity.getOriginalData();
            dataEntity.setOriginalData(null);
            originalData.putAll(BeanUtil.beanToMap(dataEntity, false, true));
            result.put("formData", originalData);
        }
        return Result.success(result);
    }
}
