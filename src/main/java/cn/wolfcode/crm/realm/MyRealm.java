package cn.wolfcode.crm.realm;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    EmployeeMapper mapper;

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取登录表单中的用户名
        Object tokenPrincipal = token.getPrincipal();
        //查询数据库中是否拥有该用户
        Employee employee = mapper.selectByUsername((String) tokenPrincipal);
        if (employee == null) {
            return null;
        }
        //返回认证信息对象
        return new SimpleAuthenticationInfo(employee, employee.getPassword(), this.getName());
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登录的员工对象
        Employee employee = (Employee) principals.getPrimaryPrincipal();
        //判断是否是管理员
        if (employee.getAdmin()) {
            info.addStringPermission("*:*");
            info.addRole("admin");
        } else {
            //查询数据库的权限和角色相关信息
        }
        return info;
    }

}
