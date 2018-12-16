package cn.jy.service.impl;

import cn.jy.constent.Constent;
import cn.jy.dto.ResultMap;
import cn.jy.entity.Food;
import cn.jy.entity.FoodType;
import cn.jy.mapper.FoodMapper;
import cn.jy.mapper.FoodTypeMapper;
import cn.jy.service.FoodService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodTypeMapper foodTypeMapper;

    @Autowired
    FoodMapper foodMapper;

    @Override
    public List<FoodType> getFoodTypeList(Map<String, Object> params) throws Exception {
        List<FoodType> foodTypes = foodTypeMapper.selectByParams(params);
        return foodTypes;
    }

    @Override
    public List<Food> getFoodList(Map<String, Object> params) throws Exception {
        List<Food> foods = foodMapper.selectByParams(params);
        return foods;
    }

    @Override
    public ResultMap addFoodType(FoodType foodType) throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //设置创建时间
            foodType.setCreateTime(new Date());
            Long maxSort = foodTypeMapper.maxSort(foodType.getParentId());
            if(null != maxSort) {
                foodType.setSort(maxSort.longValue()+1);
            } else {
                foodType.setSort(new Long(1));
            }
            String maxTreeCode = foodTypeMapper.maxTreeCode(foodType.getParentId());
            if(!StringUtils.isEmpty(maxTreeCode)) {
                foodType.setTreeCode(String.valueOf(Integer.parseInt(maxTreeCode)+1));
            } else {
                if(foodType.getParentId() == 0) {
                    foodType.setTreeCode("10001");
                }
                HashMap<String, Object> first = new HashMap<String, Object>();
                first.put("id",foodType.getParentId());
                List<FoodType> action0 = foodTypeMapper.selectByParams(first);
                if(null != action0 && action0.size() > 0) {
                    foodType.setTreeCode(action0.get(0).getTreeCode()+"10001");
                }
            }
            int dbResult = foodTypeMapper.insertSelective(foodType);
            if(dbResult >0){
                return ResultMap.success(Constent.DB_INSERT_SUCCESS);
            }else{
                return ResultMap.fail(Constent.DB_INSERT_FAILURE);
            }
        }catch (Exception e){
            // 工作单号 字段唯一 错误判断
            int index =e.getMessage().indexOf("Duplicate");
            if(index >= 0){
                return ResultMap.fail(Constent.DB_UNIQUE_GZDH_FAILURE);
            }else {
                return ResultMap.fail(Constent.DB_INSERT_FAILURE);
            }
        }
    }

    @Override
    public ResultMap addFood(Food food) throws Exception {
        return null;
    }

    @Override
    public ResultMap updateFoodType(FoodType foodType) {
        try {
            foodType.setUpdateTime(new Date());
            int dbResult = foodTypeMapper.updateByPrimaryKeySelective(foodType);
            if(dbResult >0){
                return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
            }else{
                return ResultMap.fail(Constent.DB_UPDATE_FAILURE);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultMap.fail(Constent.DB_UPDATE_FAILURE);
        }
    }

    @Override
    public FoodType getFoodTypeById(Long id) {
        FoodType foodType = foodTypeMapper.selectByPrimaryKey(id);
        return foodType;
    }

    @Override
    public ResultMap delFoodType(Long id) {
        try {
            int dbResult = foodTypeMapper.delFoodType(id);
            if(dbResult >0){
                return ResultMap.success(Constent.DB_DELETE_SUCCESS);
            }else{
                return ResultMap.fail(Constent.DB_DELETE_FAILURE);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultMap.fail(Constent.DB_DELETE_FAILURE);
        }
    }
}
