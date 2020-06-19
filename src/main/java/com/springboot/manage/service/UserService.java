package com.springboot.manage.service;

import com.springboot.manage.bean.User;

/**
 * @Description: 用户服务层接口
 * @Author: jpj
 * @Date: 2020/6/6
 */
public interface UserService {
    //登录
    public User login(String account);
    //注册
    public int insertUser(String account,String password);
    //找回密码
    public int updatepass(String account,String password);
}
