package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.util.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
//用户登录
    @RequestMapping(value="/login")
    public String Userlogin(String username, String password, HttpSession session){
        boolean flag= userService.login(username, password);
        if(flag){
            session.setAttribute("username",username);
            return "redirect:main/main.jsp";
        }
        return "login";
    }
    @RequestMapping(value="/show")
    @ResponseBody
    public Map<String,Object> show(){
        List<Integer> categoies=userService.selAll();

        List<Integer> list=new ArrayList<Integer>();
        int q=0;
        for (Integer categoy : categoies) {
//            System.out.println("fkasfdhajkdhfjk"+categoy);
            if(categoy<=7){
                q=q+1;
                list.add(q);
            }
            if(categoy<=15 && categoy>7) {
                q = q + 1;
                list.add(q);
            }
            if(categoy<=30 && categoy>15){
                q=q+1;
                list.add(q);
            }
            if(categoy<=90 && categoy>30){
                q=q+1;
                list.add(q);
            }
            if(categoy<=185 && categoy>90){
                q=q+1;
                list.add(q);
            }
            if(categoy<=365 && categoy>185){
                q=q+1;
                list.add(q);
            }
        }

       /* for (Integer integer : list) {
            System.out.println("hjkashdjfahjfhlkja"+integer);
        }*/

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("list",list);
        return map;
    }
 /*   @RequestMapping(value="/exportUser",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
    public void export(String titles, String fileds, HttpServletResponse response){
        String[] title=titles.split(",");
        String[] filed=fileds.split(",");

        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        CellStyle cellStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        cellStyle.setDataFormat(format);

        //创建表
        Sheet sheet = workbook.createSheet("用户信息表");
        //创建标题行
        Row row = sheet.createRow(0);
        for (int i = 0; i <title.length; i++) {
            String s = title[i];
            Cell cell = row.createCell(i);
            cell.setCellValue(s);
        }
        //数据行
        List<User> users = userService.query();
        for (int i = 0; i < users.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            for (int j = 0; j < filed.length; j++) {
                Cell cell = row1.createCell(j);
                //填充id name password date
                Class<? extends User> userClass=users.get(i).getClass();
                //拼接get方法
                String methodName="get"+filed[j].substring(0,1).toUpperCase()+filed[j].substring(1);
                try {
                    Object invoke = userClass.getDeclaredMethod(methodName, null).invoke(users.get(i), null);
                    if(invoke instanceof Date){
                        sheet.autoSizeColumn((short)j);
                        cell.setCellStyle(cellStyle);
                        cell.setCellValue((Date)invoke);
                    }else{
                        cell.setCellValue(invoke.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        String name="用户信息.xls";
        String fileName="";
        try {
            fileName=new String(name.getBytes("utf-8"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("content-Disposition","attachment;fileName="+fileName);
        response.setContentType("application/vnd.ms-excel");
        try {
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    @RequestMapping(value="/importUser",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String  importUser(String fileds)throws Exception{
        String[] filed=fileds.split(",");
        ArrayList<User> users = new ArrayList<>();
        Workbook workbook = new HSSFWorkbook(new FileInputStream("f:/用户信息.xls"));
        Sheet sheet = workbook.getSheet("用户信息表");
        for (int i = 1; i<=sheet.getLastRowNum(); i++) {
            User user = new User();
            Row row = sheet.getRow(i);

            Cell cell1 = row.getCell(0);
            String stringCellValue = cell1.getStringCellValue();
            user.setUsername(stringCellValue);

            Cell cell3 = row.getCell(1);
            Date dateCellValue = cell3.getDateCellValue();
            user.setDate(dateCellValue);

            users.add(user);
        }
        for (User user : users) {
            userService.insert(user);
            System.out.println(user);
        }
        return null;
    }

}
