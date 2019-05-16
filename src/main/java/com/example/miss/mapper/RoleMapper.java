package com.example.miss.mapper;

import com.example.miss.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    //插入角色信息
    int insert(Role record);

    int insertSelective(Role record);

    //按用户id查询出用户所有的角色，shiro用
    List<Role> findUserRole(int userid);

    //根据userid查询出对对应的角色，前端动态生成路由用
    Role findRole(String token);

    //查询出分页每一页的数据
    List<Role> rolePageList(int index, int limit, String roleValue);

    //查询结果的总数
    int roleCount(@Param(value = "roleValue") String roleValue);

    //修改角色信息
    void updateRole(Role role);

    //删除角色信息
    void deleteRole(String roleKey);

    //查询出所有的角色
    List<Role> findAllRole();

}