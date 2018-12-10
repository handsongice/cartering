package cn.jy.controller.basic;

import cn.jy.dto.ResultMap;
import cn.jy.entity.Action;
import cn.jy.pojo.LayUiPageParams;
import cn.jy.service.ActionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/10/31 14:22
 */
@Controller
public class ActionController {
    @Autowired
    ActionService actionService;

    /**
     * 菜单页面
     * @return
     */
    @RequestMapping(value = "/main/action")
    public String action() {
        return "basic/action";
    }

    /**
     * 新建菜单页面
     * @return
     */
    @RequestMapping(value = "/main/addAction")
    public String addAction() {
        return "basic/addAction";
    }

    /**
     * 编辑菜单页面
     * @param params
     * @return
     */
    @RequestMapping(value = "/main/editAction")
    public ModelAndView editAction(@RequestParam Map<String, Object> params) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("basic/editAction");
        Long _id = Long.parseLong(params.get("id").toString());
        Action action = actionService.getActionById(_id);
        mv.addObject("action", action);
        return mv;
    }

    /**
     * 菜单列表
     *
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/noc/pageActionList")
    @ResponseBody
    public Object pageActionList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        try {
            List<Action> actions = actionService.getActionList(params);
            PageInfo<Action> pageInfo = new PageInfo<>(actions);

            LayUiPageParams<Action> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), actions);
            return pageParams;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 菜单树
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/noc/allActionList")
    @ResponseBody
    public Object allActionList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        try {
            List<Action> actions = actionService.getActionList(params);
            return actions;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/noc/insertAction")
    @ResponseBody
    public ResultMap insertAction(HttpServletRequest request, Action action) {
        return actionService.insertAction(action);
    }

    @RequestMapping(value = "/noc/updateAction")
    @ResponseBody
    public ResultMap updateAction(HttpServletRequest request, Action action) {
        return actionService.updateAction(action);
    }

    @RequestMapping(value = "/noc/delAction")
    @ResponseBody
    public ResultMap delAction(@RequestParam Map<String, Object> params,HttpServletRequest request) {
        return actionService.delAction(Long.parseLong(params.get("id").toString()));
    }
}
