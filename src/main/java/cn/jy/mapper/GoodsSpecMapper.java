package cn.jy.mapper;

import cn.jy.entity.GoodsSpec;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GoodsSpecMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsSpec record);

    int insertSelective(GoodsSpec record);

    GoodsSpec selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsSpec record);

    int updateByPrimaryKey(GoodsSpec record);

    List<GoodsSpec> selectByParams(Map<String, Object> map);

    int deleteByGoodsId(Long goods_id);
}