package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IRoleService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @RequestMapping("view")
    @RequiresPermissions(value = {"role:view","角色页面"},logical = Logical.OR)
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

    @RequestMapping("selectAll")
    @ResponseBody
    public List<Role> selectAll(){
        return roleService.selectAll();
    }

    @RequestMapping("selectByEmployeeId")
    @ResponseBody
    public List<Long> selectByEmployeeId(Long id) {
        return roleService.selectRoleIdByEmployeeId(id);
    }

}
