package com.example.miss.mapper;

import com.example.miss.model.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthorityMapper {
    int insert(Authority record);

    int insertSelective(Authority record);

    //按角色id查询出所有权限
    List<Authority> findRolePremission(String role_key);

    //按menu_code查询出一条权限信息
    Authority findPremission(String menuCode);

    //查询出分页每一页的数据
    List<Authority> permissionPageList(int index, int limit, String dataUrl);

    //查询结果的总数
    int permissionCount(@Param(value = "dataUrl") String dataUrl);

    //添加权限
    void permissionAdd(Authority authority);

    //删除一条权限
    void deleteP(String menuCode);

    //修改权限信息
    void alterP(Authority authority);

    //查询出所有的权限信息
    List<Authority> findAllPermission();
}