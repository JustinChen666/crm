package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.query.EmployeeQuery;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PageResult;
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
    public String index() {
        return "employee";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(EmployeeQuery query) {
        return employeeService.query(query);
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
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

}
