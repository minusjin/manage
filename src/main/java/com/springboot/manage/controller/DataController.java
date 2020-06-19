package com.springboot.manage.controller;

import com.springboot.manage.bean.DataSourceModel;
import com.springboot.manage.bean.DataWeekModel;
import com.springboot.manage.bean.MeRecord;
import com.springboot.manage.service.DataService;
import com.springboot.manage.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 负责处理数据图表请求
 * @Author: jpj
 * @Date: 2020/6/7
 */
@Controller
public class DataController {

@Autowired
    private DataService dataService;
    @Autowired
    private TimeUtil timeUtil;
//周接诊人次
    @GetMapping("/data/usertonday")
    @ResponseBody
    public Map<String,Object> userTondayChart(){
        Map<String, Object> model = new HashMap<>();
        List<String> weekTime = timeUtil.getWeekTime();
        List<Integer> nums = new ArrayList<>();
        for (String data : weekTime){
            data = "%"+data+"%";
            List<MeRecord> meRecordByTime = dataService.findMeRecordByTime(data);
            if (meRecordByTime!=null){
                nums.add(meRecordByTime.size());
            }else {
                nums.add(0);
            }
        }
        model.put("code","200");
        model.put("nums",nums);
        return model;
    }

    //病症百分比
    @GetMapping("/data/usersource")
    @ResponseBody
    public Map<String ,Object> userSourceChart(){
        Map<String, Object> model = new HashMap<>();
        List<DataSourceModel> dataSourceModels = dataService.findMeRecordByType();
        List<DataSourceModel> source = new ArrayList<>();
        if (dataSourceModels!=null){
            for (DataSourceModel dataSourceModel :dataSourceModels){
                if (!StringUtils.isEmpty(dataSourceModel.getName())&& dataSourceModel!=null){
                    source.add(dataSourceModel);
                }
            }
            model.put("nums",source);
        }
        return model;
    }

//接诊具体情况
    @GetMapping("/data/userweeksource")
    @ResponseBody
    public Map<String ,Object> userWeekSourceChart(){
        Map<String, Object> model = new HashMap<>();
        //查出有多少个病症
        List<String> namelist = dataService.findAllDialeType();
        List<String> names = new ArrayList<>();
        for (String name :namelist){
            if (name!=null&&!StringUtils.isEmpty(name)){
                names.add(name);
            }
        }


        if (names != null && names.size()!=0){
            List<DataWeekModel> dataWeekModel = new ArrayList<>();
            for (String  name : names){
                DataWeekModel dataWeekModels = new DataWeekModel();
                dataWeekModels.setName(name);
                dataWeekModel.add(dataWeekModels);
            }
            //获得日期
            List<String> datas = timeUtil.getWeekTime();
            for (String data : datas){
                data="%"+data+"%";
                List<DataSourceModel> dataSourceModels = dataService.findMeRecordByTT(data);
                for (DataWeekModel dataWeekModels : dataWeekModel){
                    int flag =0;
                    for (DataSourceModel dataSourceModel : dataSourceModels){
                        if (dataSourceModel.getName().equals(dataWeekModels.getName())){
                            dataWeekModels.getValue().add(Integer.valueOf(dataSourceModel.getValue()));
                            flag = 1;
                            break;
                        }
                    }

                    if (flag == 0){
                        dataWeekModels.getValue().add(0);
                    }
                    flag = 0;
                }
            }
            model.put("code",200);
            model.put("names",names);
            model.put("nums",dataWeekModel);
        }else {
            model.put("code",200);
        }


        return model;
    }


}
