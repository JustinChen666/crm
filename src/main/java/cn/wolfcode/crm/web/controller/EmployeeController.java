package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.query.EmployeeQuery;
import cn.wolfcode.crm.service.IEmployeeService;
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
}
