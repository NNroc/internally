package com.cumt.internally.controller;

import com.cumt.internally.annotation.AdministratorToken;
import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Result;
import com.cumt.internally.service.RiskControlService;
import com.cumt.internally.service.RiskMarkService;
import com.cumt.internally.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NNroc
 * @date 2020/5/23 20:50
 */
@RestController
@RequestMapping("/analysis")
public class AnalysisController {
    @Autowired
    ResponseData responseData;
    @Autowired
    RiskMarkService riskMarkService;
    @Autowired
    RiskControlService riskControlService;
    @Autowired
    StaffService staffService;

    /**
     * // TODO 风险统计结果，列举所有风险，排序
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/get_all_result")
    public Result getResult() {
        return null;
    }

    /**
     * // TODO 风险统计结果分析，排序
     * 风险等级	风险个数 所占比例
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/get_risk")
    public Result getAnalyse() {
        return null;
    }

    /**
     * // TODO 问卷统计份数分析
     * 职级	问卷份数 权值系数
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/get_num")
    public Result getNum() {
        return null;
    }


    /**
     * // TODO 废卷统计分析
     * 统计问卷	废卷 问卷合计 废卷率
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/get_waste")
    public Result getWaste() {
        return null;
    }
}
