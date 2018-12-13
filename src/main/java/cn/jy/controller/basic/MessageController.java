package cn.jy.controller.basic;

import cn.jy.controller.BaseController;
import cn.jy.dto.ResultMap;
import cn.jy.entity.Broadcast;
import cn.jy.entity.BroadcastEmployee;
import cn.jy.entity.Employee;
import cn.jy.entity.Message;
import cn.jy.pojo.LayUiPageParams;
import cn.jy.service.BroadcastService;
import cn.jy.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class MessageController extends BaseController {
    @Autowired
    BroadcastService broadcastService;

    @Autowired
    MessageService messageService;
    /**
     * 消息页面
     * @return
     */
    @RequestMapping(value = "/main/message")
    public String message() {
        return "basic/message";
    }

    /**
     * 查看消息
     * @return
     */
    @RequestMapping(value = "/viewBroadcast")
    public ModelAndView viewBroadcast(@RequestParam Map<String, Object> params) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("basic/viewBroadcast");
        Long _id = Long.parseLong(params.get("id").toString());
        Broadcast broadcast = broadcastService.getBroadcastById(_id);
        Employee employee = getLoginEmployee();
        if(null != broadcast && null != broadcast.getId()) {
            BroadcastEmployee input = new BroadcastEmployee();
            input.setBroadcastId(broadcast.getId());
            input.setEmployeeId(employee.getId());
            BroadcastEmployee broadcastEmployee = broadcastService.findBroadcastEmployee(input);
            if(null == broadcastEmployee || null == broadcastEmployee.getId()) {
                broadcastService.addBroadcastEmployee(input);
            }
        }
        mv.addObject("broadcast", broadcast);
        return mv;
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
    /**
     * 列表
     * @param params
     * @return
     */
    @RequestMapping("/noc/pageMessageList")
    @ResponseBody
    public Object pageMessageList(@RequestParam Map<String, Object> params) {
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        Employee employee = getLoginEmployee();
        params.put("to_id",employee.getId());
        params.put("enterprise_id",employee.getEnterpriseId());
        params.put("to_type",1);
        try {
            List<Message> messages = messageService.getMessageList(params);
            PageInfo<Message> pageInfo = new PageInfo<>(messages);

            LayUiPageParams<Message> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), messages);
            return pageParams;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/noc/readMessage")
    @ResponseBody
    public ResultMap readMessage(@RequestParam Map<String, Object> params) {
        ResultMap ret = null;
        Employee employee = getLoginEmployee();
        params.put("employee_id",employee.getId());
        try {
            if("1".equals(params.get("type").toString())) {
                ret = broadcastService.addBroadcastsEmployee(params);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
        return ret;
    }
}
