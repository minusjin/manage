package com.springboot.manage.service.impl;

import com.springboot.manage.bean.Drug;
import com.springboot.manage.dao.DrugDao;
import com.springboot.manage.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugServiceImpl  implements DrugService {
    @Autowired
    private DrugDao drugDao;

    @Override
    public int drugCount() {
        return drugDao.drugCount();
    }

    @Override
    public List<Drug> findAllDrug(int page, int limit) {
        return drugDao.findAllDrug(page, limit);
    }

    @Override
    public int drugNameCount(String drugname, String efficacy, String categorize) {
        return drugDao.drugNameCount(drugname, efficacy, categorize);
    }

    @Override
    public List<Drug> findAllDrugName(int page, int limit, String drugname, String efficacy, String categorize) {
        return drugDao.findAllDrugName(page, limit, drugname, efficacy, categorize);
    }

    @Override
    public int addDrug(Drug drug) {
        return drugDao.addDrug(drug);
    }

    @Override
    public int updateDrug(Integer id, String key, String value) {
        return drugDao.updateDrug(id, key, value);
    }

    @Override
    public int deleteDrug(Integer id) {
        return drugDao.deleteDrug(id);
    }

    @Override
    public List<Drug> findAllDrugPrice() {
        return drugDao.findAllDrugPrice();
    }
}
