package com.springboot.manage.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.springboot.manage.bean.User;
import com.springboot.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户响应层
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    //登录
    @PostMapping("/loginpost")
    @ResponseBody
    public Map<String,Object> logintest(String account, String password, HttpSession session){


        Map<String, Object> model = new HashMap<>();
        User user = userService.login(account);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                session.setAttribute("user", user);
                model.put("code", "0");
                model.put("msg", "登录成功");
            } else {
                model.put("code", "1");
                model.put("msg", "密码错误");
            }
        }  else {
            model.put("code","1");
            model.put("msg","用户名不存在");
        }
        return model;
    }


    //注册
    @PostMapping("/registpost")
    @ResponseBody
    public Map<String,Object> regist(String account,String password){
        Map<String,Object> model = new HashMap<>();
        User user = userService.login(account);
        if (user != null) {
            model.put("code","1");
            model.put("msg","账号已经注册");
        }else {
            int flag = userService.insertUser(account, password);
            if (flag == 1){
                model.put("code","0");
                model.put("msg","注册成功");
            }else {

                model.put("code","1");
                model.put("msg","注册失败");
            }
        }
        return model;
    }

    //找回密码
    @PostMapping("/forgetpost")
    @ResponseBody
    public Map<String, Object> forget(String account, String password){
        Map<String,Object> model = new HashMap<>();
        int updatepflag = userService.updatepass(account, password);
        if (updatepflag==1){
            model.put("code","0");
            model.put("msg","重置成功");
        }else {
            model.put("code","1");
            model.put("msg","重置失败");
        }
        return model;


    }

}
