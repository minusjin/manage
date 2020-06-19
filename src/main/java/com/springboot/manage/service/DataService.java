package com.springboot.manage.service;

import com.springboot.manage.bean.DataSourceModel;
import com.springboot.manage.bean.MeRecord;

import java.util.List;

public interface DataService {
//
public List<MeRecord> findMeRecordByTime(String data);
//查询疾病分类情况
public List<DataSourceModel> findMeRecordByType();
//
    public List<String> findAllDialeType();

    //
    public List<DataSourceModel>  findMeRecordByTT(String data);
}
