package cn.jy.controller;

import cn.jy.entity.Employee;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static cn.jy.constent.Constent.SESSION_EMPLOYEE;

public abstract class BaseController {
    /**
     * 获取当前登录的用户信息
     *
     * @return
     */
    public Employee getLoginEmployee() {
        Employee employee = (Employee) this.getSession().getAttribute(SESSION_EMPLOYEE);
        if (employee != null) {
            return employee;
        }
        return null;
    }
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public HttpSession getSession() {
        return getRequest().getSession();
    }

    public HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
