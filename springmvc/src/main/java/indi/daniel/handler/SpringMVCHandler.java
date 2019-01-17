package indi.daniel.handler;

import indi.daniel.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("handler")
public class SpringMVCHandler {
    @RequestMapping(value = "welcome", method = RequestMethod.GET)
    public String welcome() {
        return "success";
    }

//    * @RequestMapping
//    - params 设置参数格式
//    必须要有name，且name=23
//    age不能为23，可以没有
//    不能有height
//    @RequestMapping(value = "welcome", method = RequestMethod.POST, params = {"name=zs", "age!=3", "!height"})

//    - headers 设置请求头
//    @RequestMapping(value = "welcome", method = RequestMethod.POST, headers = {"Accept=text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8"})

//    - value 可以设置通配符 单个字符？ 任意个字符*  任意路径**


//    * @PathVariable
//    * @RequestHeader
//    * @CookieValue

    @RequestMapping(value = "student1", method = RequestMethod.POST)
    public String printStudent(Student student) {
        System.out.println(student);
        return "success";
    }

    @RequestMapping(value = "student", method = RequestMethod.POST)
    public ModelAndView postStudent(Student student, ModelAndView modelAndView) {
        modelAndView.setViewName("studentview");
        modelAndView.addObject("student", student);
        System.out.println(student);
        return modelAndView;
    }

    @RequestMapping(value = "testModelMap", method = RequestMethod.POST)
    public String testModelMap(Student student, ModelMap m) {
        m.addAttribute("student", student);
        System.out.println(student);
        return "studentview";
    }

    @RequestMapping(value = "testMap", method = RequestMethod.POST)
    public String testMap(Student student, Map<String, Object> m) {
        m.put("student", student);
        System.out.println(student);
        return "studentview";
    }

    @RequestMapping(value = "testModel", method = RequestMethod.POST)
    public String testModel(Student student, Model m) {
        m.addAttribute("student", student);
        System.out.println(student);
        return "studentview";
    }
}
