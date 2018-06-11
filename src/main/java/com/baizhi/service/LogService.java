package com.baizhi.service;

import com.baizhi.entity.Log;

import java.util.List;

public interface LogService {
    public void insert(Log log);

    public List<Log> selAll();
}
