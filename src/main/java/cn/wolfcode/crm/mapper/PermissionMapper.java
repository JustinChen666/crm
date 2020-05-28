package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Permission;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    List<Permission> selectByRoleId(Long id);

    List<String> selectResource();

    List<String> selectByEmployeeId(Long employeeId);
}