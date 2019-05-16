package com.example.miss.service.Impl;

import com.example.miss.mapper.AuthorityMapper;
import com.example.miss.model.Authority;
import com.example.miss.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityMapper authorityMapper;

    /**
     * 根据角色id查询出角色所有的权限
     *
     * @param role_key
     * @return
     */
    @Override
    public List<Authority> findRolePremission(String role_key) {
        return authorityMapper.findRolePremission(role_key);
    }

    /**
     * 根据menu_code查询出一条权限信息
     *
     * @param menuCode
     * @return
     */
    @Override
    public Authority findPremissinService(String menuCode) {
        return authorityMapper.findPremission(menuCode);
    }

    /**
     * 查询结果的每页的数据
     *
     * @param page    第几页
     * @param limit   每页显示几条
     * @param dataUrl 权限，用于查询
     * @return
     */
    @Override
    public List<Authority> permissionPageList(int page, int limit, String dataUrl) {
        int index = (page - 1) * limit;
        return authorityMapper.permissionPageList(index, limit, dataUrl);
    }

    /**
     * 查询结果的总数，分页用
     *
     * @param dataUrl 权限，查询用
     * @return
     */
    @Override
    public int permissionCount(String dataUrl) {
        return authorityMapper.permissionCount(dataUrl);
    }

    /**
     * 添加一条权限信息
     *
     * @param authority
     */
    @Override
    public void permissionAddService(Authority authority) {
        authorityMapper.permissionAdd(authority);

    }

    /**
     * 删除一条权限信息
     *
     * @param menuCode
     */
    @Override
    public void deletePService(String menuCode) {
        authorityMapper.deleteP(menuCode);
    }

    /**
     * 修改一条权限信息
     *
     * @param authority
     */
    @Override
    public void alterPService(Authority authority) {
        authorityMapper.alterP(authority);
    }

    @Override
    public List<Authority> findAllPermissionService() {
        return authorityMapper.findAllPermission();
    }
}
