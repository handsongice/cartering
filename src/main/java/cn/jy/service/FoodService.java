package cn.jy.service;

import cn.jy.dto.ResultMap;
import cn.jy.entity.Food;
import cn.jy.entity.FoodType;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/2 11:48
 */
public interface FoodService {
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<FoodType> getFoodTypeList(Map<String, Object> params) throws Exception;
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<Food> getFoodList(Map<String, Object> params) throws Exception;
    /**
     * 插入数据
     * @param foodType
     * @return
     */
    ResultMap addFoodType(FoodType foodType) throws Exception;

    /**
     * 插入数据
     * @param food
     * @return
     */
    ResultMap addFood(Food food) throws Exception;

    /**
     * 更新数据
     * @param foodType
     * @return
     */
    ResultMap updateFoodType(FoodType foodType);

    /**
     * 获取信息
     * @param id
     * @return
     */
    FoodType getFoodTypeById(Long id);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    ResultMap delFoodType(Long id);
}
