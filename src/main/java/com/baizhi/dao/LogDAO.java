package com.baizhi.dao;

import com.baizhi.entity.Log;

import java.util.List;

public interface LogDAO {
    public void save(Log log);

    public List<Log> queryAll();

}
