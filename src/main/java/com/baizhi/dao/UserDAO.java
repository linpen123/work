package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDAO {
//    注册
    public void save(User user);
//    登录
    public User queryByNameOrPassword(@Param("username")String username,@Param("password")String password);
//    活跃分布
    public List<Integer> queryAll();

    public List<User> showAll();
    public User queryById(int id);
    public void update(User user);

}
