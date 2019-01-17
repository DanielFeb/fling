package indi.daniel.fling.springboot.datasource.jpa.controller;

import indi.daniel.fling.springboot.datasource.jpa.dao.JpaUserRepository;
import indi.daniel.fling.springboot.datasource.jpa.dataconverter.SexEnum;
import indi.daniel.fling.springboot.datasource.jpa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by daniel on 2018/12/20.
 */
@Controller
@RequestMapping("/jpa")
public class JpaUserController {
    @Autowired
    private JpaUserRepository jpaUserRepository = null;

    @RequestMapping("/createUser")
    @ResponseBody
    public User createUser(String name, Integer sex) {
        User user = new User();
        user.setUserName(name);
        user.setSex(SexEnum.getEmumId(sex));
        return jpaUserRepository.save(user);
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id) {
        User user = jpaUserRepository.findById(id).get();
        return user;
    }
}
