package cn.jy.controller.enterprise;

import cn.jy.controller.BaseController;
import cn.jy.dto.ResultMap;
import cn.jy.entity.*;
import cn.jy.pojo.LayUiPageParams;
import cn.jy.service.EmployeeService;
import cn.jy.service.StoreService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/12/20 13:32
 */
@Controller
public class StoreController extends BaseController {

    @Autowired
    StoreService storeService;

    @Autowired
    EmployeeService employeeService;

    /**
     * 门店页面
     * @return
     */
    @RequestMapping(value = "/main/store/store")
    public String store() {
        return "enterprise/store/store";
    }

    /**
     * 门店员工
     * @return
     */
    @RequestMapping(value = "/main/store/employee")
    public ModelAndView storeEmployee() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("enterprise/store/employee");
        Map<String, Object> params = new HashMap<>();
        Enterprise enterprise = getLoginEnterprise();
        params.put("enterprise_id",enterprise.getId());
        List<Store> stores = storeService.getStoreList(params);
        mv.addObject("store_id", 0);
        mv.addObject("stores", stores);
        return mv;
    }

    /**
     * 门店员工
     * @return
     */
    @RequestMapping(value = "/main/store/goods")
    public ModelAndView storeGoods() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("enterprise/store/goods");
        Map<String, Object> params = new HashMap<>();
        Enterprise enterprise = getLoginEnterprise();
        params.put("enterprise_id",enterprise.getId());
        List<Store> stores = storeService.getStoreList(params);
        if(null != stores && stores.size() >0) {
            mv.addObject("store_id", stores.get(0).getId());
        } else {
            mv.addObject("store_id", 0);
        }
        stores.get(0).setStyle("layui-this");
        mv.addObject("stores", stores);
        return mv;
    }

    /**
     * 新建门店员工
     * @return
     */
    @RequestMapping(value = "/main/store/addEmployee")
    public ModelAndView addStoreEmployee(@RequestParam Map<String, Object> params) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("enterprise/store/addEmployee");
        Enterprise enterprise = getLoginEnterprise();
        params.put("enterprise_id",enterprise.getId());
        if(null != params.get("store_id") && StringUtil.isNotEmpty(params.get("store_id").toString())) {
            Long _id = Long.parseLong(params.get("store_id").toString());
            params.put("id",_id);
        }
        List<Store> stores = storeService.getStoreList(params);
        mv.addObject("stores", stores);
        return mv;
    }
    /**
     * 编辑门店员工
     * @return
     */
    @RequestMapping(value = "/main/store/editEmployee")
    public ModelAndView editStoreEmployee(@RequestParam Map<String, Object> params) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("enterprise/store/editEmployee");
        Long _id = Long.parseLong(params.get("id").toString());
        Employee employee = employeeService.getEmployeeById(_id);
        mv.addObject("employee", employee);
        Map<String, Object> params1 = new HashMap<>();
        Enterprise enterprise = getLoginEnterprise();
        params1.put("enterprise_id",enterprise.getId());
        params1.put("id",employee.getStoreId());
        List<Store> stores = storeService.getStoreList(params1);

        mv.addObject("stores", stores);
        return mv;
    }
    /**
     * 新建门店
     * @return
     */
    @RequestMapping(value = "/main/store/addStore")
    public ModelAndView addStore(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("enterprise/store/addStore");
        Map<String, Object> params = new HashMap<>();
        List<Format> formats = storeService.getFormatList(params);
        List<CookStyle> cookStyles = storeService.getCookStyleList(params);
        mv.addObject("formats", formats);
        mv.addObject("cookStyles", cookStyles);
        return mv;
    }
    /**
     * 编辑门店
     * @return
     */
    @RequestMapping(value = "/main/store/editStore")
    public ModelAndView editStore(@RequestParam Map<String, Object> params) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("enterprise/store/editStore");
        Long _id = Long.parseLong(params.get("id").toString());
        Store store = storeService.getStoreById(_id);
        mv.addObject("store", store);

        Map<String, Object> params1 = new HashMap<>();
        List<Format> formats = storeService.getFormatList(params1);

        for (Format f:formats) {
            if(f.getId().intValue() == store.getFormat()) {
                f.setChecked("selected");
            }
        }
        List<CookStyle> cookStyles = storeService.getCookStyleList(params1);
        for (CookStyle c:cookStyles) {
            if(c.getId().intValue() == store.getCookStyle()) {
                c.setChecked("selected");
            }
        }
        mv.addObject("formats", formats);
        mv.addObject("cookStyles", cookStyles);
        return mv;
    }
    /**
     * 列表
     * @param params
     * @return
     */
    @RequestMapping("/noc/store/pageStoreList")
    @ResponseBody
    public Object pageStoreList(@RequestParam Map<String, Object> params) {
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        Enterprise enterprise = getLoginEnterprise();
        params.put("enterprise_id",enterprise.getId());
        try {
            List<Store> stores = storeService.getStoreList(params);
            PageInfo<Store> pageInfo = new PageInfo<>(stores);

            LayUiPageParams<Food> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), stores);
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
    @RequestMapping("/noc/store/pageGoodsList")
    @ResponseBody
    public Object pageGoodsList(@RequestParam Map<String, Object> params) {
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        Enterprise enterprise = getLoginEnterprise();
        params.put("enterprise_id",enterprise.getId());
        try {
            List<Store> stores = storeService.getStoreList(params);
            PageInfo<Store> pageInfo = new PageInfo<>(stores);

            LayUiPageParams<Food> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), stores);
            return pageParams;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 插入
     * @param request
     * @param store
     * @return
     */
    @RequestMapping(value = "/noc/store/insertStore")
    @ResponseBody
    public ResultMap insertStore(HttpServletRequest request, Store store) {
        try {
            Enterprise enterprise = getLoginEnterprise();
            store.setEnterpriseId(enterprise.getId());
            return storeService.addStore(store);
        }catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/noc/store/updateStore")
    @ResponseBody
    public ResultMap updateStore(HttpServletRequest request, Store store) {
        try {
            Enterprise enterprise = getLoginEnterprise();
            store.setEnterpriseId(enterprise.getId());
            return storeService.updateStore(store);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/noc/store/delStore")
    @ResponseBody
    public ResultMap delStore(@RequestParam Map<String, Object> params,HttpServletRequest request) {
        return storeService.delStore(Long.parseLong(params.get("id").toString()));
    }
}
