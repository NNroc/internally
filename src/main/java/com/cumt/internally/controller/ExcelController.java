package com.cumt.internally.controller;

import com.cumt.internally.model.Result;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author NNroc
 * @date 2020/5/13 16:18
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {
    /**
     * 导出风险
     *
     * @return
     */
    @RequestMapping("/export/risk")
    public Result exportRisk() {
        return null;
    }

    /**
     * 导入风险
     *
     * @return
     */
    @RequestMapping("/import/risk")
    public Result importRisk() {
        return null;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public void readExcel(File file) throws Exception {
        InputStream inputStream = new FileInputStream(file);
        String fileName = file.getName();
        Workbook wb = null;
        //.xls 使用
        wb = new HSSFWorkbook(inputStream);
        //.xlsx 使用
//        wb=new XSSFWorkbook(inputStream);
        //对应 Excel 中的表格 id
        Sheet sheet = wb.getSheetAt(0);
        //获取第一行
        int firstRowIndex = sheet.getFirstRowNum();
        //获取最后一行
        int lastRowIndex = sheet.getLastRowNum();
        //第一行普遍是标题，故不用
        for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row != null) {
                //Student 为实例化的对象，可根据自己需求构建
                Student student = new Student();
                Cell cell = row.getCell(0);
                student.setStudentId(cell.toString());
                cell = row.getCell(1);
                student.setStudentName(cell.toString());
                cell = row.getCell(2);
                student.setGender(cell.toString());

                //需修改的内容 及 新加入内容
                cell = row.getCell(3);
                student.setBorn(cell.toString());
                cell = row.getCell(4);
                student.setNation(cell.toString());
                cell = row.getCell(5);
                student.setBirthPlace(cell.toString());
                cell = row.getCell(6);
                student.setIdCard(cell.toString());
                cell = row.getCell(7);
                student.setLive(cell.toString());
                display(student);
                merge(student);
            }
        }
    }

    public void merge(Student student) {
        Student studentHave = studentService.selectStudentById(student.getStudentId());
        if (studentHave == null) {
//            studentService.insertStudent(student);
        } else {
            studentHave.setBorn(student.getBorn());
            studentHave.setNation(student.getNation());
            studentHave.setBirthPlace(student.getBirthPlace());
            studentHave.setLive(student.getLive());
            studentHave.setIdCard(student.getIdCard());
            studentService.updateStudentByStudentId(studentHave);
        }
    }

    @RequestMapping("/insert")
    public void make() throws Exception {
        //读取 excal
        File file = new File("C:\\Users\\Secluded Wind\\Desktop\\2018级学生信息1.xls");
        readExcel(file);
        System.out.println("finish");
    }

}
