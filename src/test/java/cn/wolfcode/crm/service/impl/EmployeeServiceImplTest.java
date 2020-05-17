package cn.wolfcode.crm.service.impl;


import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeServiceImplTest {

    @Autowired
    IEmployeeService employeeService;

    @Test
    public void insert() {
        for (int i = 1; i <= 15; i++) {
            Employee employee = new Employee();
            employee.setAdmin(false);
            employee.setState(false);
            employee.setEmail("111@qq.com");
            employee.setHireDate(new Date());
            employee.setPassword("1");
            employee.setUsername("wolf"+i);
            employee.setRealname("wolf"+i);
            employee.setTel("111");
            employeeService.insert(employee);
        }
    }
}