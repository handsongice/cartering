package cn.jy.mapper;

import cn.jy.entity.FoodSpec;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FoodSpecMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FoodSpec record);

    int insertSelective(FoodSpec record);

    FoodSpec selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FoodSpec record);

    int updateByPrimaryKey(FoodSpec record);

    List<FoodSpec> selectByParams(Map<String, Object> map);

    int deleteByFoodId(Long food_id);
}