package cn.jy.controller;

import cn.jy.entity.Action;
import cn.jy.entity.Employee;
import cn.jy.service.ActionService;
import cn.jy.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PageController extends BaseController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    ActionService actionService;

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }
    @RequestMapping(value = "/main")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        Employee employee = getLoginEmployee();
        if(null == employee || StringUtils.isEmpty(employee.getLoginCode())) {
            return mv;
        }
        mv.addObject("employee", employee);
        try {
            List<Action> actions = actionService.leftMenus(employee);
            mv.addObject("actions", actions);
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            return mv;
        }
    }
    @RequestMapping(value = "/home")
    public ModelAndView home(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        Map<String, Object> params = new HashMap<>();
        PageHelper.startPage(1, 10, true);
        return mv;
    }
}
