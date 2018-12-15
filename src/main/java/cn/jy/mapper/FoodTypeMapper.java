package cn.jy.mapper;

import cn.jy.entity.FoodType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FoodTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FoodType record);

    int insertSelective(FoodType record);

    FoodType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FoodType record);

    int updateByPrimaryKey(FoodType record);

    List<FoodType> selectByParams(Map<String, Object> map);

    int delFoodType(Long id);
}