package cn.jy.controller.enterprise;

import cn.jy.controller.BaseController;
import cn.jy.dto.ResultMap;
import cn.jy.entity.Enterprise;
import cn.jy.entity.Food;
import cn.jy.entity.FoodType;
import cn.jy.pojo.LayUiPageParams;
import cn.jy.service.FoodService;
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

@Controller
public class FoodController extends BaseController {

    @Autowired
    FoodService foodService;

    /**
     * 类型页面
     * @return
     */
    @RequestMapping(value = "/main/food/type")
    public String foodType() {
        return "enterprise/food/type";
    }
    /**
     * 菜品页面
     * @return
     */
    @RequestMapping(value = "/main/food/food")
    public String food() {
        return "enterprise/food/food";
    }
    /**
     * 添加类型页面
     * @return
     */
    @RequestMapping(value = "/main/food/addType")
    public String addType() {
        return "enterprise/food/addType";
    }
    /**
     * 添加菜品页面
     * @return
     */
    @RequestMapping(value = "/main/food/addFood")
    public String addFood() {
        return "enterprise/food/addFood";
    }
    /**
     * 添加菜品规格页面
     * @return
     */
    @RequestMapping(value = "/main/food/addFoodSpec")
    public String addFoodSpec() {
        return "enterprise/food/addFoodSpec";
    }
    /**
     * 编辑菜单页面
     * @param params
     * @return
     */
    @RequestMapping(value = "/main/food/editType")
    public ModelAndView editType(@RequestParam Map<String, Object> params) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("enterprise/food/editType");
        Long _id = Long.parseLong(params.get("id").toString());
        FoodType foodType = foodService.getFoodTypeById(_id);
        mv.addObject("foodType", foodType);
        return mv;
    }
    /**
     * 编辑规格页面
     * @param params
     * @return
     */
    @RequestMapping(value = "/main/food/editFoodSpec")
    public ModelAndView editFoodSpec(@RequestParam Map<String, Object> params) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("enterprise/food/editFoodSpec");
        Long _id = Long.parseLong(params.get("id").toString());
        mv.addObject("id", _id);
        return mv;
    }
    /**
     * 列表
     * @param params
     * @return
     */
    @RequestMapping("/noc/food/pageTypeList")
    @ResponseBody
    public Object pageTypeList(@RequestParam Map<String, Object> params) {
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        Enterprise enterprise = getLoginEnterprise();
        params.put("enterprise_id",enterprise.getId());
        try {
            List<FoodType> foodTypes = foodService.getFoodTypeList(params);
            PageInfo<FoodType> pageInfo = new PageInfo<>(foodTypes);

            LayUiPageParams<FoodType> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), foodTypes);
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
    @RequestMapping("/noc/food/pageFoodList")
    @ResponseBody
    public Object pageFoodList(@RequestParam Map<String, Object> params) {
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        Enterprise enterprise = getLoginEnterprise();
        params.put("enterprise_id",enterprise.getId());
        try {
            List<Food> foods = foodService.getFoodList(params);
            PageInfo<Food> pageInfo = new PageInfo<>(foods);

            LayUiPageParams<Food> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), foods);
            return pageParams;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 类型树
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/noc/food/allTypeList")
    @ResponseBody
    public Object allTypeList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        try {
            Enterprise enterprise = getLoginEnterprise();
            params.put("enterprise_id",enterprise.getId());
            List<FoodType> foodTypes = foodService.getFoodTypeList(params);
            return foodTypes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/noc/food/insertType")
    @ResponseBody
    public ResultMap insertType(HttpServletRequest request, FoodType foodType) {
        Enterprise enterprise = getLoginEnterprise();
        foodType.setEnterpriseId(enterprise.getId());
        try {
            return foodService.addFoodType(foodType);
        } catch (Exception e) {
            return ResultMap.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/noc/food/updateType")
    @ResponseBody
    public ResultMap updateType(HttpServletRequest request, FoodType foodType) {
        return foodService.updateFoodType(foodType);
    }

    @RequestMapping(value = "/noc/food/delType")
    @ResponseBody
    public ResultMap delType(@RequestParam Map<String, Object> params,HttpServletRequest request) {
        return foodService.delFoodType(Long.parseLong(params.get("id").toString()));
    }
}
