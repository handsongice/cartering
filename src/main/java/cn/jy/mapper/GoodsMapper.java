package cn.jy.mapper;

import cn.jy.entity.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> selectByParams(Map<String, Object> map);

    int delGoods(Long id);
}