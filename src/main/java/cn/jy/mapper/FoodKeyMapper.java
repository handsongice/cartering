package cn.jy.mapper;

import cn.jy.entity.FoodKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FoodKeyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FoodKey record);

    int insertSelective(FoodKey record);

    FoodKey selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FoodKey record);

    int updateByPrimaryKey(FoodKey record);

    List<FoodKey> selectByParams(Map<String, Object> map);
}