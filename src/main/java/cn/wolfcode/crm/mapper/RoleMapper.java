package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    int query4Count(QueryObject query);

    List<Role> query4List(QueryObject query);

    void insertRelation(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    void deleteRelation(Long roleId);

    List<Long> selectRoleIdByEmployeeId(Long id);

    List<String> selectRoleSnByEmployeeId(Long emplyoeeId);
}