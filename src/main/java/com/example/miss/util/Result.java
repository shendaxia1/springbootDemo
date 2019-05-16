package com.example.miss.util;

import java.io.Serializable;

public abstract class Result implements Serializable {
    private static final long serialVersionUID = 2267751680865696851L;
    /**
     * 是否成功
     **/
    private Boolean success = false;
    /**
     * 返回消息
     **/
    private String message;
    /**
     * 返回数据
     **/
    private Object data;
    /**
     * 返回查询的总数
     **/
    private int total;
    //首次登陆时返回给前端包含用户和用户对应的角色信息
    private int token;


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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public abstract void setSystemId(String systemId);

    public String getSystemId() {
        return null;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }
}
