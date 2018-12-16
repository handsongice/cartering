package cn.jy.mapper;

import cn.jy.entity.FoodCarousel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FoodCarouselMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FoodCarousel record);

    int insertSelective(FoodCarousel record);

    FoodCarousel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FoodCarousel record);

    int updateByPrimaryKey(FoodCarousel record);

    List<FoodCarousel> selectByParams(Map<String, Object> map);
}