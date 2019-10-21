package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDao {

    @Select(value="select * from user")
    public List<User> queryUserList();

    @Select(value="select * from user where id=#{id}")
    User findById(Integer id);

    @Update(value="update user set username=#{username},password = #{password},name=#{name} where id=#{id}")
    void update(User user);
}
