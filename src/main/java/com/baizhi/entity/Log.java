package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
    private String id;
    private String name;
    private Date setTime;
    private String status;
    private String setWhy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getSetTime() {
        return setTime;
    }

    public void setSetTime(Date setTime) {
        this.setTime = setTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSetWhy() {
        return setWhy;
    }

    public void setSetWhy(String setWhy) {
        this.setWhy = setWhy;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", setTime=" + setTime +
                ", status='" + status + '\'' +
                ", setWhy='" + setWhy + '\'' +
                '}';
    }
}
