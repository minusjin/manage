package com.springboot.manage.dao;

import com.springboot.manage.bean.Drug;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
药品持久层
 */
@Repository
public interface DrugDao {
    //药品总量
    @Select("select count(id) from drug")
    public int drugCount();

    //查询药品(分页)
    @Select("select *from drug limit #{page},#{limit}")
    public List<Drug> findAllDrug(int page,int limit);

    //搜索指定药品数量
    @Select("select count(id) from drug where drugname like #{drugname} and efficacy like #{efficacy} and categorize like #{categorize} ")
    public int drugNameCount(String drugname,String efficacy,String categorize);

    //搜索药品
    @Select("select *from drug where drugname like #{drugname} and efficacy like #{efficacy} and categorize like #{categorize} limit #{page},#{limit}")
    public List<Drug> findAllDrugName(int page,int limit,String drugname,String efficacy,String categorize);

    //添加药品
    @Insert("insert into drug(drugname,efficacy,medprope,medtaste,categorize,amount,price) values" +
            "(#{drugname},#{efficacy},#{medprope},#{medtaste}," +
            "#{categorize},#{amount},#{price})")
    public int addDrug(Drug drug);

    //更新药品(动态指定字段名称)
    @Update("update drug set ${key}=#{value} where id=#{id}")
    public int updateDrug(Integer id,String key,String value);

    //删除药品
    @Delete("delete from drug where id=#{id}")
    public int deleteDrug(Integer id);


    //查询药品价格信息
    @Select("select * from drug")
    public List<Drug> findAllDrugPrice();



}
