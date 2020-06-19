package com.springboot.manage.service.impl;

import com.springboot.manage.bean.MeRecord;
import com.springboot.manage.dao.MeRecordDao;
import com.springboot.manage.service.MeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeRecordServiceImpl  implements MeRecordService {
@Autowired
    MeRecordDao meRecordDao;
    @Override
    public int meRecordCount() {
        return meRecordDao.meRecordCount();
    }

    @Override
    public List<MeRecord> findAllMeRecord(Integer page, Integer limit) {
        return meRecordDao.findAllMeRecord(page, limit);
    }

    @Override
    public int meRecordSearchCount(String name, String time) {
        return meRecordDao.meRecordSearchCount(name, time);
    }

    @Override
    public List<MeRecord> findAllSearch(int page, int limit, String name, String time) {
        return meRecordDao.findAllSearch(page, limit, name, time);
    }

    @Override
    public MeRecord findMeRecordById(String id) {
        return meRecordDao.findMeRecordById(Integer.valueOf(id));
    }

    @Override
    public int addMeRecord(MeRecord meRecord) {
        return meRecordDao.addMeRecord(meRecord);
    }

    @Override
    public int updateMeRecord(MeRecord meRecord) {
        return meRecordDao.updateMeRecord(meRecord);
    }

    @Override
    public int deleteMeRecord(Integer id) {
        return meRecordDao.deleteMeRecord(id);
    }
}
