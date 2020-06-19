package com.springboot.manage.service.impl;

import com.springboot.manage.bean.FamRecord;
import com.springboot.manage.dao.FamRecordDao;
import com.springboot.manage.service.FarmRecordService;
import com.springboot.manage.util.MdFileCompare;
import com.springboot.manage.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class FarmRecordServiceImpl implements FarmRecordService {
    @Autowired
    PageUtil pageUtil;
    @Autowired
    FamRecordDao famRecordDao;
    @Autowired
    MdFileCompare mdFileCompare;

    @Override
    public int famRecordCount() {
        return famRecordDao.famRecordCount();
    }

    @Override
    public List<FamRecord> findAllFamRecord(Integer page, Integer limit) {
        return famRecordDao.findAllFamRecord(page, limit);
    }

    @Override
    public int famRecordSearchCount(String zdiagnosis, String dialetype) {
        return famRecordDao.famRecordSearchCount(zdiagnosis, dialetype);
    }

    @Override
    public List<FamRecord> findAllSearch(int page, int limit, String zdiagnosis, String dialetype) {
        return famRecordDao.findAllSearch(page, limit, zdiagnosis, dialetype);
    }

    @Override
    public FamRecord findFamRecordById(Integer id) {
        return famRecordDao.findFamRecordById(id);
    }

    @Override
    public int addFamRecord(FamRecord famRecord) {
        return famRecordDao.addFamRecord(famRecord);
    }

    @Override
    public int deleteFamRecord(Integer id) {
        return famRecordDao.deleteFamRecord(id);
    }

    @Override
    public int updateFamRecord(FamRecord famRecord) {
        return famRecordDao.updateFamRecord(famRecord);
    }

    @Override
    public FamRecord findCompare(String zdiagnosis, String dialetype, String drugs, String page, Model model) {
        int count = 0;
        List<FamRecord> famRecordList = new ArrayList<>();
        //调用角标算法
        Integer[] pageindex = pageUtil.pageindex(page,"1");
        //检索方式的判断
        if ((zdiagnosis == null || StringUtils.isEmpty(zdiagnosis)) && (dialetype == null ||StringUtils.isEmpty(dialetype)
        )){
            count = famRecordDao.famRecordCount();
            famRecordDao.findAllFamRecord(pageindex[0],pageindex[1]);
        }else {
            if (zdiagnosis != null && !StringUtils.isEmpty(zdiagnosis)){
                zdiagnosis="%"+zdiagnosis+"%";
            }else {
                zdiagnosis="%";
            }
            if (dialetype!=null && !StringUtils.isEmpty(dialetype)){
                dialetype= "%"+dialetype+"%";
            }else {
                dialetype="%";
            }
            count=famRecordDao.famRecordSearchCount(zdiagnosis, dialetype);
            famRecordList= famRecordDao.findAllSearch(pageindex[0], pageindex[1], zdiagnosis, dialetype);
        }
        if (famRecordList.size()==0){
            model.addAttribute("countnum","0");
            model.addAttribute("pagenum","0");
            model.addAttribute("drugs",drugs);
            return null;
        }else {
            String[] strings = mdFileCompare.compare(famRecordList.get(0), drugs);
            famRecordList.get(0).setDrugs(strings[0]);
            model.addAttribute("countnum",count);
            model.addAttribute("pagenum",pageindex[0]+1);
            model.addAttribute("drugs",strings[1]);
            return famRecordList.get(0);
        }


    }
}
