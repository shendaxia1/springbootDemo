package com.example.miss.service.Impl;

import com.example.miss.mapper.UserMapper;
import com.example.miss.model.User;
import com.example.miss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询每页的用户数据
     *
     * @param page 前端传回来的数据
     * @return
     */
    @Override
    public List<User> findPageUserService(int page, int limit, String name) {
        int index = (page - 1) * limit;
        return userMapper.findPageUser(index, limit, name);
    }

    /**
     * 查询单条用户
     *
     * @param username
     * @return
     */
    @Override
    public User findUser(String username) {
        return userMapper.findUser(username);
    }

    /**
     * 查询用户总数，做分页用
     *
     * @return
     */
    @Override
    public int findUserCount(String name) {
        return userMapper.findUserConunt(name);
    }

    /**
     * 通过用户id查询出一条用户信息
     *
     * @param userid
     * @return
     */
    @Override
    public User findUserById(int userid) {
        return userMapper.findUserById(userid);
    }


    /**
     * 修改用户信息
     *
     * @param user 用户id
     */
    @Override
    public void updateUserService(User user) {
        userMapper.updateUser(user);
    }

    /**
     * 添加用户
     *
     * @param user
     */
    @Override
    public void addUserService(User user) {
        userMapper.insert(user);

    }

    /**
     * 删除员工
     *
     * @param userid 员工id
     */
    @Override
    public void deleteUserService(int userid) {
        userMapper.deleteUser(userid);
    }

    /**
     * 按姓名查询员工
     *
     * @param name
     * @return
     */
    @Override
    public List<User> selectUserService(String name) {
        return userMapper.selectUser(name);
    }

    @Override
    public int selectUserCountService(String name) {
        return userMapper.selectUserCount(name);
    }
}
