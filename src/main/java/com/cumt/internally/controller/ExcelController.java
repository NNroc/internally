package com.cumt.internally.controller;

import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Project;
import com.cumt.internally.model.Result;
import com.cumt.internally.service.ProjectService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author NNroc
 * @date 2020/5/13 16:18
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {
    @Autowired
    ResponseData responseData;
    @Autowired
    ProjectService projectService;

    /**
     * 导入风险
     *
     * @return
     */
    @RequestMapping("/import/risk")
    public Result importRisk() {
        return null;
    }

    /**
     * 导出风险
     *
     * @return
     */
    @RequestMapping("/export/risk")
    public Result exportRisk() {
        return null;
    }


    @RequestMapping("/import/flow_sheet")
    public Result readExcelFlowSheet(@RequestParam MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        String fileName = file.getName();
        Workbook wb = null;
        int lastIndexOf = fileName.lastIndexOf(".");
        //获取文件的后缀名 .jpg
        String suffix = fileName.substring(lastIndexOf);
        if (suffix.equals(".xls")) {
            //.xls 使用
            wb = new HSSFWorkbook(inputStream);
        } else if (suffix.equals(".xlsx")) {
            //.xlsx 使用
            wb = new XSSFWorkbook(inputStream);
        } else {
            return responseData.write("文件格式错误",400,new HashMap<>());
        }
        //对应 Excel 中的表格 id
        Sheet sheet = wb.getSheetAt(0);
        //获取第一行
        int firstRowIndex = sheet.getFirstRowNum();
        //获取最后一行
        int lastRowIndex = sheet.getLastRowNum();
        //第一行普遍是标题，故不用
        for (int rowIndex = firstRowIndex; rowIndex <= lastRowIndex; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row.getCell(0).toString().equals("编号")) {
                continue;
            }
            if (row != null) {
                Project project = new Project();
                Cell cell = row.getCell(0);
                project.setNum(cell.toString());
                cell = row.getCell(1);
                project.setStepName(cell.toString());
                cell = row.getCell(2);
                project.setDepartment(cell.toString());
                cell = row.getCell(3);
                project.setControlId(cell.toString());
                cell = row.getCell(4);
                project.setStepDescribe(cell.toString());
                cell = row.getCell(5);
                project.setStepDescribe(cell.toString());
                // 类别，测试时使用
                cell = row.getCell(6);
                project.setType(cell.toString());
                // 类别，正常情况时使用
//                project.setType(fileName);
                // 删除同名类别的（覆盖）
                // 写入数据库
            }
        }
    }

}
