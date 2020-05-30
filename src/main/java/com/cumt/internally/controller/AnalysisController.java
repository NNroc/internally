package com.cumt.internally.controller;

import com.cumt.internally.annotation.AdministratorToken;
import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Result;
import com.cumt.internally.model.Risk;
import com.cumt.internally.service.RiskControlService;
import com.cumt.internally.service.RiskMarkService;
import com.cumt.internally.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
     * // TODO 风险统计结果（列举所有风险，排序？得分高到低），excel
     * 职务：staffDuty
     * 副科级
     * 科级
     * 副处级
     * 处级
     * 校领导
     * 科员
     * 技术专家
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/get_all_risk")
    public Result getResult() {
//        possibleAvg[0] = possibleAvg[0] * 0.15 / (double) num[0];
//        effectAvg[0] = effectAvg[0] * 0.15 / (double) num[0];
//        possibleAvg[1] = possibleAvg[1] * 0.15 / (double) num[1];
//        effectAvg[1] = effectAvg[1] * 0.15 / (double) num[1];
//        possibleAvg[2] = possibleAvg[2] * 0.2 / (double) num[2];
//        effectAvg[2] = effectAvg[2] * 0.2 / (double) num[2];
//        possibleAvg[3] = possibleAvg[3] * 0.2 / (double) num[3];
//        effectAvg[3] = effectAvg[3] * 0.2 / (double) num[3];
//        possibleAvg[4] = possibleAvg[4] * 0.3 / (double) num[4];
//        effectAvg[4] = effectAvg[4] * 0.3 / (double) num[4];
//        double possibleGrade=0;
//        double effectGrade=0;
        // 装入 Risk 实体
        List<Risk> riskList = new ArrayList<Risk>();
        return null;
    }

    /**
     * // TODO 风险统计结果分析，排序（计算方式？等级？随便设），excel
     * 风险等级	风险个数 所占比例
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/get_result")
    public Result getAnalyse() {
        return null;
    }
}
