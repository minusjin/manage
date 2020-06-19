package com.springboot.manage.controller;

import com.springboot.manage.bean.FamRecord;
import com.springboot.manage.service.FarmRecordService;
import com.springboot.manage.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FamRecordController {
    @Autowired
    FarmRecordService farmRecordService;
    @Autowired
    PageUtil pageUtil;

    //医案列表
    @GetMapping("/fmRecordList")
    @ResponseBody
    public Map<String ,Object> famRecordList(String page,String limit,String zdiagnosis,String dialetype ){
        Map<String, Object> model = new HashMap<>();
        int count = 0;
        List<FamRecord> famRecordList = new ArrayList<>();
        //调用角标算法
        Integer[] pageindex = pageUtil.pageindex(page, limit);
        //检索方式判断
        if ((zdiagnosis==null|| StringUtils.isEmpty(zdiagnosis))&&(dialetype==null||StringUtils.isEmpty(dialetype))){
            count = farmRecordService.famRecordCount();
            famRecordList = farmRecordService.findAllFamRecord(pageindex[0],pageindex[1]);
        }else {
            if (zdiagnosis!=null && !StringUtils.isEmpty(zdiagnosis)){
                zdiagnosis="%"+zdiagnosis+"%";
            }else {
                zdiagnosis = "%";
            }
            if (dialetype!=null&& !StringUtils.isEmpty(dialetype)){
                dialetype="%"+dialetype+"%";
            }else {
                dialetype = "%";
            }

            count = farmRecordService.famRecordSearchCount(zdiagnosis, dialetype);
            famRecordList = farmRecordService.findAllSearch(pageindex[0],pageindex[1],zdiagnosis,dialetype);
        }
         model.put("code",0);
        model.put("count",String.valueOf(count));
        model.put("data",famRecordList);
        return model;
    }


    //医案添加
    @GetMapping("/famrecordadd")
    @ResponseBody
    public Map<String ,Object> famRecordadd(FamRecord famRecord){
        Map<String, Object> model = new HashMap<>();
        int addflag = farmRecordService.addFamRecord(famRecord);
        if (addflag == 1){
            model.put("code",200);
            model.put("msg","添加医案成功");
        }else {
            model.put("code",1);
            model.put("msg","添加案例失败");
        }

        return model;
    }
    //医案更新
    @GetMapping("/famRecordupdate")
    @ResponseBody
    public Map<String ,Object> famRecordupdate(FamRecord famRecord){
        Map<String, Object> model = new HashMap<>();
        int updateFamRecord = farmRecordService.updateFamRecord(famRecord);
        if (updateFamRecord ==1){
            model.put("code",200);
            model.put("msg","更新医案成功");
        }else {
            model.put("code",1);
            model.put("msg","更新失败");
        }

        return model;
    }
    //医案删除
    @GetMapping("/famrecorddelete")
    @ResponseBody
    public Map<String ,Object> famRecorddelete(String[] idsStr){
        Map<String, Object> model = new HashMap<>();
        int count = 0;
        String notdelet = "";
        int length=0;
        for (String s : idsStr){
            if (s!=null&& StringUtils.isEmpty(s)==false){
                int deleteflag = farmRecordService.deleteFamRecord(Integer.valueOf(s));
                count = count+deleteflag;
                length++;
                if (deleteflag==0){
                    notdelet=notdelet+s;
                }
            }
        }

        if (count==length){
            model.put("code",0);
            model.put("msg","删除成功");
        }else {
            model.put("code",1);
            model.put("msg","删除"+notdelet+"失败");
        }

        return model;
    }

    //医案详情展示
    @GetMapping("/fmrecord/detail")
    public String fmRecordDetail(String id, Model model ){
        FamRecord famRecord = farmRecordService.findFamRecordById(Integer.valueOf(id));
        model.addAttribute("detail",famRecord);
        return "pages/record/f-edit";
    }




}
