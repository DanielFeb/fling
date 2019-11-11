package indi.daniel.fling.shiroweb.service;

import indi.daniel.fling.shiroweb.entity.Resource;
import indi.daniel.fling.shiroweb.entity.Role;
import indi.daniel.fling.shiroweb.entity.User;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Role ROLE_ADMIN = new Role();
    private static final Role ROLE_CITIZEN = new Role();

    static {
        ROLE_ADMIN.setName("admin");
        Resource resource = new Resource();
        resource.setUri("admin:resource");
        ROLE_ADMIN.setResources(Lists.list(resource));
        ROLE_CITIZEN.setName("citizen");
        resource = new Resource();
        resource.setUri("citizen:resource");
        ROLE_CITIZEN.setResources(Lists.list(resource));
    }
    public User getUserByName(String username) {
        User user = new User();
        user.setUsername(username);
        user.setPassword("123");
        if (username.equals("daniel")) {
            user.setRoles(Lists.list(ROLE_ADMIN, ROLE_CITIZEN));
        } else if (username.equals("admin")) {
            user.setRoles(Lists.list(ROLE_ADMIN));
        } else {
            user.setRoles(Lists.list(ROLE_CITIZEN));
        }
        return user;
    }
}
