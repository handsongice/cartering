package cn.jy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: jiyong
 * @Date: 2018-10-19 14:56
 */
@Configuration
public class AdminSecurityConfig extends WebMvcConfigurerAdapter {


    public final static String SESSION_KEY = "token";
    public static int i = 1;

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
        //排除的路径
        addInterceptor.excludePathPatterns("/static/**");
        addInterceptor.excludePathPatterns("/login/**");
        addInterceptor.excludePathPatterns("/error/**");
        //addInterceptor.excludePathPatterns("/findByAdminAndPassword");
        //拦截所有路径
        addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
            HttpSession session = request.getSession();
            //判断是否已有该用户登录的session
            if (session.getAttribute(SESSION_KEY) != null) {
                session.setMaxInactiveInterval(2 * 60 * 60);
                return true;
            }
            //跳转到登录页
            session.setAttribute(SESSION_KEY, "SESSION_KEY");
            response.sendRedirect("login");
            return false;
        }
    }

}

