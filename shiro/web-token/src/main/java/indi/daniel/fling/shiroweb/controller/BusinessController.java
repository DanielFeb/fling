package indi.daniel.fling.shiroweb.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {

    @GetMapping("/401")
    public String fail401() {
        return "401 error";
    }

    @GetMapping("/403")
    public String fail403() {
        return "403 error";
    }

    @GetMapping("/index")
    public String login() {
        return "hello index";
    }

    @GetMapping("/admin")
    @RequiresRoles("admin")
    public String admin() {
        return "hello admin";
    }

    @GetMapping("/admin/r")
    @RequiresPermissions("admin:resource")
    public String adminResource() {
        return "hello admin resource";
    }

    @GetMapping("/admin/r2")
    @RequiresPermissions("admin:resource2")
    public String adminResource2() {
        return "hello admin resource2";
    }

    @GetMapping("/citizen")
    @RequiresRoles("citizen")
    public String citizen() {
        return "hello citizen";
    }

    @GetMapping("/citizen/r")
    @RequiresPermissions("citizen:resource")
    public String citizenResource() {
        return "hello citizen resource";
    }
}
