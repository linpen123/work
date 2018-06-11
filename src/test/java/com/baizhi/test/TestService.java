package com.baizhi.test;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Member;
import com.baizhi.entity.User;
import com.baizhi.service.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;


public class TestService{
    @Test
    public void test1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        //创建表格工作对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置日期格式
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日");

        //调节表格样式
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(format);

        //创建工作表
        Sheet sheet = workbook.createSheet("用户信息表");

        sheet.setColumnWidth(4,18*256);
        //创建属性行
        Row row = sheet.createRow(0);
        String[] title={"编号","姓名","密码","日期"};

        //创建单元格
        Cell cell=null;
        for (int i = 0; i < title.length; i++) {
            //表头标题
            cell = row.createCell(i);
            //填充单元格里的值
            cell.setCellValue(title[i]);
        }
        //设置数据行
        List<User> users = userService.query();
        for (int i = 0; i < users.size(); i++) {
            row=sheet.createRow(i+1);
            row.createCell(0).setCellValue(users.get(i).getId());
            row.createCell(1).setCellValue(users.get(i).getUsername());
            row.createCell(2).setCellValue(users.get(i).getPassword());

            Cell cell1 = row.createCell(3);
            cell1.setCellStyle(cellStyle);
            cell1.setCellValue(users.get(i).getDate());
        }

        try {
            workbook.write(new FileOutputStream(new File("f:/用户.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test2()throws Exception{
        ArrayList<User> users = new ArrayList<>();
        Workbook workbook = new HSSFWorkbook(new FileInputStream("f:/用户信息.xls"));
        Sheet sheet = workbook.getSheet("用户信息表");
        for (int i = 1; i<=sheet.getLastRowNum(); i++) {
            User user = new User();
            Row row = sheet.getRow(i);

            Cell cell = row.getCell(0);
            String stringCellValue = cell.getStringCellValue();
            user.setUsername(stringCellValue);

            Cell cell2 = row.getCell(1);
            Date dateCellValue = cell2.getDateCellValue();
            user.setDate(dateCellValue);

            users.add(user);
        }
        System.out.println(users.size());
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test3(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        AdminService adminService = (AdminService) applicationContext.getBean("adminService");
        Admin admin = adminService.selById("28e77dac698711e8b6dfb46d832eeeb9");
        List<Member> members = admin.getMembers();
        for (Member member : members) {
            System.out.println(member);
        }

    }

}
