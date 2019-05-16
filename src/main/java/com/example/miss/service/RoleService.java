package com.example.miss.service;

import com.example.miss.model.Role;
import com.example.miss.model.Role_authority;
import com.example.miss.model.User_role;

import java.util.List;

public interface RoleService {

    //通过userid查询出用户的所有角色,shiro用
    List<Role> findUserRole(int userid);

    //通过userid查询出用户所有的角色，前端动态生成路由用
    Role findRoleService(String token);

    //角色列表出每页数据
    List<Role> rolePageListService(int index, int limit, String roleValue);

    //查询出结果的总数
    int roleCountService(String roleValue);

    //添加角色service方法
    void insertRoleService(Role role);

    //修改角色信息
    void updateRoleService(Role role);

    //删除角色信息
    void deleteRoleService(String roleKey);

    //修改角色权限
    void insertRolePService(Role_authority role_authority);

    //查询所有的角色
    List<Role> findAllRoleService();

    //修改用户角色
    void insertRService(User_role user_role);

    //查询出一条user_role信息
    User_role findUserRoleService(int userid, String roleKey);

    //查询出一条角色权限信息
    Role_authority findRolePermissionService(String menuCode, String roleKey);

}
