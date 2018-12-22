package cn.jy.controller.basic;

import cn.jy.constent.Constent;
import cn.jy.controller.BaseController;
import cn.jy.dto.ResultMap;
import cn.jy.entity.Employee;
import cn.jy.entity.Enterprise;
import cn.jy.pojo.LayUiPageParams;
import cn.jy.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
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

    /**
     * 企业列表
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/noc/pageEmployeeList")
    @ResponseBody
    public Object pageEmployeeList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        Enterprise enterprise = getLoginEnterprise();
        params.put("enterprise_id",enterprise.getId());
        try {
            List<Employee> employees = employeeService.getEmployeeList(params);
            PageInfo<Employee> pageInfo = new PageInfo<>(employees);

            LayUiPageParams<Employee> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), employees);
            return pageParams;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 插入
     * @param request
     * @param employee
     * @return
     */
    @RequestMapping(value = "/noc/insertEmployee")
    @ResponseBody
    public ResultMap insertEmployee(HttpServletRequest request, Employee employee) {
        Enterprise enterprise = getLoginEnterprise();
        employee.setEnterpriseId(enterprise.getId());
        try {
            return employeeService.addEmployee(employee);
        }catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
    }

    /**
     * 更新密码
     * @param employee
     * @return
     */
    @RequestMapping(value = "/noc/updateEmployee")
    @ResponseBody
    public ResultMap updateEmployee(Employee employee) {
        ResultMap ret = null;
        try {
            ret = employeeService.updateEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
        return ret;
    }

    @RequestMapping(value = "/noc/delEmployee")
    @ResponseBody
    public ResultMap delEmployee(@RequestParam Map<String, Object> params,HttpServletRequest request) {
        return employeeService.delEmployee(Long.parseLong(params.get("id").toString()));
    }
}
