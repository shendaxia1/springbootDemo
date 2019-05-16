package com.example.miss.controller;

import com.example.miss.controller.config.RedisService;
import com.example.miss.model.Authority;
import com.example.miss.model.Role;
import com.example.miss.model.User;
import com.example.miss.service.AuthorityService;
import com.example.miss.service.RoleService;
import com.example.miss.service.UserService;
import com.example.miss.util.MD5Salt;
import com.example.miss.util.RoleResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private RedisService redisService;//自动注入redis服务类

    /**
     * 登陆函数
     *
     * @param username 用户名
     * @param password 密码
     * @throws IOException
     */

    @RequestMapping(value = "/findUser")
    public void checkLogin(String username, String password, HttpServletRequest request) throws IOException {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject currectUser = SecurityUtils.getSubject();
        if (!currectUser.isAuthenticated()) {
            try {
                //使用shiro进行验证
                currectUser.login(token);
            } catch (AuthenticationException e) {
                result.setSuccess(false);
                result.setMessage("用户名或密码错误，请重新登陆");
                super.writeJSON(result);
                return;
            }
            String name = (String) SecurityUtils.getSubject().getPrincipal();//这样是得到SimpleAuthenticationInfo的第一个参数principal，应该还可以得到其他三个参数，但我们这里不需要，第一个参数我们放的是用户名
            User user = userService.findUser(name);
            if (user.getFlat() == 0) {
                result.setSuccess(false);
                result.setMessage("该员工已离职");
                super.writeJSON(result);
                return;
            }
            request.getSession().setAttribute("username", username);
            result.setData(user);
            result.setSuccess(true);
            result.setMessage("chengh");
            result.setToken(user.getUserid());
            writeJSON(result);
            return;
        }

    }

    /**
     * 分页显示用户列表
     *
     * @param page 当前页
     * @throws IOException
     */
    @RequestMapping(value = "/list")
    public void page(int page, int limit, String name) throws IOException {
        List<User> list = userService.findPageUserService(page, limit, name);
        int total = userService.findUserCount(name);
        result.setData(list);
        result.setTotal(total);
        result.setSuccess(true);
        writeJSON(result);
        return;
    }

    @RequestMapping(value = "/updateUser")
    public void updateUser(User user) throws IOException {
        userService.updateUserService(user);
        result.setSuccess(true);
        result.setMessage("修改成功");
        super.writeJSON(result);
        return;
    }

    /**
     * 用户登陆后将其权限以数组的方式传到前端，让前端生成对应的路由
     *
     * @param token
     * @throws IOException
     */
    @RequestMapping(value = "/info")
    public void roleInfo(int token) throws IOException {
        List<String> list1 = new ArrayList<String>();
        for (Role role : roleService.findUserRole(token)) {
            for (Authority permission : authorityService.findRolePremission(role.getRoleKey())) {
                list1.add(permission.getDataUrl());//将权限编码放入info中，如果需要其他的可以再get
            }
        }
        String[] inter = new String[list1.size()];
        String[] s = list1.toArray(inter);
        roleResult.setPermissions(s);
        User user = userService.findUserById(token);
        roleResult.setName(user.getName());
        roleResult.setIntroduction(user.getIntroduce());
        roleResult.setAvatar(user.getAvater());
        roleResult.setSuccess(true);
        super.writeJSON(roleResult);
        return;

    }

    @RequiresPermissions("admin11")
    @RequestMapping(value = "/addUser")
    public void addUser(User user) throws IOException {
        System.out.println(user.getCreateTime());
        if (user.getUsername() != null && user.getPassword() != null) {
            user.setFlat(1);
            String salt = Double.toString(Math.random());
            user.setSalt(salt);
            user.setPassword(MD5Salt.md5(user.getPassword(), salt));
            userService.addUserService(user);
            result.setSuccess(true);
            result.setMessage("添加用户成功");
            super.writeJSON(result);
            return;
        } else {
            result.setSuccess(false);
            result.setMessage("用户名或密码不能为空");
            super.writeJSON(result);
            return;
        }
    }

    /**
     * 删除用户
     *
     * @param userId
     */
    @RequestMapping(value = "/deleteUser")
    public void deleteUser(int userId) throws IOException {
        User user = userService.findUserById(userId);
        if (user != null) {
            userService.deleteUserService(userId);
            result.setSuccess(true);
            result.setMessage("删除成功");
            super.writeJSON(result);
        } else {
            result.setSuccess(false);
            result.setMessage("该用户不存在，请刷新后重试");
            super.writeJSON(result);
        }
    }

    @RequestMapping(value = "/logout")
    public void logout() throws IOException {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
        result.setMessage("登出成功");
        result.setSuccess(true);
        writeJSON(result);
        return;
    }

    @RequestMapping(value = "/selectUser")
    public void selectUser(String name) throws IOException {
        result.setData(userService.selectUserService(name));
        result.setTotal(userService.selectUserCountService(name));
        result.setSuccess(true);
        result.setMessage("查询成功");
        super.writeJSON(result);
        return;
    }


}
