package cn.jy.mapper;

import cn.jy.entity.Food;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FoodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKeyWithBLOBs(Food record);

    int updateByPrimaryKey(Food record);

    List<Food> selectByParams(Map<String, Object> map);

    int delFood(Long id);
}