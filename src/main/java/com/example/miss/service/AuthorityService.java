package com.example.miss.service;

import com.example.miss.model.Authority;

import java.util.List;

public interface AuthorityService {
    //根据角色id查询角色所有的权限
    List<Authority> findRolePremission(String role_key);

    //按menu_code查询出一条权限信息
    Authority findPremissinService(String menuCode);

    //查询结果的每页的数据,分页用
    List<Authority> permissionPageList(int page, int limit, String dataUrl);

    //查询结果的总数，分页用
    int permissionCount(String dataUrl);

    //添加权限信息
    void permissionAddService(Authority authority);

    //删除一条权限信息
    void deletePService(String menuCode);

    //修改权限信息
    void alterPService(Authority authority);

    //查询全部权限
    List<Authority> findAllPermissionService();
}
