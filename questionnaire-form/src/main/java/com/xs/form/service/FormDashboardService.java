package com.xs.form.service;

import com.xs.form.vo.FormReportVO;
import com.xs.form.vo.SituationVO;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author : xs
 * @description :
 **/
public interface FormDashboardService {

    /**
     * 表单统计
     *
     * @param formKey
     * @return
     */
    Collection<SituationVO> formReportSituation(String formKey);


    /**
     * 表单位置报表
     *
     * @param formKey
     * @return
     */
    Map<String, Integer> formReportPosition(String formKey);

    /**
     * 表单设备
     *
     * @param formKey
     * @return
     */
    List<FormReportVO.Device> formReportDevice(String formKey);


    /**
     * 来源
     *
     * @param formKey
     * @return
     */
    List<FormReportVO.Source> formReportSource(String formKey);


    /**
     * @param formKey
     * @return
     */
    List<FormReportVO.Analysis> formReportAnalysis(String formKey);
}
