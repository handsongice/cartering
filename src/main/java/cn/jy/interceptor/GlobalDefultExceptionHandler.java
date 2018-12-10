package cn.jy.interceptor;

import cn.jy.common.BaseResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 123 on 2018-08-08
 */
@ControllerAdvice
@Slf4j
public class GlobalDefultExceptionHandler {
    // 声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object defultExcepitonHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        //log.error("【****异常****】:调用接口" + request.getRequestURL() + "时产生异常：" + e.getMessage());
        // 未知错误
        int index = request.getRequestURL().lastIndexOf("/");
        return BaseResultData.resultError(e.toString(), null);
    }
}
