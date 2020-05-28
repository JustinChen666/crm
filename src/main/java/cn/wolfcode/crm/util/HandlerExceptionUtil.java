package cn.wolfcode.crm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 对Controller的增强
 */
@ControllerAdvice
public class HandlerExceptionUtil {
    /**
     * 指定对哪个异常进行处理
     */
    @ExceptionHandler(UnauthorizedException.class)
    public void handlerException(HttpServletResponse response, HandlerMethod method) throws IOException {
        //判断是否是ajax请求,如果是就返回json字符串,否则就直接跳转到没有权限的页面
        ResponseBody responseBody = method.getMethodAnnotation(ResponseBody.class);
        if(responseBody!=null){
            response.getWriter().print(new ObjectMapper().writeValueAsString(new JsonResult().mark("没有权限操作")));
        }else {
            response.sendRedirect("/nopermission.jsp");
        }
    }
}
