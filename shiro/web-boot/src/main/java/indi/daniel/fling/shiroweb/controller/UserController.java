package indi.daniel.fling.shiroweb.controller;

import indi.daniel.fling.shiroweb.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @PostMapping("login")
    public User login(@RequestBody User user) {

        if (StringUtils.isEmpty(user.getUsername())){
            throw new AuthenticationException("用户名不能为空");
        }
        if (StringUtils.isEmpty(user.getPassword())){
            throw new AuthenticationException("密码不能为空");
        }

        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.isAuthenticated()) {
            throw new AuthenticationException("已经登录过了");
        }

        try {
            //登录
            currentUser.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()) );
            //从session取出用户信息
            User authUser = (User) currentUser.getPrincipal();
            if (authUser == null) throw new AuthenticationException();
            //返回登录用户的信息给前台，含用户的所有角色和权限
            return authUser;

        }  catch (Exception e) {
            throw e;
        }

//        catch ( UnknownAccountException uae ) {
//            return "用户帐号或密码不正确";
//
//        } catch ( IncorrectCredentialsException ice ) {
//            return "用户帐号或密码不正确";
//
//        } catch ( LockedAccountException lae ) {
//            return "用户帐号被锁定不可用";
//
//        } catch ( AuthenticationException ae ) {
//            return "登录失败："+ae.getMessage();
//        }
    }
}
