package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class BannerController {
    @Autowired
    private BannerService bannerService;
// 分页展示所有
    @RequestMapping(value ="/showall",produces ={MediaType.APPLICATION_JSON_UTF8_VALUE} )
    @ResponseBody
    public Map showAll(@RequestParam(value="page")int page, @RequestParam(value="rows")int rows){
        int p=(page-1)*rows;//p为每页的第一行
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhh"+p);
        List<Banner> banners = bannerService.selAll(p,rows);
        for(Banner banner:banners){
            System.out.println("===========-------------==========="+banner);
        }
        List<Banner> list=new ArrayList<Banner>();
        for(Banner banner:banners){
            list.add(banner);
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("total",200);//数据库中的总计录数
        map.put("rows",list);
        return map;
    }
//添加轮播图
    @RequestMapping(value="/insertBanner",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public void insert(MultipartFile img, String title,String des,String status,String date,HttpServletRequest request) {
        //获取当前项目的路径
        String projectPath = request.getSession().getServletContext().getRealPath("/");
        File file = new File(projectPath);//获取项目
        String webappsPath = file.getParent();//获取项目所在的web环境的路径
        File uploadPath = new File(webappsPath + "/upload");//创建一个存储上传文件的文件夹
        if(!uploadPath.exists()){
            uploadPath.mkdir();
        }
        //获取原始文件名
        String oldname = img.getOriginalFilename();
        //获取文件的后缀名
        String extension = FilenameUtils.getExtension(oldname);
        //为文件起新名
        String s = UUID.randomUUID().toString();
        String newname = s.replace("-", "");
        newname=newname+"."+extension;
        try {
            //上传到指定的文件夹
            img.transferTo(new File(uploadPath.getPath(),newname));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Banner banner = new Banner();
        banner.setTitle(title);
        banner.setStatus(status);
        banner.setDes(des);
        banner.setImgpath("http://localhost:9898/upload/"+newname);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1=null;
        try {
            date1 = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("ggggggggggggggggggggggggg"+date1);
        System.out.println(date1 instanceof Date);
        banner.setDate(date1);
        bannerService.regiser(banner);
    }
//删除轮播图
    @RequestMapping(value="/delete",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public boolean del(int id){
        boolean flag = bannerService.delBanner(id);
        if(flag){
            return true;
        }
        return false;
    }
    //更改轮播图信息
    @RequestMapping(value="/update",produces ={MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public boolean upd(String status,int id,String title){
        Banner banner = bannerService.selById(id);
        banner.setStatus(status);
        banner.setTitle(title);
        boolean flag = bannerService.updateBanner(banner);
        if(flag){
            return true;
        }
        return false;
    }
}
