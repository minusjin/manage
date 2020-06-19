package com.springboot.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面映射
 */

@Controller
public class PageController {

    //跳转到主页
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    //数据窗口
    @GetMapping("/index/console")
    public String console(){
        return "pages/console";
    }

    //弹窗动画
    @GetMapping("/system/alertSkin")
    public String alertSkin(){
        return "pages/system/alertSkin";
    }

    /**
     * 用户信息模块
     */
    //跳转到登录页面
    @GetMapping("/login")
    public String login(){
        return "pages/login";
    }
    //注册页面
    @GetMapping("/regist")
    public String regist(){
        return "pages/regist";
    }
    //忘记密码
    @GetMapping("/forget")
    public String forget(){
        return "pages/forget";
    }
    //用户信息
    @GetMapping("/user/userpwd")
    public String userInfo(){return "pages/user/user-pwd";}
    /**
     * 诊疗信息模块
     */
    //1.今日诊疗
      //诊疗列表
    @GetMapping("/tonpatient/list")
    public String patientList(){
        return "pages/patient/patient";
    }
    //添加诊疗记录
    @GetMapping("/tonpatient/add")
    public String partientAdd(){
        return "pages/patient/patient-add";
    }
  //2.诊疗记录列表
  @GetMapping("/mdfile/list")
  public String mdfileList(){
        return "pages/file/mdfilelist";
  }

    /**
     * 医案模块
     */
    //医案列表
    @GetMapping("/famouns/recordlist")
    public String famRecordList(){
        return "pages/record/famousrecord";
    }
    //医案添加
    @GetMapping("/famous/recordadd")
    public String famousRecordAdd(){
        return "pages/record/f-add";
    }
    /**
     * 药品模块
     */
    //药品列表页
    @GetMapping("/drug/list")
    public String druglist(){
        return "pages/file/druglist";
    }
    //药品添加页
    @GetMapping("/drug/add")
    public String drugadd(){
        return "pages/file/drugadd";
    }

}
