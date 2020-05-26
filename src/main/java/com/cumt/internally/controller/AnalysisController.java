package com.cumt.internally.controller;

import com.cumt.internally.annotation.AdministratorToken;
import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Result;
import com.cumt.internally.model.Risk;
import com.cumt.internally.model.RiskMark;
import com.cumt.internally.model.analysis.PaperNumber;
import com.cumt.internally.service.RiskControlService;
import com.cumt.internally.service.RiskMarkService;
import com.cumt.internally.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
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
     * // TODO 风险统计结果（列举所有风险，排序？得分高到低）
     * 职务：staffDuty
     * 副科级，0.15
     * 科级，0.15
     * 副处级，0.2
     * 处级，0.2
     * 校领导，0.3
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
     * // TODO 风险统计结果分析，排序（计算方式？等级？随便设）
     * 风险等级	风险个数 所占比例
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/get_result")
    public Result getAnalyse() {
        return null;
    }

    /**
     * // TODO 问卷统计份数分析（职级？问卷对象？所有人）
     * 职级	问卷份数 权值系数
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/get_num")
    public Result getNum() {
        int[] num = new int[6];
        List<RiskMark> riskMarkList = riskMarkService.selectAll();
        for (RiskMark riskMark : riskMarkList) {
            if (riskMark.getStaffDuty().equals("副科级")) {
                num[0]++;
            } else if (riskMark.getStaffDuty().equals("科级")) {
                num[1]++;
            } else if (riskMark.getStaffDuty().equals("副处级")) {
                num[2]++;
            } else if (riskMark.getStaffDuty().equals("处级")) {
                num[3]++;
            } else if (riskMark.getStaffDuty().equals("校领导")) {
                num[4]++;
            } else {
                return responseData.write("员工职务存在错误！建议查看员工职务并修改！", 444, new HashMap<>());
            }
            num[5]++;
        }
        List<PaperNumber> paperNumbers = new ArrayList<>();
        PaperNumber paperNumber = new PaperNumber();
        // 装入数据
        paperNumber.setStaffDuty("副科级");
        paperNumber.setPaperNum(num[0]);
        paperNumber.setWeight(0.15);
        paperNumbers.add(paperNumber);

        paperNumber.setStaffDuty("科级");
        paperNumber.setPaperNum(num[1]);
        paperNumber.setWeight(0.15);
        paperNumbers.add(paperNumber);

        paperNumber.setStaffDuty("副处级");
        paperNumber.setPaperNum(num[2]);
        paperNumber.setWeight(0.15);
        paperNumbers.add(paperNumber);

        paperNumber.setStaffDuty("处级");
        paperNumber.setPaperNum(num[3]);
        paperNumber.setWeight(0.15);
        paperNumbers.add(paperNumber);

        paperNumber.setStaffDuty("校领导");
        paperNumber.setPaperNum(num[4]);
        paperNumber.setWeight(0.15);
        paperNumbers.add(paperNumber);

        paperNumber.setStaffDuty("总计");
        paperNumber.setPaperNum(num[5]);
        paperNumber.setWeight(1);
        paperNumbers.add(paperNumber);
        return responseData.write("问卷统计份数分析", 200, paperNumbers);
    }


    /**
     * // TODO 废卷统计分析（问卷对象？）
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
