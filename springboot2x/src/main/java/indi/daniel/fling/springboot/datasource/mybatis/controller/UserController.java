package indi.daniel.fling.springboot.datasource.mybatis.controller;

import indi.daniel.fling.springboot.datasource.mybatis.dao.UserDao;
import indi.daniel.fling.springboot.datasource.mybatis.dataconverter.SexEnum;
import indi.daniel.fling.springboot.datasource.mybatis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by daniel on 2018/12/20.
 */
@Controller
@RequestMapping("/mybatis")
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/createUser")
    @ResponseBody
    public User createUser(String name, Integer sex) {
        User user = new User();
        user.setUserName(name);
        user.setSex(SexEnum.getEmumId(sex));
        user.setId(userDao.create(user));
        return user;
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id) {
        User user = userDao.getUser(id);
        return user;
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello world";
    }
}
