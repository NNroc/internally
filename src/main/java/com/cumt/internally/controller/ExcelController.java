package com.cumt.internally.controller;

import com.cumt.internally.annotation.PassToken;
import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Project;
import com.cumt.internally.model.Result;
import com.cumt.internally.model.RiskControl;
import com.cumt.internally.service.ProjectService;
import com.cumt.internally.service.RiskControlService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;

/**
 * @author NNroc
 * @date 2020/5/13 16:18
 */
@Slf4j
@RestController
@RequestMapping("/excel")
public class ExcelController {
    @Autowired
    ResponseData responseData;
    @Autowired
    ProjectService projectService;
    @Autowired
    RiskControlService riskControlService;

    /**
     * 导入风险矩阵
     * 无需求，暂不启用
     *
     * @param file
     * @return
     */
    @PassToken
//    @RequestMapping("/import/risk")
    public Result importRisk(@RequestParam MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        String fileName = file.getOriginalFilename();
        log.info(fileName);
        Workbook wb = null;
        int lastIndexOf = fileName.lastIndexOf(".");
        //获取文件的后缀名
        String suffix = fileName.substring(lastIndexOf);
        if (suffix.equals(".xls")) {
            //.xls 使用
            wb = new HSSFWorkbook(inputStream);
        } else if (suffix.equals(".xlsx")) {
            //.xlsx 使用
            wb = new XSSFWorkbook(inputStream);
        } else {
            return responseData.write("文件格式错误", 400, new HashMap<>());
        }
        //对应 Excel 中的表格 id
        Sheet sheet = wb.getSheetAt(0);
        //获取第一行
        int firstRowIndex = sheet.getFirstRowNum();
        //获取最后一行
        int lastRowIndex = sheet.getLastRowNum();
        // 删除同名类别的（覆盖）
        projectService.deleteByType(fileName);
        // 用于记录之前的实体信息
        RiskControl use = new RiskControl();
        //第一行普遍是标题
        for (int rowIndex = firstRowIndex; rowIndex <= lastRowIndex; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            // 判断下一行有没有东西
            boolean haveMessage = true;
            for (int i = 0; i <= 10; i++) {
                if (!StringUtils.isBlank(row.getCell(i).toString())) {
                    haveMessage = false;
                    break;
                }
            }
            if (haveMessage) {
                break;
            }

            if (row.getCell(0).toString().equals("业务流程")) {
                continue;
            }
            RiskControl riskControl = new RiskControl();
            // 判断主流程名称，并更新
            if (!StringUtils.isBlank(row.getCell(0).toString())) {
                boolean flag = true;
                for (int i = 1; i <= 10; i++) {
                    if (!StringUtils.isBlank(row.getCell(i).toString())) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    use.setMainName(row.getCell(0).toString());
                    continue;
                }
            }
            riskControl.setMainName(use.getMainName());

            Cell cell = row.getCell(0);
            if (!StringUtils.isBlank(cell.toString())) {
                use.setProcessName(cell.toString());
            }
            riskControl.setProcessName(use.getProcessName());

            cell = row.getCell(1);
            if (!StringUtils.isBlank(cell.toString())) {
                use.setProcessPoint(cell.toString());
            }
            riskControl.setProcessPoint(use.getProcessPoint());

            cell = row.getCell(2);
            if (!StringUtils.isBlank(cell.toString())) {
                use.setRiskId(cell.toString());
            }
            riskControl.setRiskId(use.getRiskId());

            cell = row.getCell(3);
            if (!StringUtils.isBlank(cell.toString())) {
                use.setRiskDescribe(cell.toString());
            }
            riskControl.setRiskDescribe(use.getRiskDescribe());

            cell = row.getCell(4);
            if (!StringUtils.isBlank(cell.toString())) {
                use.setControlObjectives(cell.toString());
            }
            riskControl.setControlObjectives(use.getControlObjectives());

            cell = row.getCell(5);
            if (!StringUtils.isBlank(cell.toString())) {
                use.setControlId(cell.toString());
            }
            riskControl.setControlId(use.getControlId());

            cell = row.getCell(6);
            if (!StringUtils.isBlank(cell.toString())) {
                use.setControlName(cell.toString());
            }
            riskControl.setControlName(use.getControlName());

            cell = row.getCell(7);
            if (!StringUtils.isBlank(cell.toString())) {
                use.setControlMeasures(cell.toString());
            }
            riskControl.setControlMeasures(use.getControlMeasures());

            cell = row.getCell(8);
            if (!StringUtils.isBlank(cell.toString())) {
                use.setResponsiblePosition(cell.toString());
            }
            riskControl.setResponsiblePosition(use.getResponsiblePosition());

            cell = row.getCell(9);
            if (!StringUtils.isBlank(cell.toString())) {
                use.setCorrespondingSystem(cell.toString());
            }
            riskControl.setCorrespondingSystem(use.getCorrespondingSystem());

            cell = row.getCell(10);
            if (!StringUtils.isBlank(cell.toString())) {
                use.setEvidence(cell.toString());
            }
            riskControl.setEvidence(use.getEvidence());

            riskControl.setCreateTime(new Date());
            riskControl.setUpdateTime(new Date());

            // 写入数据库
            riskControlService.insert(riskControl);
        }
        return responseData.write("导入成功", 200, new HashMap<>());
    }

    /**
     * 导出风险矩阵
     *
     * @return
     */
    @RequestMapping("/export/risk")
    public Result exportRisk() {
        return null;
    }

    /**
     * 流程图信息导入
     * 无需求，暂不启用·
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/import/flow_sheet")
    @PassToken
    public Result readExcelFlowSheet(@RequestParam MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        String fileName = file.getOriginalFilename();
        log.info(fileName);
        Workbook wb = null;
        int lastIndexOf = fileName.lastIndexOf(".");
        //获取文件的后缀名
        String suffix = fileName.substring(lastIndexOf);
        if (suffix.equals(".xls")) {
            //.xls 使用
            wb = new HSSFWorkbook(inputStream);
        } else if (suffix.equals(".xlsx")) {
            //.xlsx 使用
            wb = new XSSFWorkbook(inputStream);
        } else {
            return responseData.write("文件格式错误", 400, new HashMap<>());
        }
        //对应 Excel 中的表格 id
        Sheet sheet = wb.getSheetAt(0);
        //获取第一行
        int firstRowIndex = sheet.getFirstRowNum();
        //获取最后一行
        int lastRowIndex = sheet.getLastRowNum();
        // 删除同名类别的（覆盖）
        projectService.deleteByType(fileName);
        //第一行普遍是标题
        for (int rowIndex = firstRowIndex; rowIndex <= lastRowIndex; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row.getCell(0).toString().equals("编号")) {
                continue;
            }
            if (!StringUtils.isBlank(row.getCell(0).toString())) {
                Project project = new Project();
                Cell cell = row.getCell(0);
                project.setNum((int) Double.parseDouble(cell.toString()));
                cell = row.getCell(1);
                project.setStepName(cell.toString());
                cell = row.getCell(3);
                project.setControlId(cell.toString());
                cell = row.getCell(4);
                project.setStepDescribe(cell.toString());
                cell = row.getCell(5);
                project.setDocument(cell.toString());
                project.setCreateTime(new Date());
                project.setUpdateTime(new Date());
//                 类别，测试时使用
                cell = row.getCell(6);
                project.setType(cell.toString());
                // 类别，正常情况时使用，但是好像不用了
//                project.setType(fileName);
                cell = row.getCell(2);
                project.setDepartment(cell.toString());
                while ((rowIndex + 1) <= lastRowIndex &&
                        StringUtils.isBlank(sheet.getRow(rowIndex + 1).getCell(0).toString()) &&
                        !StringUtils.isBlank(sheet.getRow(rowIndex + 1).getCell(2).toString())) {
                    rowIndex++;
                    project.setDepartment(project.getDepartment() + ";" + sheet.getRow(rowIndex).getCell(2).toString());
                }
                // 写入数据库
                projectService.insert(project);
            }
        }
        return responseData.write("导入成功", 200, new HashMap<>());
    }

}
