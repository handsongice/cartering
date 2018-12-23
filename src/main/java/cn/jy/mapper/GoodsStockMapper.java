package cn.jy.mapper;

import cn.jy.entity.GoodsStock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GoodsStockMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsStock record);

    int insertSelective(GoodsStock record);

    GoodsStock selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsStock record);

    int updateByPrimaryKey(GoodsStock record);

    List<GoodsStock> selectByParams(Map<String, Object> map);

    int deleteByGoodsId(Long goods_id);
}