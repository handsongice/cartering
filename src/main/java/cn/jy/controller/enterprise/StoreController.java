package cn.jy.controller.enterprise;

import cn.jy.controller.BaseController;
import cn.jy.dto.ResultMap;
import cn.jy.entity.*;
import cn.jy.pojo.LayUiPageParams;
import cn.jy.service.StoreService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    /**
     * 门店页面
     * @return
     */
    @RequestMapping(value = "/main/store/store")
    public String store() {
        return "enterprise/store/store";
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
}
