package cn.jy.controller.basic;

import cn.jy.common.BaseResultData;
import cn.jy.constent.Constent;
import cn.jy.entity.Employee;
import cn.jy.service.EmployeeService;
import cn.jy.util.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class RoleController {
    @Autowired
    EmployeeService employeeService;

    /**
     * 登录
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("dologin")
    @ResponseBody
    public Object doLogin(@RequestParam Map<String, Object> params, HttpServletRequest request) {

        //输入检查
        String username = params.get("username").toString();
        if (null == username || "".equalsIgnoreCase(username)) {
            return BaseResultData.resultError("1", null);
        }
        String password = params.get("password").toString();
        if (null == password || "".equalsIgnoreCase(password)) {
            return BaseResultData.resultError("2", null);
        }
        String code = params.get("code").toString();
        if (null == code || "".equalsIgnoreCase(code)) {
            return BaseResultData.resultError("3", null);
        }
        String sessionCode = request.getSession().getAttribute("code").toString();
        if(null == sessionCode || "".equalsIgnoreCase(sessionCode)) {
            return BaseResultData.resultError("4", null);
        }
        //验证码校验
        if(!code.equalsIgnoreCase(sessionCode)) {
            return BaseResultData.resultError("4", null);
        }
        Employee employee;
        try {
            //用户名验证
            employee = employeeService.getEmployee(username);
            if(null == employee || StringUtils.isEmpty(employee.getLoginCode())) {
                return BaseResultData.resultError("5", null);
            }
            //密码验证
            if(!MD5.md5(password).equals(employee.getPassword())) {
                return BaseResultData.resultError("6", null);
            }
            //登录成功
            HttpSession session = request.getSession();
            session.setAttribute(Constent.SESSION_EMPLOYEE, employee);
        }catch (Exception e) {
            return BaseResultData.resultError("0", null);
        }
        return BaseResultData.resultOK(employee);
    }

    /**
     * 登出
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("dologout")
    @ResponseBody
    public Object doLogout(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constent.SESSION_EMPLOYEE);
        session.invalidate();
        return BaseResultData.resultOK(null);
    }
}
