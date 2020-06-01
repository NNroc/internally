package com.cumt.internally.controller;

import com.cumt.internally.annotation.AdministratorToken;
import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Result;
import com.cumt.internally.model.RiskControl;
import com.cumt.internally.model.RiskMark;
import com.cumt.internally.service.RiskControlService;
import com.cumt.internally.service.RiskMarkService;
import com.cumt.internally.service.StaffService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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
    public Result getResult(HttpServletResponse response) throws Exception {
        List<RiskMark> riskMarks = riskMarkService.selectAll();
        // 装入 RiskControl 实体
        List<RiskControl> riskControlList = riskControlService.selectAll();
        for (RiskMark riskMark : riskMarks) {
            for (RiskControl riskControl : riskControlList) {
                if (riskControl.getId() == riskMark.getRiskControlId()) {
                    riskControl.setPossibleGrade(riskControl.getPossibleGrade() + riskMark.getPossibleGrade());
                    riskControl.setEffectGrade(riskControl.getEffectGrade() + riskMark.getEffectGrade());
                    riskControl.setNum(riskControl.getNum() + 1);
                    break;
                }
            }
        }
        for (RiskControl riskControl : riskControlList) {
            if (riskControl.getNum() != 0) {
                riskControl.setPossibleGrade(riskControl.getPossibleGrade() / riskControl.getNum());
                riskControl.setEffectGrade(riskControl.getEffectGrade() / riskControl.getNum());
            }
            riskControl.setSumGrade(riskControl.getPossibleGrade() + riskControl.getEffectGrade());
        }
        Collections.sort(riskControlList);
        for (int i = 0; i < riskControlList.size(); i++) {
            riskControlList.get(i).setRiskSort(i);
        }
        // TODO 需加入风险等级
        // 写入 excel
        String filename = "风险统计结果.xls";
        // 关键语句，内存中只创建100个对象
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("风险统计结果");
        sheet.setDefaultColumnWidth(9);
        Row row = null;
        Cell cell = null;

        // 第一行
        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue("风险排序");
        cell = row.createCell(1);
        cell.setCellValue("业务流程");
        cell = row.createCell(2);
        cell.setCellValue("流程节点");
        cell = row.createCell(3);
        cell.setCellValue("风险编号");
        cell = row.createCell(4);
        cell.setCellValue("风险描述");
        cell = row.createCell(5);
        cell.setCellValue("可能性");
        cell = row.createCell(6);
        cell.setCellValue("影响性");
        cell = row.createCell(7);
        cell.setCellValue("总分");
        cell = row.createCell(8);
        cell.setCellValue("风险等级");
        for (int i = 0; i < riskControlList.size(); i++) {
            row = sheet.createRow(i + 1);    //新建行对象
            cell = row.createCell(0);
            cell.setCellValue(riskControlList.get(i).getRiskSort());
            cell = row.createCell(1);
            cell.setCellValue(riskControlList.get(i).getProcessName());
            cell = row.createCell(2);
            cell.setCellValue(riskControlList.get(i).getProcessPoint());
            cell = row.createCell(3);
            cell.setCellValue(riskControlList.get(i).getRiskId());
            cell = row.createCell(4);
            cell.setCellValue(riskControlList.get(i).getRiskDescribe());
            cell = row.createCell(5);
            cell.setCellValue(riskControlList.get(i).getPossibleGrade());
            cell = row.createCell(6);
            cell.setCellValue(riskControlList.get(i).getEffectGrade());
            cell = row.createCell(7);
            cell.setCellValue(riskControlList.get(i).getSumGrade());
            cell = row.createCell(8);
            cell.setCellValue(riskControlList.get(i).getRiskLevel());
        }

        response.setContentType("application/vnd.ms-excel");
        // 这后面可以设置导出Excel的名称
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        // 刷新缓冲
        response.flushBuffer();
        // workbook将Excel写入到response的输出流中，供页面下载
        wb.write(response.getOutputStream());
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
