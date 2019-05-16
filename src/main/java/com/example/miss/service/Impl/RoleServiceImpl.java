package com.example.miss.service.Impl;

import com.example.miss.mapper.RoleMapper;
import com.example.miss.mapper.Role_authorityMapper;
import com.example.miss.mapper.User_roleMapper;
import com.example.miss.model.Role;
import com.example.miss.model.Role_authority;
import com.example.miss.model.User_role;
import com.example.miss.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private Role_authorityMapper role_authorityMapper;
    @Autowired
    private User_roleMapper user_roleMapper;

    /**
     * 根据用户id查询出用户所有的角色
     *
     * @param userid
     * @return
     */
    @Override
    public List<Role> findUserRole(int userid) {
        return roleMapper.findUserRole(userid);
    }

    @Override
    public Role findRoleService(String token) {
        return roleMapper.findRole(token);
    }

    @Override
    public List<Role> rolePageListService(int page, int limit, String roleValue) {
        int index = (page - 1) * limit;
        return roleMapper.rolePageList(index, limit, roleValue);
    }

    @Override
    public int roleCountService(String roleValue) {
        return roleMapper.roleCount(roleValue);
    }

    @Override
    public void insertRoleService(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void updateRoleService(Role role) {
        roleMapper.updateRole(role);
    }

    @Override
    public void deleteRoleService(String roleKey) {
        roleMapper.deleteRole(roleKey);
    }

    @Override
    public void insertRolePService(Role_authority role_authority) {
        role_authorityMapper.insert(role_authority);
    }

    @Override
    public List<Role> findAllRoleService() {
        return roleMapper.findAllRole();
    }

    @Override
    public void insertRService(User_role user_role) {
        user_roleMapper.insert(user_role);
    }

    @Override
    public User_role findUserRoleService(int userid, String roleKey) {
        return user_roleMapper.findUserRole(userid, roleKey);
    }

    @Override
    public Role_authority findRolePermissionService(String menuCode, String roleKey) {
        return role_authorityMapper.findRolePermission(menuCode, roleKey);
    }
}
