package com.cumt.internally.controller;

import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Result;
import com.cumt.internally.model.Staff;
import com.cumt.internally.service.StaffService;
import com.cumt.internally.utils.MD5Util;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author NNroc
 * @date 2020/6/24 10:18
 */
@RestController
@RequestMapping("/staff/excel")
public class StaffInputController {
    @Autowired
    ResponseData responseData;
    @Autowired
    StaffService staffService;

    /**
     * 批量导入员工
     *
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/import")
    public Result input(@RequestParam MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        String fileName = file.getOriginalFilename();
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
        Staff staff = new Staff();
        //第一行普遍是标题
        for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            Cell cell = row.getCell(0);
            cell.setCellType(CellType.STRING);
            // 删除已存在的
            staffService.deleteByStaffId(cell.getStringCellValue());
            staff.setStaffId(cell.getStringCellValue());

            cell = row.getCell(1);
            cell.setCellType(CellType.STRING);
            staff.setStaffName(String.valueOf(cell));

            cell = row.getCell(2);
            cell.setCellType(CellType.STRING);
            staff.setStaffUnit(String.valueOf(cell));

            cell = row.getCell(3);
            cell.setCellType(CellType.STRING);
            staff.setStaffPosition(String.valueOf(cell));

            cell = row.getCell(4);
            cell.setCellType(CellType.STRING);
            staff.setStaffProfessional(String.valueOf(cell));

            cell = row.getCell(5);
            if (Double.parseDouble(cell.toString()) != 4.0 && Double.parseDouble(cell.toString()) != 1.0) {
                return responseData.write("系统权限设置错误", 400, new HashMap<>());
            }
            staff.setStaffWeight(Double.parseDouble(cell.toString()));

            cell = row.getCell(6);
            cell.setCellType(CellType.STRING);
            staff.setStaffDuty(String.valueOf(cell));

            staff.setStaffPwd(MD5Util.md5(staff.getStaffId()));

            // 写入数据库
            staffService.insert(staff);
        }
        return responseData.write("导入成功", 200, new HashMap<>());
    }

    /**
     * 导出员工
     *
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/export")
    public Result export(HttpServletResponse response) throws IOException {
        List<Staff> staffList = staffService.selectAll();
        // 写入 excel
        String filename = "员工信息.xls";
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("员工信息");
        sheet.setDefaultColumnWidth(9);
        sheet.setColumnWidth(0, 10 * 256);
        sheet.setColumnWidth(1, 10 * 256);
        sheet.setColumnWidth(2, 10 * 256);
        sheet.setColumnWidth(3, 10 * 256);
        sheet.setColumnWidth(4, 10 * 256);
        sheet.setColumnWidth(5, 10 * 256);
        sheet.setColumnWidth(6, 10 * 256);
        Row row = null;
        Cell cell = null;

        // 第一行
        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue("工号");
        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell = row.createCell(2);
        cell.setCellValue("单位");
        cell = row.createCell(3);
        cell.setCellValue("职务");
        cell = row.createCell(4);
        cell.setCellValue("职称");
        cell = row.createCell(5);
        cell.setCellValue("系统权限");
        cell = row.createCell(6);
        cell.setCellValue("备注");

        for (int i = 0; i < staffList.size(); i++) {
            //新建行对象
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(staffList.get(i).getStaffId());
            cell = row.createCell(1);
            cell.setCellValue(staffList.get(i).getStaffName());
            cell = row.createCell(2);
            cell.setCellValue(staffList.get(i).getStaffUnit());
            cell = row.createCell(3);
            cell.setCellValue(staffList.get(i).getStaffPosition());
            cell = row.createCell(4);
            cell.setCellValue(staffList.get(i).getStaffProfessional());
            cell = row.createCell(5);
            cell.setCellValue(staffList.get(i).getStaffWeight());
            cell = row.createCell(6);
            cell.setCellValue(staffList.get(i).getStaffDuty());
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
}
