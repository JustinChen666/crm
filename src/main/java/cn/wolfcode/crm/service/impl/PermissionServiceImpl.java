package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.mapper.PermissionMapper;
import cn.wolfcode.crm.service.IPermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    PermissionMapper mapper;
    @Autowired
    RequestMappingHandlerMapping handlerMapping;

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

    public void load() {
        //获取所有权限表达式
        List<String> resources = mapper.selectResource();
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        Collection<HandlerMethod> methods = handlerMethods.values();
        //循环取出每一个方法
        for (HandlerMethod method : methods) {
            //判断该方法上是否有指定的注解
            RequiresPermissions annotation = method.getMethodAnnotation(RequiresPermissions.class);
            if (annotation != null) {
                String[] value = annotation.value();
                if (!resources.contains(value[0])) {
                    //保存到数据库中
                    Permission p = new Permission();
                    p.setResource(value[0]);
                    p.setName(value[1]);
                    mapper.insert(p);
                }
            }
        }
    }

}
