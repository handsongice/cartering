package cn.jy.mapper;

import cn.jy.entity.FoodParam;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FoodParamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FoodParam record);

    int insertSelective(FoodParam record);

    FoodParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FoodParam record);

    int updateByPrimaryKey(FoodParam record);

    List<FoodParam> selectByParams(Map<String, Object> map);
}