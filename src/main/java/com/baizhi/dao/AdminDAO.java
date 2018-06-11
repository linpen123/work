package com.baizhi.dao;

import com.baizhi.entity.Admin;

public interface AdminDAO {
    void save(Admin admin);
    Admin queryByPhoneNum(String phoneNum);
    Admin queryById(String id);
}
