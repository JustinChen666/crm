package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.mapper.RoleMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IRoleService;
import cn.wolfcode.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    RoleMapper mapper;

    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    public int insert(Role record) {
        int count = mapper.insert(record);
        //关联关系
        List<Permission> permissions = record.getPermissions();
        for (Permission permission : permissions) {
            mapper.insertRelation(record.getId(),permission.getId());
        }
        return count;

    }

    public Role selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<Role> selectAll() {
        return mapper.selectAll();
    }

    public int updateByPrimaryKey(Role record) {
        //打破关系
        mapper.deleteRelation(record.getId());
        //关联关系
        List<Permission> permissions = record.getPermissions();
        for (Permission permission : permissions) {
            mapper.insertRelation(record.getId(),permission.getId());
        }
        return mapper.updateByPrimaryKey(record);
    }

    public PageResult query(QueryObject query) {
        int count = mapper.query4Count(query);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<Role> emps = mapper.query4List(query);
        System.out.println(emps);
        return new PageResult(count, emps);
    }

    public List<Long> selectRoleIdByEmployeeId(Long id) {
        return mapper.selectRoleIdByEmployeeId(id);
    }


}
