package com.cumt.internally.controller;

import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Result;
import com.cumt.internally.model.RiskControl;
import com.cumt.internally.model.RiskLevel;
import com.cumt.internally.model.RiskMark;
import com.cumt.internally.service.PositionService;
import com.cumt.internally.service.RiskControlService;
import com.cumt.internally.service.RiskLevelService;
import com.cumt.internally.service.RiskMarkService;
import com.cumt.internally.service.StaffService;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    PositionService positionService;
    @Autowired
    RiskLevelService riskLevelService;

    /**
     * 风险统计结果（列举所有风险，得分高到低），excel
     *
     * @param response
     * @param year     4位年份
     * @return
     * @throws Exception
     */
    @RequestMapping("/get_all_risk")
    public Result getResult(HttpServletResponse response, @RequestParam int year) throws Exception {
        if (year < 1000 || year > 9999) {
            return responseData.write("年份错误", 400, new HashMap<>());
        }
        List<RiskMark> riskMarks = riskMarkService.selectByYear(year);
        // 装入 RiskControl 实体
        List<RiskControl> riskControlList = riskControlService.selectAll();
        for (RiskMark riskMark : riskMarks) {
            for (RiskControl riskControl : riskControlList) {
                if (riskControl.getId() == riskMark.getRiskControlId()) {
                    // use 表示对应的权重
                    double use = getWeight(riskMark.getStaffPosition());
                    if (use == -1) {
                        return responseData.write("不存在该职位", 400, new HashMap<>());
                    }
                    riskControl.setPossibleGrade(riskControl.getPossibleGrade() + riskMark.getPossibleGrade() * use);
                    riskControl.setEffectGrade(riskControl.getEffectGrade() + riskMark.getEffectGrade() * use);
                    // num 指人数
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
            RiskLevel riskLevel = riskLevelService.select();
            if (riskControl.getSumGrade() < riskLevel.getLow()) {
                riskControl.setRiskLevel("低风险");
            } else if (riskControl.getSumGrade() < riskLevel.getMedium()) {
                riskControl.setRiskLevel("中风险");
            } else {
                riskControl.setRiskLevel("高风险");
            }
        }
        Collections.sort(riskControlList);
        for (int i = 0; i < riskControlList.size(); i++) {
            riskControlList.get(i).setRiskSort(i);
        }
        // 写入 excel
        String filename = "风险统计结果.xls";
        // 关键语句，内存中只创建100个对象
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("风险统计结果");
        sheet.setDefaultColumnWidth(9);
        sheet.setColumnWidth(0, 10 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 20 * 256);
        sheet.setColumnWidth(3, 20 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 10 * 256);
        sheet.setColumnWidth(6, 10 * 256);
        sheet.setColumnWidth(7, 10 * 256);
        sheet.setColumnWidth(8, 10 * 256);
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
     * 风险统计结果分析，排序，excel
     * 风险等级	风险个数 所占比例
     *
     * @param response
     * @param year     4位年份
     * @return
     * @throws IOException
     */
    @RequestMapping("/get_result")
    public Result getAnalyse(HttpServletResponse response, @RequestParam int year) throws IOException {
        int low = 0, medium = 0, high = 0;
        List<RiskMark> riskMarks = riskMarkService.selectByYear(year);
        // 装入 RiskControl 实体
        List<RiskControl> riskControlList = riskControlService.selectAll();
        for (RiskMark riskMark : riskMarks) {
            for (RiskControl riskControl : riskControlList) {
                if (riskControl.getId() == riskMark.getRiskControlId()) {
                    double use = getWeight(riskMark.getStaffPosition());
                    if (use == -1) {
                        return responseData.write("不存在该职位", 400, new HashMap<>());
                    }
                    riskControl.setPossibleGrade(riskControl.getPossibleGrade() + riskMark.getPossibleGrade() * use);
                    riskControl.setEffectGrade(riskControl.getEffectGrade() + riskMark.getEffectGrade() * use);
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
            RiskLevel riskLevel = riskLevelService.select();
            if (riskControl.getSumGrade() < riskLevel.getLow()) {
                riskControl.setRiskLevel("低风险");
                low++;
            } else if (riskControl.getSumGrade() < riskLevel.getMedium()) {
                riskControl.setRiskLevel("中风险");
                medium++;
            } else {
                riskControl.setRiskLevel("高风险");
                high++;
            }
        }
        // 写入 excel
        String filename = "风险统计结果分析.xls";
        // 关键语句，内存中只创建100个对象
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("风险统计结果分析");
        sheet.setDefaultColumnWidth(4);
        sheet.setColumnWidth(0, 10 * 256);
        sheet.setColumnWidth(1, 10 * 256);
        sheet.setColumnWidth(2, 20 * 256);
        Row row = null;
        Cell cell = null;

        // 第一行
        CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 2);
        sheet.addMergedRegion(region1);
        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue("风险统计结果分析");

        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("风险等级");
        cell = row.createCell(1);
        cell.setCellValue("风险个数");
        cell = row.createCell(2);
        cell.setCellValue("所占比例");

        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue("高风险");
        cell = row.createCell(1);
        cell.setCellValue(high);
        cell = row.createCell(2);
        cell.setCellValue(1.0 * high / (low + medium + high));

        row = sheet.createRow(3);
        cell = row.createCell(0);
        cell.setCellValue("中风险");
        cell = row.createCell(1);
        cell.setCellValue(medium);
        cell = row.createCell(2);
        cell.setCellValue(1.0 * medium / (low + medium + high));

        row = sheet.createRow(4);
        cell = row.createCell(0);
        cell.setCellValue("低风险");
        cell = row.createCell(1);
        cell.setCellValue(low);
        cell = row.createCell(2);
        cell.setCellValue(1.0 * low / (low + medium + high));

        row = sheet.createRow(5);
        cell = row.createCell(0);
        cell.setCellValue("合计");
        cell = row.createCell(1);
        cell.setCellValue(high + medium + low);
        cell = row.createCell(2);
        cell.setCellValue(1);

        // 第七行
        CellRangeAddress region2 = new CellRangeAddress(6, 6, (short) 0, (short) 2);
        sheet.addMergedRegion(region2);
        row = sheet.createRow(6);
        cell = row.createCell(0);

        // 对应 high medium low 的值
        double h = 0, m = 0, l = 0;
        // 算三种风险的比例
        if (high == 0) {
            if (medium == 0) {
                l = 1;
            } else if (low == 0) {
                m = 1;
            } else {
                l = 1.0 * low / medium;
                m = 1;
            }
        } else {
            m = 1.0 * medium / high;
            l = 1.0 * low / high;
            h = 1;
        }
        cell.setCellValue("高风险：中风险：低风险=" +
                String.format("%.2f", h) + "：" + String.format("%.2f", m) +
                "：" + String.format("%.2f", l));

        response.setContentType("application/vnd.ms-excel");
        // 这后面可以设置导出Excel的名称
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        // 刷新缓冲
        response.flushBuffer();
        // workbook将Excel写入到response的输出流中，供页面下载
        wb.write(response.getOutputStream());
        return null;
    }

    public double getWeight(String staffPosition) {
        if (staffPosition.equals("副科级")) {
            return positionService.selectByPosition("副科级").getWeight();
        } else if (staffPosition.equals("科级")) {
            return positionService.selectByPosition("科级").getWeight();
        } else if (staffPosition.equals("副处级")) {
            return positionService.selectByPosition("副处级").getWeight();
        } else if (staffPosition.equals("处级")) {
            return positionService.selectByPosition("处级").getWeight();
        } else if (staffPosition.equals("校领导")) {
            return positionService.selectByPosition("校领导").getWeight();
        } else if (staffPosition.equals("科员")) {
            return positionService.selectByPosition("科员").getWeight();
        } else if (staffPosition.equals("技术专家")) {
            return positionService.selectByPosition("技术专家").getWeight();
        } else {
            return -1;
        }
    }
}
