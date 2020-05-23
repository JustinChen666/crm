package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.query.EmployeeQuery;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;

    @RequestMapping("view")
    @RequiresPermissions(value = {"employee:view","员工页面"},logical = Logical.OR)
    public String index() {
        return "employee";
    }

    @RequestMapping("list")
    @ResponseBody
    @RequiresPermissions(value = {"employee:list","员工列表"},logical = Logical.OR)
    public PageResult list(EmployeeQuery query) {
        return employeeService.query(query);
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions(value = {"employee:saveOrUpdate","员工新增/编辑"},logical = Logical.OR)
    public JsonResult saveOrUpdate(Employee employee) {
        try {
            if (employee.getId() != null) {
                employeeService.updateByPrimaryKey(employee);
            } else {
                //设置为在职状态
                employee.setState(true);
                employeeService.insert(employee);
            }
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult().mark("操作失败!");
        }
    }

    @RequestMapping("changeState")
    @ResponseBody
    @RequiresPermissions(value = {"employee:changeState","员工状态"},logical = Logical.OR)
    public JsonResult changeState(Long id) {
        try {
            employeeService.changeState(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult().mark("操作失败!");
        }
    }
}
