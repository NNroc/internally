package com.cumt.internally.controller;

import com.cumt.internally.annotation.AdministratorToken;
import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Result;
import com.cumt.internally.model.RiskControl;
import com.cumt.internally.model.RiskMark;
import com.cumt.internally.service.RiskControlService;
import com.cumt.internally.service.RiskMarkService;
import com.cumt.internally.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
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
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/get_all_risk")
    public Result getResult() {
        List<RiskMark> riskMarks = riskMarkService.selectAll();
        // 装入 RiskControl 实体
        List<RiskControl> riskControlList = riskControlService.selectAll();
        for (RiskMark riskMark : riskMarks) {
            for (RiskControl riskControl : riskControlList) {
                if (riskControl.getId() == riskMark.getRiskControlId()) {
                    riskControl.setPossibleGrade(riskControl.getPossibleGrade() + riskMark.getPossibleGrade());
                    riskControl.setEffectGrade(riskControl.getEffectGrade() + riskMark.getEffectGrade());
                    riskControl.setNum(riskControl.getNum() + 1);
                }
            }
        }
        for (RiskControl riskControl : riskControlList) {
            riskControl.setPossibleGrade(riskControl.getPossibleGrade() / riskControl.getNum());
            riskControl.setEffectGrade(riskControl.getEffectGrade() / riskControl.getNum());
            riskControl.setSumGrade(riskControl.getPossibleGrade() + riskControl.getEffectGrade());
        }
        Collections.sort(riskControlList);
        return null;
    }

    /**
     * // TODO 风险统计结果分析，排序（计算方式？等级？随便设），excel
     * 风险等级	风险个数 所占比例
     * <p>
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
    @RequestMapping("/get_result")
    public Result getAnalyse() {
        return null;
    }
}
