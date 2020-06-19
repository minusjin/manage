package com.springboot.manage.service.impl;

import com.springboot.manage.bean.DataSourceModel;
import com.springboot.manage.bean.MeRecord;
import com.springboot.manage.dao.MeRecordDao;
import com.springboot.manage.service.DataService;
import com.springboot.manage.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    private MeRecordDao meRecordDao;

    @Override
    public List<MeRecord> findMeRecordByTime(String data) {
        return meRecordDao.findMeRecordByTime(data);
    }

    @Override
    public List<DataSourceModel> findMeRecordByType() {
        return meRecordDao.findMeRecordByType();
    }

    @Override
    public List<String> findAllDialeType() {
        return meRecordDao.findAllDialeType();
    }

    @Override
    public List<DataSourceModel> findMeRecordByTT(String data) {
        return meRecordDao.findMeRecordByTT(data);
    }
}
