package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chaptor;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChaptorService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ChaptorService chaptorService;

    @RequestMapping(value="/showAll",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    //展示所有
    public List<Album> show(){
        List<Album> albums = albumService.selAll();

        return albums;
    }

    @RequestMapping(value="/addalbum",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public void addAlbum(MultipartFile img,String title,int count,String score,String author,String broadCast,String brief,HttpServletRequest request){
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
        Album album = new Album();
        album.setTitle(title);
        album.setCount(count);
        album.setAuthor(author);
        album.setBrief(brief);
        album.setBroadCast(broadCast);
        album.setScore(score);
        album.setImg("http://localhost:9898/upload/"+newname);
        album.setPublishDate(new Date());
        albumService.insert(album);
    }
    @RequestMapping(value="/addchaptor",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public void addChaptor(MultipartFile audio,Chaptor chaptor,HttpServletRequest request){
        //获取当前项目的路径
        String projectPath = request.getSession().getServletContext().getRealPath("/");
        File file = new File(projectPath);//获取项目
        String webappsPath = file.getParent();//获取项目所在的web环境的路径
        File uploadPath = new File(webappsPath + "/upload");//创建一个存储上传文件的文件夹
        if(!uploadPath.exists()){
            uploadPath.mkdir();
        }
        //获取原始文件名
        String oldname =audio.getOriginalFilename();
        //获取文件的后缀名
        String extension = FilenameUtils.getExtension(oldname);
        //为文件起新名
        String s = UUID.randomUUID().toString();
        String newname = s.replace("-", "");
        newname=newname+"."+extension;
        try {
            //上传到指定的文件夹
            audio.transferTo(new File(uploadPath.getPath(),newname));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = UUID.randomUUID().toString();
        String id = str.replace("-", "");
        chaptor.setId(id);
        chaptor.setUrlpath("http://localhost:9898/upload/"+newname);
        chaptorService.insert(chaptor);

    }
    @RequestMapping(value="/down",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public void down(String url, String oldname, HttpServletResponse response, HttpServletRequest request){
        //获取文件存储位置
        String projectPath = request.getSession().getServletContext().getRealPath("/");
        //获取文件
        File file = new File(projectPath);
        //获取web路径
        String webappPath = file.getParent();
        //下载的路径
        String urlpath=url.substring(21);
        String FilePath=webappPath+urlpath;
        //下载文件的绝对路径
        File downfile = new File(FilePath);

        //设置响应头 类型
        String filename=null;
        try {
            filename=new String(oldname.getBytes("utf-8"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("content-Disposition","attachment;filename="+filename);
        response.setContentType("audio/mpeg");

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(downfile));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
