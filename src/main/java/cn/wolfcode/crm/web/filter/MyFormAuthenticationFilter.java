package cn.wolfcode.crm.web.filter;

import cn.wolfcode.crm.util.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        response.getOutputStream().print(new ObjectMapper().writeValueAsString(new JsonResult()));
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        String errorMsg = "登录异常,请联系管理员!";

        //判断异常对象是属于哪种异常
        if(e instanceof UnknownAccountException){
            errorMsg = "用户名不存在!";
        }else if (e instanceof IncorrectCredentialsException){
            errorMsg="密码错误!";
        }
        try {
            //使用字符流
            response.getWriter().print(new ObjectMapper().writeValueAsString(new JsonResult().mark(errorMsg)));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }
}
