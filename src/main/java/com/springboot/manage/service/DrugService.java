package com.springboot.manage.service;

import com.springboot.manage.bean.Drug;

import java.util.List;

public interface DrugService {
    //药品总量
    public int drugCount();

    //查询药品(分页)
    public List<Drug> findAllDrug(int page, int limit);

    //搜索指定药品数量
    public int drugNameCount(String drugname,String efficacy,String categorize);

    //搜索药品
    public List<Drug> findAllDrugName(int page,int limit,String drugname,String efficacy,String categorize);

    //添加药品
    public int addDrug(Drug drug);

    //更新药品(动态指定字段名称)
    public int updateDrug(Integer id,String key,String value);

    //删除药品
    public int deleteDrug(Integer id);


    //查询药品价格信息
    public List<Drug> findAllDrugPrice();

}
