package com.springboot.manage.service;

import com.springboot.manage.bean.FamRecord;
import org.springframework.ui.Model;

import java.util.List;

/**
 * @Description:  医案服务接口
 * @Author: jpj
 * @Date: 2020/6/8
 */
public interface FarmRecordService {


    //总记录数
    public int famRecordCount();

    //查询所有医疗记录
    public List<FamRecord> findAllFamRecord(Integer page, Integer limit);

    //搜索指定名称时间数量
    public int famRecordSearchCount(String zdiagnosis,String dialetype);

    //搜索名称时间
    public List<FamRecord> findAllSearch(int page, int limit, String zdiagnosis,String dialetype);

    //id查询
    public FamRecord findFamRecordById(Integer id);

    //插入医案
    public int addFamRecord(FamRecord famRecord);

    //删除医案
    public int deleteFamRecord(Integer id);

    //更新医案
    public int updateFamRecord(FamRecord famRecord);

   //处方对比
    public FamRecord findCompare(String zdiagnosis, String dialetype, String drugs, String page,Model model) ;


    }
