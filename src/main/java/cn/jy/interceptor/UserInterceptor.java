package cn.jy.interceptor;

import cn.jy.entity.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Component
public class UserInterceptor implements HandlerInterceptor {

    //@Autowired
    //private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //执行handler之前先执行
        //从cookie里取token信息
        System.out.println("UserInterceptor");
        HttpSession session = httpServletRequest.getSession();
        Employee employee = (Employee) session.getAttribute("employee");
        if (null == employee || StringUtils.isBlank(employee.getLoginCode())) {
            //跳转登陆界面
            httpServletResponse.sendRedirect("/login");
            return false;
        }
        //取到用户名和token 去验证用户是否登录了
//        BaseResultData result = userService.getUserByToken(username,null);
//        if (result.getStatus() != 200){
//            //取当前请求的url
//            String requestURL = httpServletRequest.getRequestURL().toString();
//            //跳转登陆界面
//            httpServletResponse.sendRedirect("?url="+requestURL);
//            return false;
//        }
        //session设置有效期2小时
        httpServletRequest.getSession().setMaxInactiveInterval(2 * 60 * 60);
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
