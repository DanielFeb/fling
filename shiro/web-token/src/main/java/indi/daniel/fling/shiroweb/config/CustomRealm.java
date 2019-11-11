package indi.daniel.fling.shiroweb.config;

import indi.daniel.fling.shiroweb.entity.Resource;
import indi.daniel.fling.shiroweb.entity.Role;
import indi.daniel.fling.shiroweb.entity.User;
import indi.daniel.fling.shiroweb.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        User user = (User) getAvailablePrincipal(principals);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = user
                .getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        Set<String> perms = user
                .getRoles()
                .stream()
                .flatMap(role -> role.getResources().stream())
                .map(Resource::getUri)
                .collect(Collectors.toSet());
        System.out.println("获取角色信息："+roles);
        System.out.println("获取权限信息："+perms);

        info.setRoles(roles);
        info.setStringPermissions(perms);
        return info;
    }

    /**
     * 这里可以注入userService,为了方便演示，我就写死了帐号了密码
     * private UserService userService;
     * <p>
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {


        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }

        User userDB = userService.getUserByName(username);


        if (userDB == null) {
            throw new UnknownAccountException("No account found for admin [" + username + "]");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDB, userDB.getPassword(), getName());
//        if (userDB.getSalt() != null) {
//            info.setCredentialsSalt(ByteSource.Util.bytes(userDB.getSalt()));
//        }

        return info;

    }
}
