package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.service.IPermissionService;
import cn.wolfcode.crm.service.impl.PermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    IPermissionService permissionService;

    @RequestMapping("selectAll")
    @ResponseBody
    public List<Permission> list() {
        List<Permission> permissions = permissionService.selectAll();
        return permissions;
    }

    @RequestMapping("selectByRoleId")
    @ResponseBody
    public List<Permission> selectByRoleId(Long id){
        return permissionService.selectByRoleId(id);
    }

    @RequestMapping("load")
    @ResponseBody
    public void load(){
        permissionService.load();
    }

}
