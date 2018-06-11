package com.baizhi.service;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;
    @Override
    //用户登录
    public boolean login(String name, String password) {
        User user = userDAO.queryByNameOrPassword(name, password);
        if(user!=null){
            return true;
        }else return false;
    }

    @Override
    public User selByNameAndPassword(String name, String passwords) {
        return userDAO.queryByNameOrPassword(name,passwords);
    }

    @Override
    public List<Integer> selAll() {
        return userDAO.queryAll();
    }

    @Override
    public List<User> query() {
        return userDAO.showAll();
    }

    @Override
    public void insert(User user) {
        userDAO.save(user);
    }

    @Override
    public User selById(int id) {
        return userDAO.queryById(id);
    }

    @Override
    public boolean updateById(User user) {
        try {
            userDAO.update(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
