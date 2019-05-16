package com.example.miss.mapper;

import com.example.miss.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    //按用户名查询出用户信
    User findUser(String username);

    //查询出一页用户数据
    List<User> findPageUser(int index, int limit, String name);

    //查询用户总数
    int findUserConunt(@Param(value = "name") String name);

    //按id查询出一条用户信息
    User findUserById(int userid);


    //修改用户信息
    void updateUser(User user);

    //删除用户
    void deleteUser(int userid);

    //按员工姓名查询员工信息
    List<User> selectUser(String name);

    //用于返回上面查询结果的总数
    int selectUserCount(String name);

}