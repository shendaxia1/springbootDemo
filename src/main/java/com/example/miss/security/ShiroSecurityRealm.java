package com.example.miss.security;

import com.example.miss.model.Role;
import com.example.miss.model.User;
import com.example.miss.model.Authority;
import com.example.miss.service.AuthorityService;
import com.example.miss.service.RoleService;
import com.example.miss.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShiroSecurityRealm extends AuthorizingRealm {
    /*
     * @Autowired private SysUserMapper sysUserMapper;
     *///注意这里只能注入接口，因为shiro的是比springmvc先加载的所以如果直接注入类的话就会有找不到依赖的错误，因为执行到这的时候还没有bean还没有生成
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthorityService authorityService;

    //权限管理
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String username = principals.toString();
        int userId = userService.findUser(username).getUserid();
        User user = userService.findUserById(userId);
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<Role> list = roleService.findUserRole(userId);
            for (Role role : roleService.findUserRole(userId)) {
                info.addRole(role.getRoleKey());//将角色的编码放入role中，如果需要别的可以再换
                for (Authority permission : authorityService.findRolePremission(role.getRoleKey())) {
                    info.addStringPermission(permission.getDataUrl());//将权限编码放入info中，如果需要其他的可以再get
                }


            }
            return info;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unused")
    @Override
    //登陆管理
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        // TODO Auto-generated method stub
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;//这个token是controller层传入的token
        /*
         * System.out.println(token.getUsername());
         * System.out.println(token.getPassword());
         */
        User user = userService.findUser(token.getUsername());//取出controller中传入token中保存的用户名，并查出来一条用户信息
        ByteSource salt = ByteSource.Util.bytes(user.getSalt());//得到该用户的密码盐值
        if (user != null) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), salt, this.getName());//SimpleAuthenticationInfo是用于验证前台传入得用户名密码是否匹配的，包含四个参数，具体参数的作用可百度
        }
        return null;
    }

}
