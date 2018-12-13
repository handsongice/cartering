package cn.jy.controller.basic;

import cn.jy.constent.Constent;
import cn.jy.controller.BaseController;
import cn.jy.dto.ResultMap;
import cn.jy.entity.Employee;
import cn.jy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class EmployeeController extends BaseController {
    @Autowired
    EmployeeService employeeService;

    /**
     * 修改个人信息
     * @return
     */
    @RequestMapping(value = "/user-information")
    public ModelAndView user_information(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user-information");
        Employee _employee = getLoginEmployee();
        Employee employee = employeeService.getEmployeeById(_employee.getId());
        mv.addObject("employee", employee);
        return mv;
    }

    /**
     * 修改密码
     * @return
     */
    @RequestMapping(value = "/change-password")
    public ModelAndView change_password(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("change-password");
        Employee employee = getLoginEmployee();
        mv.addObject("employee", employee);
        return mv;
    }

    /**
     * 更新
     * @param request
     * @param employee
     * @return
     */
    @RequestMapping(value = "/noc/updateMyInfo")
    @ResponseBody
    public ResultMap updateMyInfo(HttpServletRequest request, Employee employee) {
        ResultMap ret = null;
        try {
            ret = employeeService.updateMyInfo(employee);
            Employee _employee = employeeService.getEmployeeById(employee.getId());
            HttpSession session = request.getSession();
            session.setAttribute(Constent.SESSION_EMPLOYEE, _employee);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
        return ret;
    }

    /**
     * 更新密码
     * @param params
     * @return
     */
    @RequestMapping(value = "/noc/updateMyPassword")
    @ResponseBody
    public ResultMap updateMyPassword(@RequestParam Map<String, Object> params) {
        ResultMap ret = null;
        try {
            ret = employeeService.updateMyPassword(params);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
        return ret;
    }
}
