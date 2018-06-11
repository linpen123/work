package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;

public interface UserService {
    //用户登录
    public boolean login(String name,String password);
    public User selByNameAndPassword(String name,String passwords);

    //查看用户保持记录
    public List<Integer> selAll();

    public List<User> query();
    public void insert(User user);

    public User selById(int id);
    public boolean updateById(User user);
}
