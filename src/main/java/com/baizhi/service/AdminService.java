package com.baizhi.service;

import com.baizhi.entity.Admin;

public interface AdminService {
    public boolean insert(Admin admin);
    public Admin selByPhoneNum(String phoneNum);
    public Admin selById(String id);
}
