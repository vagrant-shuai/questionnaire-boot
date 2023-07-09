package com.xs.api.web.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xs.common.util.CacheUtils;
import com.xs.common.util.Result;
import com.xs.form.entity.UserFormDataEntity;
import com.xs.form.service.FormDashboardService;
import com.xs.form.service.UserFormDataService;
import com.xs.form.service.UserFormViewCountService;
import com.xs.form.util.FormAuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 表单报表
 *
 * @author : xs
 * @description : 报表相关接口
 **/
@RestController
@RequiredArgsConstructor
public class FormDashboardController {

    private final CacheUtils cacheUtils;
    private final UserFormViewCountService userFormViewCountService;
    private final UserFormDataService userFormDataService;
    private final FormDashboardService formDashboardService;

    /**
     * 表单收集信息
     */
    @GetMapping("/user/form/report/stats")
    public Result formReportStats(String formKey) {
        //浏览量
        Long viewCount = userFormViewCountService.count(formKey);
        //平均完成时间
        Map<String, Object> resultMap = userFormDataService.getMap(Wrappers.<UserFormDataEntity>query().select("AVG(complete_time) as avgCompleteTime, count(1) as completeCount").eq("form_key", formKey));
        resultMap.put("viewCount", viewCount);
        return Result.success(resultMap);
    }


    /**
     * 表单收集情况按周查看
     */
    @GetMapping("/user/form/report/situation")
    public Result formReportSituation(String formKey) {
        FormAuthUtils.hasPermission(formKey);
        return Result.success(formDashboardService.formReportSituation(formKey));
    }


    /**
     * 项目收集位置情况
     */
    @GetMapping("/user/form/report/position")
    public Result formReportPosition(String formKey) {
        FormAuthUtils.hasPermission(formKey);
        return Result.success(formDashboardService.formReportPosition(formKey));
    }


    /**
     * 项目收集设备
     */
    @GetMapping("/user/form/report/device")
    public Result formReportDevice(String formKey) {
        FormAuthUtils.hasPermission(formKey);
        return Result.success(formDashboardService.formReportDevice(formKey));
    }


    /**
     * 项目收集来源
     */
    @GetMapping("/user/form/report/source")
    public Result formReportSource(String formKey) {
        FormAuthUtils.hasPermission(formKey);
        return Result.success(formDashboardService.formReportSource(formKey));
    }

    /**
     * 数据分析
     */
    @GetMapping("/user/form/report/analysis")
    public Result formReportAnalysis(String formKey) {
        FormAuthUtils.hasPermission(formKey);
        return Result.success(formDashboardService.formReportAnalysis(formKey));
    }
}
