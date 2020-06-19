package com.springboot.manage.controller;

import com.alibaba.druid.sql.PagerUtils;
import com.springboot.manage.bean.FamRecord;
import com.springboot.manage.bean.MeRecord;
import com.springboot.manage.bean.User;
import com.springboot.manage.service.FarmRecordService;
import com.springboot.manage.service.MeRecordService;
import com.springboot.manage.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.font.DelegatingShape;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 诊疗记录响应层
 * @Author: jpj
 * @Date: 2020/6/8 
 */
@Controller
public class MdFileController {
    @Autowired
    private MeRecordService meRecordService;
    @Autowired
    private FarmRecordService farmRecordService;
    @Autowired
    private PageUtil pageUtil;
    //诊疗列表
    @GetMapping("/mdfilelist")
    @ResponseBody
    public Map<String,Object> mdFilelist(String page,String limit, String name, String time){
        Map<String ,Object> model = new HashMap<>();
        int count = 0;
        List<MeRecord> meRecordList = new ArrayList<>();
        //调用角标算法
        Integer[] pageindex = pageUtil.pageindex(page, limit);
        //检索方式判断
        if (name == null || StringUtils.isEmpty(name) && (time==null || StringUtils.isEmpty(time))){
             count = meRecordService.meRecordCount();
           meRecordList =  meRecordService.findAllMeRecord(pageindex[0],pageindex[1]);
        }else {

            if (name!=null && !StringUtils.isEmpty(name)){
                name="%"+name+"%";
            }else {
                name = new String();
                name = "%";
            }
            if (time !=null && !StringUtils.isEmpty(time)){
                time = "%"+time.split("\\s+")[0]+"%";
            }else {
                time = new String();
                time = "%";
            }
            count = meRecordService.meRecordSearchCount(name, time);
            meRecordList = meRecordService.findAllSearch(pageindex[0],pageindex[1],name,time);
        }
        model.put("code",0);
        model.put("count",String.valueOf(count));
        model.put("data",meRecordList);
        return model;
    }


    //诊疗记录详细查看
    @GetMapping("/mdfile/detail")
    public String mdfile(String id, Model model){
        MeRecord recordById = meRecordService.findMeRecordById(id);
        model.addAttribute("detail",recordById);
        return "pages/file/mdfiledetail";
    }

    //今日诊疗
    @GetMapping("/tonpatlist")
    @ResponseBody
    public Map<String,Object> patientlist(String page, String limit, String name ,String time, HttpSession session){
        Map<String ,Object> model = new HashMap<>();
        int count = 0;
        List<MeRecord> meRecordList = new ArrayList<>();
        //调用角标算法
        Integer[] pageindex = pageUtil.pageindex(page, limit);
        //检索方式判断
        if ((name ==null || StringUtils.isEmpty(name)) &&(time == null ||StringUtils.isEmpty(time) )){
            meRecordList = meRecordService.findAllMeRecord(pageindex[0], pageindex[1]);
        }else {
            if (name != null && !StringUtils.isEmpty(name)){
                name = "%"+name+"%";
            }else {
                name = new String();
                name = "%";
            }
            if (time != null && !StringUtils.isEmpty(time)){
                time = "%"+time.split("\\s+")[0]+"%";
            }else {
                time = new String();
                time = "%";
            }
            meRecordList = meRecordService.findAllSearch(pageindex[0],pageindex[1],name,time);
        }
        User user = (User) session.getAttribute("user");
        List<MeRecord> meRecordListID = new ArrayList<>();

        for (MeRecord meRecord : meRecordList){
            if (user.getId()==meRecord.getUserid()){
                meRecordListID.add(meRecord);
            }
        }

        model.put("code",0);
        model.put("count",String.valueOf(meRecordListID.size()));
        model.put("data",meRecordListID);
        return model;
    }


//今日诊疗详细查看
    @GetMapping("/tonpatient/edit")
    public String test(String id,Model model){
        MeRecord meRecordById = meRecordService.findMeRecordById(id);
        model.addAttribute("detail",meRecordById);
        return "pages/patient/patient-edit";
    }

    //处方对比
    //首次点击
    @GetMapping("/tonpatient/compare")
    public void tonPanCompare(String zdiagnosis, String dialetype, String drugs, HttpSession session, HttpServletResponse response) throws IOException {
        session.setAttribute("zdsis",zdiagnosis);
        session.setAttribute("dialetype",dialetype);
        session.setAttribute("drugs",drugs);
        //开启处方页面重置
        session.setAttribute("relaxdrus","1");
        response.sendRedirect("/patient/compare");
    }

    @GetMapping("/patient/compare")
    public String compare(String page,Model model,HttpSession session){
        String zdsis = (String) session.getAttribute("zdsis");
        String dialetype = (String) session.getAttribute("dialetype");
        String drugs = (String) session.getAttribute("drugs");
        //开启处方页面重置
        session.setAttribute("relaxdrus","1");

        FamRecord famRecord = farmRecordService.findCompare(zdsis, dialetype, drugs, page, model);
        session.setAttribute("drugscmrsut",model.getAttribute("drugs"));
        model.addAttribute("detail", famRecord);
        return "pages/patient/compare";
    }

    @GetMapping("/drugs/compare")
    @ResponseBody
    public String drugUp(String drugs,HttpSession session){
        session.setAttribute("drugs",drugs);
        String flag = (String) session.getAttribute("relaxdrus");
        String drugscmrsut = (String) session.getAttribute("drugscmrsut");
        if (flag != null && flag.equals("1")){
            //开启处方页面重置
            session.setAttribute("relaxdrus","0");
            return drugscmrsut+"-*-"+"1";
        }
        return drugscmrsut+"-*-"+"1";
    }

//增加处方
@GetMapping("/tonpatiend/add")
@ResponseBody
    public Map<String,Object>  tonpanAdd(MeRecord meRecord,HttpSession session){
        Map<String,Object> model = new HashMap<>();
        //添加医生编号
    User user = (User) session.getAttribute("user");

    meRecord.setUserid(user.getId());
    int addflag = meRecordService.addMeRecord(meRecord);
    if (addflag ==1 ){
        model.put("code",200);
        model.put("msg","添加记录成功");
    }else {
        model.put("code",1);
        model.put("msg","添加失败");
    }
    return model;
}
    //修改处方
    @GetMapping("/tonpatiend/update")
    @ResponseBody
    public Map<String,Object> tonpanupdate(MeRecord meRecord){
        Map<String,Object> model = new HashMap<>();
        int updateflag = meRecordService.updateMeRecord(meRecord);
        if (updateflag==1){
            model.put("code",200);
            model.put("msg","更新记录成功");
        }else {
            model.put("code",1);
            model.put("msg","更新失败");
        }
        return model;
    }
    //删除处方
    @GetMapping("/merecorddelete")
    @ResponseBody
    public Map<String,Object> tonpandelete(String[] idsStr){
        Map<String,Object> model = new HashMap<>();
        int count = 0;
        String notdelet="";
        int length=0;
        for (String str :idsStr){
            if(StringUtils.isEmpty(str)==false&&str!=null){
                int deleteflag = meRecordService.deleteMeRecord(Integer.valueOf(str));
                count = count+deleteflag;
                length++;
                if (deleteflag==0){
                    notdelet=notdelet+str;
                }
            }
        }
        if (count==length){
            model.put("code",200);
            model.put("msg","删除成功");
        }else {
            model.put("code",1);
            model.put("msg","删除"+notdelet+"失败");
        }
        return model;
    }

}
