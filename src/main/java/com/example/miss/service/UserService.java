package com.example.miss.service;

import com.example.miss.model.User;

import java.util.List;

public interface UserService {
    //按用户名查询用户信息
    User findUser(String username);


    //    得到用户分页后的数据
    List<User> findPageUserService(int page, int limit, String name);

    //得到用户总数
    int findUserCount(String name);

    //通过id查询出一条用户信息
    User findUserById(int userid);


    //修改用户信息
    void updateUserService(User user);

    //插入一条用户信息
    void addUserService(User user);

    //删除用户
    void deleteUserService(int userid);

    //按员工姓名查询出一条员工信息
    List<User> selectUserService(String name);

    //用于返回上面查询出的总数
    int selectUserCountService(String name);
}
