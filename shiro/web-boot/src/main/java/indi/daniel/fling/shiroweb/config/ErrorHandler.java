package indi.daniel.fling.shiroweb.config;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public String handleShiroException(ShiroException e) {
        String eName = e.getClass().getSimpleName();
        log.error("shiro执行出错：{}",eName);
        return "鉴权或授权过程出错";
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public String page401() {
        return "用户未登录";
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public String page403() {
        return "用户没有访问权限";
    }

}