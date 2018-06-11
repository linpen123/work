package com.baizhi.entity;

import com.baizhi.controller.UserAnnotation;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    @UserAnnotation(name="编号")
    private int id;
    @UserAnnotation(name="姓名")
    private String username;
    @UserAnnotation(name="密码")
    private String password;
    private String dharmaName;//佛法名称
    private boolean sex;
    private String headpic;
    private String status;
    @UserAnnotation(name="日期")
    private Date date;
    @UserAnnotation(name="省份")
    private String province;
    @UserAnnotation(name="城市")
    private String city;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDharmaName() {
        return dharmaName;
    }

    public void setDharmaName(String dharmaName) {
        this.dharmaName = dharmaName;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dharmaName='" + dharmaName + '\'' +
                ", sex=" + sex +
                ", headpic='" + headpic + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}