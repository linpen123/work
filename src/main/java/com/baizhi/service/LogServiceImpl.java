package com.baizhi.service;

import com.baizhi.dao.LogDAO;
import com.baizhi.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDAO logDAO;
    @Override
    public void insert(Log log) {
        logDAO.save(log);
    }

    @Override
    public List<Log> selAll() {
        return logDAO.queryAll();
    }
}
