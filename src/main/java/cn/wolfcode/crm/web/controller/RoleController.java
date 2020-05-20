package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IRoleService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @RequestMapping("view")
    public String index() {
        return "role";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(QueryObject query) {
        return roleService.query(query);
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Role role) {
        try {
            if (role.getId() != null) {
                roleService.updateByPrimaryKey(role);
            } else {
                roleService.insert(role);
            }
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult().mark("操作失败!");
        }
    }

}
