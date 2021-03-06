package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import cn.wolfcode.crm.query.EmployeeQuery;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    EmployeeMapper mapper;

    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    public int insert(Employee record) {
        //对密码做加密操作
        Md5Hash hash = new Md5Hash(record.getPassword(),record.getUsername(),2);
        record.setPassword(hash.toString());
        int count = mapper.insert(record);
        //关联关系
        List<Role> roles = record.getRoles();
        for (Role role : roles) {
            mapper.insertRelation(record.getId(), role.getId());
        }
        return count;
    }

    public Employee selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<Employee> selectAll() {
        return mapper.selectAll();
    }

    public int updateByPrimaryKey(Employee record) {
        //打破关系
        mapper.deleteRelation(record.getId());
        //关联关系
        List<Role> roles = record.getRoles();
        for (Role role : roles) {
            mapper.insertRelation(record.getId(), role.getId());
        }
        return mapper.updateByPrimaryKey(record);
    }

    public PageResult query(EmployeeQuery query) {
        int count = mapper.query4Count(query);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<Employee> emps = mapper.query4List(query);
        System.out.println(emps);
        return new PageResult(count, emps);
    }

    public void changeState(Long id) {
        mapper.changeState(id);
    }
}
