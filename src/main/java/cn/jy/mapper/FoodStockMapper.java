package cn.jy.mapper;

import cn.jy.entity.FoodStock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FoodStockMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FoodStock record);

    int insertSelective(FoodStock record);

    FoodStock selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FoodStock record);

    int updateByPrimaryKey(FoodStock record);

    List<FoodStock> selectByParams(Map<String, Object> map);
}