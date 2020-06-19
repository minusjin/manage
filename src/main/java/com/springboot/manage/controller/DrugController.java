package com.springboot.manage.controller;

import com.springboot.manage.bean.Drug;
import com.springboot.manage.service.DrugService;
import com.springboot.manage.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 中药库处理层
 * @Author: jpj
 * @Date: 2020/6/9
 */
@Controller
public class DrugController {
    @Autowired
    DrugService drugService;
@Autowired
    private PageUtil pageUtil;
    //药品列表
    @GetMapping("/druglist")
    @ResponseBody
    public Map<String,Object> druglist(String page,String limit,String drugname,String efficacy,String categorize){
        Map<String,Object> model = new HashMap<>();
        int count = 0;
        List<Drug> drugList = new ArrayList<>();
        //调用角标算法
        Integer[] pageindex = pageUtil.pageindex(page, limit);
        //检索方式的判断
        if((drugname == null || StringUtils.isEmpty(drugname))&&(StringUtils.isEmpty(efficacy)||efficacy==null)&&(categorize ==null||StringUtils.isEmpty(categorize)) ){
            count = drugService.drugCount();
            drugList = drugService.findAllDrug(pageindex[0],pageindex[1]);
        }else {
            if (drugname!=null && !StringUtils.isEmpty(drugname)){
                drugname = "%"+drugname+"%";
            }else {
                drugname = "%";
            }
            if (efficacy!=null && !StringUtils.isEmpty(efficacy)){
                efficacy = "%" + efficacy+"%";
            }else {
                efficacy = "%";
            }
            if (categorize!=null&& !StringUtils.isEmpty(categorize)){
                categorize = "%"+categorize+"%";
            }else {
                categorize = "%";
            }
            count = drugService.drugNameCount(drugname, efficacy, categorize);
            drugList = drugService.findAllDrugName(pageindex[0],pageindex[1],drugname,efficacy,categorize);
        }
        model.put("code",0);
        model.put("count",String.valueOf(count));
        model.put("data",drugList);


        return model;
    }


//增加药品

    @GetMapping("/drugadd")
    @ResponseBody
    public Map<String,Object> drugadd(Drug drug){
        Map<String,Object> model = new HashMap<>();
        int addflag = drugService.addDrug(drug);
        if (addflag!=1){
            model.put("code",0);
            model.put("msg","添加失败");
        }else {
            model.put("code",0);
            model.put("msg","添加成功");
        }

        return model;
    }




//药品更新
    @GetMapping("/drugupdate")
    @ResponseBody
    public Map<String,Object> drugupdate(String id,String key,String value){
        Map<String,Object> model = new HashMap<>();
        int updateDrug = drugService.updateDrug(Integer.valueOf(id), key, value);
        if (updateDrug==1){
            model.put("code",0);
            model.put("msg","更新成功");
        }else {
            model.put("code",1);
            model.put("msg","更新失败");
        }
        return model;
    }



    //药品删除
    @GetMapping("/drugdelete")
    @ResponseBody
    public Map<String,Object> drugdelete(String[] idsStr ){
        Map<String,Object> model = new HashMap<>();
        int count = 0;
        String notdelet = "";
        int length = 0;
        for (String s : idsStr){
            if (StringUtils.isEmpty(s) == false && s!=null){
                int deleteDrug = drugService.deleteDrug(Integer.valueOf(s));
                count = count+deleteDrug;
                length++;
                if (deleteDrug==0){
                    notdelet = notdelet+s;
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

    //药品价格计算
    @GetMapping("/drugprice")
    @ResponseBody
    public Map<String,Object> drugprice(){
        Map<String,Object> model = new HashMap<>();
        List<Drug> drugPrice = drugService.findAllDrugPrice();
        if (drugPrice!=null&& drugPrice.size()!=0){
            model.put("code",200);
            model.put("msg","开启药品价格计算"+drugPrice);
        }else {
            model.put("code",1);
            model.put("msg","药品信息有误");
        }

        return model;
    }

}
