package com.baizhi.service;

import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO adminDAO;
    @Override
    public boolean insert(Admin admin) {
        try {
            adminDAO.save(admin);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Admin selByPhoneNum(String phoneNum) {
        return adminDAO.queryByPhoneNum(phoneNum);
    }

    @Override
    public Admin selById(String id) {
        return adminDAO.queryById(id);
    }
}
