package cn.jy.mapper;

import cn.jy.entity.GoodsParam;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GoodsParamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsParam record);

    int insertSelective(GoodsParam record);

    GoodsParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsParam record);

    int updateByPrimaryKey(GoodsParam record);

    List<GoodsParam> selectByParams(Map<String, Object> map);

    int deleteByGoodsId(Long goods_id);
}