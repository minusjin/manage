package com.springboot.manage.service;

import com.springboot.manage.bean.MeRecord;

import java.util.List;

/**
 * @Description: 诊疗记录服务层接口
 * @Author: jpj
 * @Date: 2020/6/8
 */
public interface MeRecordService {
//查询检索所有的医疗档案
    //总记录数
    public int meRecordCount();
    //查询所有医疗记录
    public List<MeRecord> findAllMeRecord(Integer page, Integer limit);
    //搜索指定名称时间数量
    public int meRecordSearchCount(String name,String time);
    //搜索名称时间
    public List<MeRecord> findAllSearch(int page, int limit, String name,String time);
    //id查询
    public MeRecord findMeRecordById(String id);
    //添加诊疗记录
    public int addMeRecord(MeRecord meRecord);
    //更新诊疗记录
    public int updateMeRecord(MeRecord meRecord);
    //删除记录
    public int deleteMeRecord(Integer id);

}
