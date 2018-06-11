package com.baizhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Album;
import com.baizhi.entity.Member;
import com.baizhi.entity.User;
import com.baizhi.service.AdminService;
import com.baizhi.service.AlbumService;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value="/test")
public class InterceptorController {
    @Autowired
    private UserService userService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private AdminService adminService;

    @RequestMapping(value="/login")
    @ResponseBody
//    登录接口
    public JSONObject login(String username,String password){
        JSONObject jsonObject = new JSONObject();
        boolean flag = userService.login(username, password);
        User user=null;
        if(flag){
            user = userService.selByNameAndPassword(username, password);
            jsonObject.put("id",user.getId());
            jsonObject.put("name",user.getUsername());
            jsonObject.put("password",user.getPassword());
            jsonObject.put("dharmaName",user.getDharmaName());
            jsonObject.put("sex",user.getSex());
            jsonObject.put("headpic",user.getHeadpic());
            jsonObject.put("status",user.getStatus());
            jsonObject.put("date",user.getDate());
            jsonObject.put("province",user.getProvince());
            jsonObject.put("city",user.getCity());

            return jsonObject;
        }else{
            jsonObject.put("error","-200");
            jsonObject.put("errmsg","登录信息有误");
            return jsonObject;
        }
    }
    @RequestMapping(value="/album")
    @ResponseBody
//    专辑接口
    public JSONObject testAlbum(int id,int uid) {
        JSONObject jsonObject = new JSONObject();
        Album album = albumService.selById(id);
        User user = userService.selById(uid);
        if(album!=null && user!=null){
            jsonObject.put("album",album);
            return jsonObject;
        }else{
            jsonObject.put("error","-200");
            jsonObject.put("errmsg","你个大傻逼");
            return jsonObject;
        }
    }
    @RequestMapping(value="/regiser")
    @ResponseBody
//    注册接口
    public JSONObject testRegiser(String phoneNum,String password){
        JSONObject jsonObject = new JSONObject();
        Admin admin = adminService.selByPhoneNum(phoneNum);
        Admin admin1=null;
        if(admin==null){
            admin1 = new Admin();
            String s = UUID.randomUUID().toString();
            String id = s.replace("-", "");
            admin1.setId(id);
            admin1.setPhoneNum(phoneNum);
            admin1.setPassword(password);
            boolean flag= adminService.insert(admin1);
            if(flag){
                jsonObject.put("phoneNum",phoneNum);
                jsonObject.put("id",id);
                jsonObject.put("password",password);

                return  jsonObject;
            }else{
                jsonObject.put("error","-200");
                jsonObject.put("errmsg","检查你的注册信息是否有误");
                return jsonObject;
            }
        }else{
            jsonObject.put("error","-200");
            jsonObject.put("errmsg","该手机号已经注册过");
            return jsonObject;
        }
    }
    @RequestMapping(value="/update")
    @ResponseBody
//   修改个人信息接口
    public JSONObject testUpdate(User user){
        JSONObject jsonObject = new JSONObject();
        User user1 = userService.selById(user.getId());
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        user1.setDate(user.getDate());
        user1.setStatus(user.getStatus());
        user1.setProvince(user.getProvince());
        user1.setHeadpic(user.getHeadpic());
        user1.setSex(user.getSex());
        user1.setCity(user.getCity());
        user1.setDharmaName(user.getDharmaName());
        boolean flag = userService.updateById(user1);
        if(flag){
            jsonObject.put("id",user.getId());
            jsonObject.put("username",user.getUsername());
            jsonObject.put("password",user.getPassword());
            jsonObject.put("dharmaName",user.getDharmaName());
            jsonObject.put("sex",user.getSex());
            jsonObject.put("headpic",user.getHeadpic());
            jsonObject.put("status",user.getStatus());
            jsonObject.put("date",user.getDate());
            jsonObject.put("province",user.getProvince());
            jsonObject.put("city",user.getCity());

            return jsonObject;
        }else{
            jsonObject.put("error","-200");
            jsonObject.put("error_msg","手机号已经存在");
            return jsonObject;
        }

    }
    @RequestMapping(value="/testcode")
    @ResponseBody
//    获取短信验证码接口
    public String testCode(String phone){
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb=new StringBuilder(4);
        for(int i=0;i<4;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        Admin admin = adminService.selByPhoneNum(phone);
        if(admin==null) {

            return "please regiser your phone";
        }else{
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpSession session = requestAttributes.getRequest().getSession();
            session.setAttribute("sb",sb.toString());
            return sb.toString();
        }
    }
    @RequestMapping(value="/checkcode")
    @ResponseBody
//    短信验证码校验接口
    public Map testCheck(String phsone,String code){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession();
        String oldcode = (String) session.getAttribute("sb");
        System.out.println("hdfjkahjdfhjakhfjkahjklhfjka"+oldcode);
        Map<String,Object> map=new HashMap<String,Object>();
        if(code.equals(oldcode)){
            map.put("result","success");
            return map;
        }else{
            map.put("result","fail");
            return map;
        }

    }
    @RequestMapping(value="/member")
    @ResponseBody
    //获取会员列表
    public List testMember(String uid){
        Admin admin = adminService.selById(uid);
        List<Member> list=new ArrayList<Member>();
        if(admin==null){
            list.add(null);
            return list;
        }else{
            List<Member> members = admin.getMembers();
            for (Member member : members) {
                list.add(member);
            }
            return list;
        }
    }

}
