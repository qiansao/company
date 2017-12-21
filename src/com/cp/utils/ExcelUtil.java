package com.cp.utils;

import com.cp.model.Salary;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘宇欣 12.21
 * 操作Excel表格的功能类
 */
public class ExcelUtil {
    //excel文件对象
    private POIFSFileSystem fs;
    // 工作簿对象
    private HSSFWorkbook wb;
    // 工作表对象
    private HSSFSheet sheet;
    // 行对象
    private HSSFRow row;


    /**
     * 读取Excel数据内容
     * @param is
     * @return 一组学生数据list集合
     */
    public List<Salary> readExcelContent(InputStream is) {
        List<Salary> studentList = new ArrayList<>();
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //得到第一张工作表
        sheet = wb.getSheetAt(0);
        // 正文内容应该从第二行开始,第一行为表头的标题
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //所有要导入的学生对象使用这个默认头像
        //File file = new File("D:\\1.png");
//        InputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        byte[] b = new byte[(int) file.length()];
//        try {
//            inputStream.read(b);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //循环读取表格每一行，封装成一个Student对象，然后放入list中
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            String id = row.getCell(0).getRichStringCellValue().getString().trim();
            String staffNumber = row.getCell(1).getRichStringCellValue().getString().trim();
            String basicSalary = row.getCell(2).getRichStringCellValue().getString().trim();
            String bfSalary = row.getCell(3).getRichStringCellValue().getString().trim();
            String deductSalary = row.getCell(4).getRichStringCellValue().getString().trim();
            String personalTax = row.getCell(5).getRichStringCellValue().getString().trim();
            String socialSec = row.getCell(6).getRichStringCellValue().getString().trim();
            String reservedFunds = row.getCell(7).getRichStringCellValue().getString().trim();
            String finalSalary = row.getCell(8).getRichStringCellValue().getString().trim();
            String time = row.getCell(9).getRichStringCellValue().getString().trim();

            java.util.Date date = null;
            try {
                date = sdf.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Salary salary = new Salary();
            salary.setId(Integer.parseInt(id));
            salary.setStaffNumber(staffNumber);
            salary.setBasicSalary(Integer.parseInt(basicSalary));
            salary.setBfSalary(Integer.parseInt(bfSalary));
            salary.setDeductSalary(Integer.parseInt(deductSalary));
            salary.setPersonalTax(Integer.parseInt(personalTax));
            salary.setSocialSec(Integer.parseInt(socialSec));
            salary.setReservedFunds(Integer.parseInt(reservedFunds));
            salary.setFinalSalary(Integer.parseInt(finalSalary));

            salary.setTime(new Date(date.getTime()));
            studentList.add(salary);
        }
        return studentList;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("D:\\salary.xls");
        InputStream is = new FileInputStream(file);
        ExcelUtil excelUtil = new ExcelUtil();
        List<Salary> salaryList = excelUtil.readExcelContent(is);
        salaryList.forEach(salary -> System.out.println(salary));
    }
}

