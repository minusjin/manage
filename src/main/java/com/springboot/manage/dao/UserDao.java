package com.springboot.manage.dao;


import com.springboot.manage.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/*
用户信息
 */
@Repository
public interface UserDao {

    //查询用户信息
    @Select("select * from user where account=#{account}")
    public User findUser(String account);

    //更新密码和用户名
    @Update("update user set username=#{username},password=#{password} where id=#{id}")
    public int updateUser(String username,String password,Integer id);

    //插入数据
    @Insert("insert user(account,password) values(#{account},#{password})")
    public int insertUser(String account,String password);

    //更新密码
    @Update("update user set password=#{password} where account=#{account}")
    public int updatePass(String account,String password);


}
