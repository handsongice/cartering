package cn.jy.controller.enterprise;

import cn.jy.controller.BaseController;
import cn.jy.entity.Enterprise;
import cn.jy.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyEnterpriseController extends BaseController {
    @Autowired
    EnterpriseService enterpriseService;

    /**
     * 列表
     * @return
     */
    @RequestMapping(value = "/main/myEnterprise")
    public ModelAndView myEnterprise(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("enterprise/myEnterprise");
        Enterprise enterprise = getLoginEnterprise();
        Enterprise _enterprise = enterpriseService.getEnterpriseById(enterprise.getId());
        mv.addObject("enterprise", _enterprise);
        return mv;
    }
}
