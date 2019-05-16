package com.example.miss.util;

import java.util.ArrayList;

/**
 * userInfo的返回值，包含用户信息和角色信息
 */
public class RoleResult {
    private String[] permissions;//用户角色
    private String name;//用户真实姓名
    private String avatar;//用户头像
    private String introduction;//用户介绍
    private Boolean success;//用户前端条件判断
    private String message;//前端输出后台定义的信息

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
