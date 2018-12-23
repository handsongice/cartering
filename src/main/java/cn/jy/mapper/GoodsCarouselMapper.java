package cn.jy.mapper;

import cn.jy.entity.GoodsCarousel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GoodsCarouselMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsCarousel record);

    int insertSelective(GoodsCarousel record);

    GoodsCarousel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsCarousel record);

    int updateByPrimaryKey(GoodsCarousel record);

    List<GoodsCarousel> selectByParams(Map<String, Object> map);

    int deleteByGoodsId(Long goods_id);
}