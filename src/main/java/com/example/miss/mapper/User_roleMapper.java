package com.example.miss.mapper;

import com.example.miss.model.User_role;

public interface User_roleMapper {
    int insert(User_role record);

    int insertSelective(User_role record);

    //按userid和roleKey查询出一条信息
    User_role findUserRole(int userid, String roleKey);
}