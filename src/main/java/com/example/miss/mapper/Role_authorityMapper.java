package com.example.miss.mapper;

import com.example.miss.model.Role_authority;

public interface Role_authorityMapper {
    int insert(Role_authority record);

    int insertSelective(Role_authority record);
//    查询一条角色权限

    Role_authority findRolePermission(String menuCode, String roleKey);
}