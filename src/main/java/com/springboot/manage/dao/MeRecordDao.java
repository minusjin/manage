package com.springboot.manage.dao;

import com.springboot.manage.bean.DataSourceModel;
import com.springboot.manage.bean.MeRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
医疗记录和今日诊疗
 */
@Repository
public interface MeRecordDao {
    //总记录数
    @Select("select count(id) from merecord")
    public int meRecordCount();

    //查询所有医疗记录
    @Select("select *from merecord limit #{page},#{limit}")
    public List<MeRecord> findAllMeRecord(Integer page,Integer limit);

    //搜索指定名称时间数量
    @Select("select count(id) from merecord where name like #{name} and time like #{time}")
    public int meRecordSearchCount(String name,String time);

    //搜索名称时间
    @Select("select *from merecord where name like #{name} and time like #{time} limit #{page},#{limit}")
    public List<MeRecord> findAllSearch(int page, int limit, String name,String time);

    //id查询
    @Select("select *from merecord where id=#{id}")
    public MeRecord findMeRecordById(Integer id);

    //添加诊疗记录
    @Insert("insert into merecord(name,sex,age,frequ,chcom,symptom,zdiagnosis,xdiagnosis," +
            "tonfur,pulse,dialetype,cercate,rule,drugs,count,prescription,dockername,time,price,userid) values " +
            "(#{name},#{sex},#{age},#{frequ},#{chcom},#{symptom},#{zdiagnosis},#{xdiagnosis},#{tonfur},#{pulse},#{dialetype},#{cercate},#{rule},#{drugs},#{count},#{prescription},#{dockername},#{time},#{price},#{userid})")
    public int addMeRecord(MeRecord meRecord);

    //更新诊疗记录
    @Update("update merecord set name=#{name},sex=#{sex},age=#{age},frequ=#{frequ},chcom=#{chcom}," +
            "symptom=#{symptom},zdiagnosis=#{zdiagnosis},xdiagnosis=#{xdiagnosis},tonfur=#{tonfur}," +
            "pulse=#{pulse},dialetype=#{dialetype},cercate=#{cercate},rule=#{rule},drugs=#{drugs}" +
            ",count=#{count},prescription=#{prescription},dockername=#{dockername},time=#{time},price=#{price}" +
            " where id=#{id}")
    public int updateMeRecord(MeRecord meRecord);


    //查询指定日期的所有记录
    @Select("select * from merecord where time like #{time}")
    public List<MeRecord> findMeRecordByTime(String time);

    //查询疾病分类情况
    @Select("select dialetype as name,count(id) as value from merecord group by dialetype ")
    public List<DataSourceModel> findMeRecordByType();

    //按日期统计疾病占比
    @Select("select dialetype as name,count(id) as value from merecord where time like #{time} group by dialetype")
    public List<DataSourceModel> findMeRecordByTT(String time);

    //所有病情的分类
    @Select("select dialetype as name from merecord group by dialetype")
    public List<String> findAllDialeType();

    //删除医案
    @Delete("delete from merecord where id=#{id}")
    public int deleteMeRecord(Integer id);

}
