package cn.jy.service.impl;

import cn.jy.constent.Constent;
import cn.jy.dto.ResultMap;
import cn.jy.entity.*;
import cn.jy.mapper.*;
import cn.jy.service.FoodService;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodTypeMapper foodTypeMapper;

    @Autowired
    FoodMapper foodMapper;

    @Autowired
    FoodCarouselMapper foodCarouselMapper;

    @Autowired
    FoodSpecMapper foodSpecMapper;

    @Autowired
    FoodStockMapper foodStockMapper;

    @Autowired
    FoodParamMapper foodParamMapper;

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
    public List<FoodCarousel> getCarouselList(Map<String, Object> params) throws Exception {
        List<FoodCarousel> foodCarousels = foodCarouselMapper.selectByParams(params);
        return foodCarousels;
    }

    @Override
    public List<FoodSpec> getSpecList(Map<String, Object> params) throws Exception {
        List<FoodSpec> foodSpecs = foodSpecMapper.selectByParams(params);
        return foodSpecs;
    }

    @Override
    public List<FoodStock> getStockList(Map<String, Object> params) throws Exception {
        List<FoodStock> foodStocks = foodStockMapper.selectByParams(params);
        return foodStocks;
    }

    @Override
    public List<FoodParam> getParamList(Map<String, Object> params) throws Exception {
        List<FoodParam> foodParams = foodParamMapper.selectByParams(params);
        return foodParams;
    }

    @Override
    public ResultMap addFoodType(FoodType foodType) throws Exception {
        try {
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
            return ResultMap.fail(Constent.DB_INSERT_FAILURE);
        }
    }

    @Override
    public ResultMap addFood(Food food) throws Exception {
        try {
            food.setCreateTime(new Date());
            int dbResult = foodMapper.insertSelective(food);
            if(dbResult <=0 || null == food.getId()){
                throw new RuntimeException(Constent.DB_INSERT_FAILURE);
            }
            //插入轮播
            JSONArray carousels = JSONArray.parseArray(food.getCarousels());
            if(null != carousels) {
                for (int i=0;i<carousels.size();i++) {
                    FoodCarousel foodCarousel = new FoodCarousel();
                    foodCarousel.setFoodId(food.getId());
                    foodCarousel.setPic(carousels.getJSONObject(i).get("filepath").toString());
                    foodCarousel.setIndex(carousels.getJSONObject(i).get("index").toString());
                    int dbResult1 = foodCarouselMapper.insertSelective(foodCarousel);
                    if(dbResult1 <=0){
                        throw new RuntimeException("轮播插入失败！");
                    }
                }
            }
            //插入规格
            JSONArray specs = JSONArray.parseArray(food.getSpecs());
            if(null != specs) {
                for (int i=0;i<specs.size();i++) {
                    FoodSpec foodSpec = new FoodSpec();
                    foodSpec.setFoodId(food.getId());
                    foodSpec.setName(specs.getJSONObject(i).get("name").toString());
                    foodSpec.setPic(specs.getJSONObject(i).get("pic").toString());
                    foodSpec.setKey(specs.getJSONObject(i).get("key").toString());
                    foodSpec.setCreateTime(new Date());
                    int dbResult2 = foodSpecMapper.insertSelective(foodSpec);
                    if(dbResult2 <=0){
                        throw new RuntimeException("规格插入失败！");
                    }
                }
            }
            //插入库存
            JSONArray stocks = JSONArray.parseArray(food.getStocks());
            if(null != stocks) {
                for (int i=0;i<stocks.size();i++) {
                    String skeystr = stocks.getJSONObject(i).get("skeys").toString();
                    JSONArray skeys = JSONArray.parseArray(skeystr);
                    List<String> specIds = new ArrayList<>();
                    List<String> skeysList = new ArrayList<>();
                    for (int j=0;j<skeys.size();j++) {
                        skeysList.add(skeys.get(j).toString());
                        String[] sa = skeys.get(j).toString().split(":");
                        Map<String, Object> params = new HashMap<>();
                        params.put("food_id",food.getId());
                        params.put("name",sa[0]);
                        params.put("key",sa[1]);
                        List<FoodSpec> foodSpecs = foodSpecMapper.selectByParams(params);
                        if(null != foodSpecs && foodSpecs.size() > 0) {
                            specIds.add(String.valueOf(foodSpecs.get(0).getId()));
                        }
                    }
                    FoodStock foodStock = new FoodStock();
                    foodStock.setFoodId(food.getId());
                    foodStock.setFoodSpecIds(StringUtils.join(specIds,","));
                    foodStock.setSkeys(StringUtils.join(skeysList,","));
                    foodStock.setFoodSpecNames(stocks.getJSONObject(i).get("keystr").toString());
                    foodStock.setPrice(stocks.getJSONObject(i).getBigDecimal("price"));
                    foodStock.setNum(stocks.getJSONObject(i).getInteger("num"));
                    foodStock.setPic(stocks.getJSONObject(i).getString("pic"));
                    foodStock.setCreateTime(new Date());
                    int dbResult3 = foodStockMapper.insertSelective(foodStock);
                    if(dbResult3 <=0){
                        throw new RuntimeException("库存插入失败！");
                    }
                }
            }
            //插入参数
            JSONArray params = JSONArray.parseArray(food.getParams());
            if(null != params) {
                for (int i=0;i<params.size();i++) {
                    FoodParam foodParam = new FoodParam();
                    foodParam.setFoodId(food.getId());
                    foodParam.setName(params.getJSONObject(i).get("name").toString());
                    foodParam.setKey(params.getJSONObject(i).get("key").toString());
                    foodParam.setCreateTime(new Date());
                    int dbResult4 = foodParamMapper.insertSelective(foodParam);
                    if(dbResult4 <=0){
                        throw new RuntimeException("规格参数失败！");
                    }
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return ResultMap.success(Constent.DB_INSERT_SUCCESS);
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
    public ResultMap updateFood(Food food) throws Exception {
        try {
            food.setUpdateTime(new Date());
            int dbResult = foodMapper.updateByPrimaryKeySelective(food);
            if(dbResult <=0 ){
                throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
            }
            //插入轮播
            foodCarouselMapper.deleteByFoodId(food.getId());
            JSONArray carousels = JSONArray.parseArray(food.getCarousels());
            if(null != carousels) {
                for (int i=0;i<carousels.size();i++) {
                    FoodCarousel foodCarousel = new FoodCarousel();
                    foodCarousel.setFoodId(food.getId());
                    foodCarousel.setPic(carousels.getJSONObject(i).get("filepath").toString());
                    foodCarousel.setIndex(carousels.getJSONObject(i).get("index").toString());
                    int dbResult1 = foodCarouselMapper.insertSelective(foodCarousel);
                    if(dbResult1 <=0){
                        throw new RuntimeException("轮播插入失败！");
                    }
                }
            }
            //插入规格
            foodSpecMapper.deleteByFoodId(food.getId());
            JSONArray specs = JSONArray.parseArray(food.getSpecs());
            if(null != specs) {
                for (int i=0;i<specs.size();i++) {
                    FoodSpec foodSpec = new FoodSpec();
                    foodSpec.setFoodId(food.getId());
                    foodSpec.setName(specs.getJSONObject(i).get("name").toString());
                    foodSpec.setPic(specs.getJSONObject(i).get("pic").toString());
                    foodSpec.setKey(specs.getJSONObject(i).get("key").toString());
                    foodSpec.setCreateTime(new Date());
                    int dbResult2 = foodSpecMapper.insertSelective(foodSpec);
                    if(dbResult2 <=0){
                        throw new RuntimeException("规格插入失败！");
                    }
                }
            }
            //插入库存
            foodStockMapper.deleteByFoodId(food.getId());
            JSONArray stocks = JSONArray.parseArray(food.getStocks());
            if(null != stocks) {
                for (int i=0;i<stocks.size();i++) {
                    String skeystr = stocks.getJSONObject(i).get("skeys").toString();
                    JSONArray skeys = JSONArray.parseArray(skeystr);
                    List<String> specIds = new ArrayList<>();
                    List<String> skeysList = new ArrayList<>();
                    for (int j=0;j<skeys.size();j++) {
                        skeysList.add(skeys.get(j).toString());
                        String[] sa = skeys.get(j).toString().split(":");
                        Map<String, Object> params = new HashMap<>();
                        params.put("food_id",food.getId());
                        params.put("name",sa[0]);
                        params.put("key",sa[1]);
                        List<FoodSpec> foodSpecs = foodSpecMapper.selectByParams(params);
                        if(null != foodSpecs && foodSpecs.size() > 0) {
                            specIds.add(String.valueOf(foodSpecs.get(0).getId()));
                        }
                    }
                    FoodStock foodStock = new FoodStock();
                    foodStock.setFoodId(food.getId());
                    foodStock.setFoodSpecIds(StringUtils.join(specIds,","));
                    foodStock.setSkeys(StringUtils.join(skeysList,","));
                    foodStock.setFoodSpecNames(stocks.getJSONObject(i).get("keystr").toString());
                    foodStock.setPrice(stocks.getJSONObject(i).getBigDecimal("price"));
                    foodStock.setNum(stocks.getJSONObject(i).getInteger("num"));
                    foodStock.setPic(stocks.getJSONObject(i).getString("pic"));
                    foodStock.setCreateTime(new Date());
                    int dbResult3 = foodStockMapper.insertSelective(foodStock);
                    if(dbResult3 <=0){
                        throw new RuntimeException("库存插入失败！");
                    }
                }
            }
            //插入参数
            foodParamMapper.deleteByFoodId(food.getId());
            JSONArray params = JSONArray.parseArray(food.getParams());
            if(null != params) {
                for (int i=0;i<params.size();i++) {
                    FoodParam foodParam = new FoodParam();
                    foodParam.setFoodId(food.getId());
                    foodParam.setName(params.getJSONObject(i).get("name").toString());
                    foodParam.setKey(params.getJSONObject(i).get("key").toString());
                    foodParam.setCreateTime(new Date());
                    int dbResult4 = foodParamMapper.insertSelective(foodParam);
                    if(dbResult4 <=0){
                        throw new RuntimeException("规格参数失败！");
                    }
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
    }

    @Override
    public FoodType getFoodTypeById(Long id) {
        FoodType foodType = foodTypeMapper.selectByPrimaryKey(id);
        return foodType;
    }

    @Override
    public Food getFoodById(Long id) {
        Food food = foodMapper.selectByPrimaryKey(id);
        return food;
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

    @Override
    public ResultMap delFood(Long id) {
        try {
            int dbResult = foodMapper.delFood(id);
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
