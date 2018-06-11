package com.baizhi.service;

import com.baizhi.dao.ChaptorDAO;
import com.baizhi.entity.Chaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChaptorServiceImpl implements ChaptorService{
    @Autowired
    private ChaptorDAO chaptorDAO;
    @Override
    public void insert(Chaptor chaptor) {
        chaptorDAO.save(chaptor);
    }
}
