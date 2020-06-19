package com.springboot.manage.service.impl;

import com.springboot.manage.bean.User;
import com.springboot.manage.dao.UserDao;
import com.springboot.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    //登录
    @Override
    public User login(String  account) {
        User user = userDao.findUser(account);
        return user;
    }
    //注册
    @Override
    public int insertUser(String account, String password) {
        return userDao.insertUser(account, password);
    }
//找回密码
    @Override
    public int updatepass(String account, String password) {
        return userDao.updatePass(account, password);
    }
}
