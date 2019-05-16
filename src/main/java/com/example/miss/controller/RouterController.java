package com.example.miss.controller;

import com.example.miss.model.*;
import com.example.miss.service.AuthorityService;
import com.example.miss.service.RoleService;
import com.example.miss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/router")
public class RouterController extends BaseController {
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/add")
    public void addRoute(HttpServletRequest request) throws IOException {
        String username = (String) request.getSession().getAttribute("username");

        User user = userService.findUser(username);
        List<Router> list = new ArrayList<Router>();
        for (Role role : roleService.findUserRole(user.getUserid())) {//根据用户id查询出拥有的角色
            for (Authority authority : authorityService.findRolePremission(role.getRoleKey())) {//根据角色id查询出所拥有的菜单权限
                Router router = new Router();
                if (authority.getParentMenucode() != null) {//判断得到的路由是否是子路由，如果是则进入if
                    for (Router router1 : list) {//遍历list找到这个子菜单的父级菜单，然后向该父级菜单中的childer中加入
                        if (router1.getMenu_code().equals(authority.getParentMenucode())) {
                            Children children = new Children();
                            children.setPath(authority.getDataUrl());
                            router1.setChildren(children);
                            break;
                        }

                    }
                    continue;
                }

                router.setPath(authority.getDataUrl());
                router.setMenu_code(authority.getMenuCode());
                list.add(router);
            }
        }
        result.setData(list);
        super.writeJSON(result);

    }
}
