package com.example.miss.controller;

import com.example.miss.model.Authority;
import com.example.miss.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping(value = "/permission")
@RestController
public class PermissionController extends BaseController {
    @Autowired
    private AuthorityService authorityService;

    /**
     * 查询权限的每页的信息，和总页数
     *
     * @param page
     * @param limit
     * @param dataUrl
     * @throws IOException
     */
    @RequestMapping(value = "/permissionList")
    public void page(int page, int limit, String dataUrl) throws IOException {
        List<Authority> list = authorityService.permissionPageList(page, limit, dataUrl);
        int total = authorityService.permissionCount(dataUrl);
        result.setSuccess(true);
        result.setTotal(total);
        result.setData(list);
        super.writeJSON(result);
        return;
    }

    /**
     * 添加一条权限信息
     *
     * @param authority
     * @throws IOException
     */
    @RequestMapping(value = "/addpermission")
    public void add(Authority authority) throws IOException {
        if (authority.getDataUrl() != null && !authority.getDataUrl().equals("")) {
            authority.setMenuCode(UUID.randomUUID().toString().replace("-", ""));
            authorityService.permissionAddService(authority);
            result.setSuccess(true);
            result.setMessage("添加成功");
            super.writeJSON(result);
            return;
        } else {
            result.setSuccess(false);
            result.setMessage("权限不能为空");
            super.writeJSON(result);
            return;
        }

    }

    /**
     * 删除一条权限信息
     *
     * @param menuCode
     * @throws IOException
     */
    @RequestMapping(value = "/deletePermission")
    public void delete(String menuCode) throws IOException {
        authorityService.deletePService(menuCode);
        result.setSuccess(true);
        result.setMessage("删除成功");
        super.writeJSON(result);
        return;
    }

    @RequestMapping(value = "/alterPermission")
    public void alter(Authority authority) throws IOException {
        if (authority.getDataUrl() != null && !authority.getDataUrl().equals("")) {
            authorityService.alterPService(authority);
            result.setSuccess(true);
            result.setMessage("修改成功");
            super.writeJSON(result);
            return;
        } else {
            result.setMessage("权限不能为空");
            result.setSuccess(false);
            super.writeJSON(result);
            return;
        }
    }

}
