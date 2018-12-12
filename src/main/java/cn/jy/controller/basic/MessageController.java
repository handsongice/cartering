package cn.jy.controller.basic;

import cn.jy.controller.BaseController;
import cn.jy.entity.Broadcast;
import cn.jy.entity.Employee;
import cn.jy.entity.Enterprise;
import cn.jy.pojo.LayUiPageParams;
import cn.jy.service.BroadcastService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController extends BaseController {
    @Autowired
    BroadcastService broadcastService;

    /**
     * 消息页面
     * @return
     */
    @RequestMapping(value = "/main/message")
    public String message() {
        return "basic/message";
    }
    /**
     * 列表
     * @param params
     * @return
     */
    @RequestMapping("/noc/pageBroadcastList")
    @ResponseBody
    public Object pageBroadcastList(@RequestParam Map<String, Object> params) {
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        Employee employee = getLoginEmployee();
        params.put("employee_id",employee.getId());
        params.put("enterprise_id",employee.getEnterpriseId());
        params.put("to_employee",1);
        try {
            List<Broadcast> broadcasts = broadcastService.getBroadcastList(params);
            PageInfo<Broadcast> pageInfo = new PageInfo<>(broadcasts);

            LayUiPageParams<Broadcast> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), broadcasts);
            return pageParams;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
