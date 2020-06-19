package com.springboot.manage.dao;

import com.springboot.manage.bean.FamRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
医案持久层
 */
@Repository
public interface FamRecordDao {

    //总记录数
    @Select("select count(id) from famrecord")
    public int famRecordCount();

    //查询所有医疗记录
    @Select("select *from famrecord limit #{page},#{limit}")
    public List<FamRecord> findAllFamRecord(Integer page, Integer limit);

    //搜索医疗记录

    //搜索指定名称时间数量
    @Select("select count(id) from famrecord where zdiagnosis like #{zdiagnosis} and dialetype like #{dialetype}")
    public int famRecordSearchCount(String zdiagnosis,String dialetype);

    //搜索名称时间
    @Select("select *from famrecord where zdiagnosis like #{zdiagnosis} and dialetype like #{dialetype} limit #{page},#{limit}")
    public List<FamRecord> findAllSearch(int page, int limit, String zdiagnosis,String dialetype);

    //id查询
    @Select("select *from famrecord where id=#{id}")
    public FamRecord findFamRecordById(Integer id);

    //插入医案
    @Insert("insert into famrecord(name,sex,age,frequ,chcom,symptom,zdiagnosis,xdiagnosis,tonfur,pulse,dialetype,cercate,rule,drugs,count,prescription) values " +
            "(#{name},#{sex},#{age},#{frequ},#{chcom},#{symptom},#{zdiagnosis},#{xdiagnosis},#{tonfur},#{pulse},#{dialetype},#{cercate},#{rule},#{drugs},#{count},#{prescription})")
    public int addFamRecord(FamRecord famRecord);

    //删除医案
    @Delete("delete from famrecord where id=#{id}")
    public int deleteFamRecord(Integer id);

    //更新医案
    @Update("update famrecord set name=#{name},sex=#{sex},age=#{age},frequ=#{frequ},chcom=#{chcom}," +
            "symptom=#{symptom},zdiagnosis=#{zdiagnosis},xdiagnosis=#{xdiagnosis},tonfur=#{tonfur}," +
            "pulse=#{pulse},dialetype=#{dialetype},cercate=#{cercate},rule=#{rule},drugs=#{drugs}" +
            ",count=#{count},prescription=#{prescription}" +
            " where id=#{id}")
    public int updateFamRecord(FamRecord famRecord);
}
