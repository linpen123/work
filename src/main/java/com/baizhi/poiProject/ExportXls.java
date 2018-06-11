package com.baizhi.poiProject;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class ExportXls {
    public static void main(String[] args)throws Exception{
        Workbook workbook = new HSSFWorkbook();
        //日期格式转换
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日");

        //调节表格样式
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(format);
        //创建表
        Sheet sheet = workbook.createSheet("hello");
        //第一个参数是第几列  第二个参数列宽 *256
        sheet.setColumnWidth(0,13*256);
        sheet.setColumnWidth(1,18*256);
        //创建行
        Row row = sheet.createRow(0);
        //创建单元格
        Cell cell = row.createCell(0);
        //cell.setCellValue("编号");
        cell.setCellValue("你好吗 嗯我很好");
        Cell cell1 = row.createCell(1);
        cell1.setCellStyle(cellStyle);
        cell1.setCellValue(new Date());
        workbook.write(new FileOutputStream(new File("f:/1.xls")));

    }
}
