package com.example.miss.controller;

import com.example.miss.model.User;
import com.example.miss.util.Result;
import com.example.miss.util.RoleResult;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@Controller
public class BaseController {
    public static final String USER_SESSION = "USER_SESSION";
    protected static ObjectMapper mapper = new ObjectMapper();
    protected static JsonFactory factory = mapper.getFactory();
    protected static Result result = new Result() {
        @Override
        public void setSystemId(String systemId) {

        }

    };//状态字段都存储在里面
    protected static RoleResult roleResult = new RoleResult();//登陆成功后返回的用户和角色信息

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    /**
     * 将json字符串输出
     **/
    protected void writeJSON(String json) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 将对象转成json输出
     **/
    protected void writeJSON(Object obj) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        //JsonGenerator responseJsonGenerator = factory.createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
        JsonGenerator responseJsonGenerator = factory.createGenerator(response.getOutputStream(), JsonEncoding.UTF8);
        responseJsonGenerator.writeObject(obj);
    }

    /**
     * 获得session用户对象
     *
     * @return
     */
    protected User getUser() {
        Object userObj = session.getAttribute(USER_SESSION);
        if (userObj == null) {
            return null;
        }
        return (User) userObj;
    }
}
