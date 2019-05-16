package com.example.miss.controller;

import com.example.miss.model.Authority;
import com.example.miss.model.Role;
import com.example.miss.model.Role_authority;
import com.example.miss.model.User_role;
import com.example.miss.service.AuthorityService;
import com.example.miss.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping(value = "/role")
@RestController
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthorityService authorityService;

    /**
     * 获取角色信息列表，如果roleValue有值就是查找
     *
     * @param page      页数，在service层要处理成index
     * @param limit     每页显示的数量
     * @param roleValue 角色名称
     * @throws IOException
     */
    @RequestMapping(value = "/roleList")
    public void roleList(int page, int limit, String roleValue) throws IOException {
        List<Role> list = roleService.rolePageListService(page, limit, roleValue);
        int total = roleService.roleCountService(roleValue);
        result.setData(list);
        result.setTotal(total);
        super.writeJSON(result);
        return;

    }

    @RequestMapping(value = "/roleAdd")
    public void roleAdd(Role role) throws IOException {
        if (role.getRoleValue() != null && !role.getRoleValue().equals("")) {
            String roleKey = UUID.randomUUID().toString().replace("-", "");
            role.setRoleKey(roleKey);
            roleService.insertRoleService(role);
            result.setSuccess(true);
            result.setMessage("添加成功");
            super.writeJSON(result);
            return;
        } else {
            result.setSuccess(false);
            result.setMessage("角色名不能为空");
            super.writeJSON(result);
            return;
        }
    }

    @RequestMapping(value = "/updateRole")
    public void updateRole(Role role) throws IOException {
        roleService.updateRoleService(role);
        result.setSuccess(true);
        result.setMessage("修改成功");
        super.writeJSON(result);
        return;
    }

    @RequestMapping(value = "/deleteRole")
    public void deleteRole(String roleKey) throws IOException {
        roleService.deleteRoleService(roleKey);
        result.setSuccess(true);
        result.setMessage("删除成功");
        writeJSON(result);
        return;
    }

    /**
     * 查询出所有的权限
     *
     * @throws IOException
     */
    @RequestMapping(value = "/findAllRole")
    public void findAllRole() throws IOException {
        List<Authority> list = authorityService.findAllPermissionService();
        result.setData(list);
        result.setSuccess(true);
        super.writeJSON(result);
        return;
    }

    /**
     * 修改角色权限
     */
    @RequestMapping(value = "/alterPermission")
    public void alterPermission(String roleKey, String check) throws IOException {
        String[] checkOne = new String[]{};
        checkOne = check.split("-");
        System.out.println(checkOne.length);

        for (String menuCode : checkOne) {
            Role_authority role = roleService.findRolePermissionService(menuCode, roleKey);
            if (role != null) {
                continue;
            }
            Role_authority permission = new Role_authority();
            permission.setMenuCode(menuCode);
            permission.setRoleKey(roleKey);
            roleService.insertRolePService(permission);
        }
        result.setSuccess(true);
        result.setMessage("修改权限成功");
        super.writeJSON(result);
        return;

    }

    /**
     * 查询出所有的角色
     *
     * @throws IOException
     */
    @RequestMapping(value = "/findAll")
    public void findAll() throws IOException {
        List<Role> list = roleService.findAllRoleService();
        result.setData(list);
        result.setSuccess(true);
        super.writeJSON(result);
    }

    /**
     * 修改角色方法
     *
     * @param userid 对应要修改角色的用户的id
     * @param check  前台传回的角色数组，被处理成字符串传回
     * @throws IOException
     */
    @RequestMapping(value = "/alterRole")
    public void alterRole(int userid, String check) throws IOException {
        String[] checkOne = new String[]{};
        checkOne = check.split("-");
        for (String roleKey : checkOne) {
            User_role role = roleService.findUserRoleService(userid, roleKey);
            if (role != null) {
                continue;
            }
            User_role UR = new User_role();
            UR.setUserId(userid);
            UR.setRoleKey(check);
            roleService.insertRService(UR);
        }
        result.setSuccess(true);
        result.setMessage("修改角色成功");
        super.writeJSON(result);

    }
}
