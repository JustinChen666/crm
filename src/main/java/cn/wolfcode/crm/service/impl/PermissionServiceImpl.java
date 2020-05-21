package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.mapper.PermissionMapper;
import cn.wolfcode.crm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    PermissionMapper mapper;

    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    public int insert(Permission record) {
        return mapper.insert(record);
    }

    public Permission selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<Permission> selectAll() {
        return mapper.selectAll();
    }

    public int updateByPrimaryKey(Permission record) {
        return mapper.updateByPrimaryKey(record);
    }

    public List<Permission> selectByRoleId(Long id) {
        return mapper.selectByRoleId(id);
    }

}
