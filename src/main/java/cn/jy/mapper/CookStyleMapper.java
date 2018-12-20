package cn.jy.mapper;

import cn.jy.entity.CookStyle;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Component
public interface CookStyleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CookStyle record);

    int insertSelective(CookStyle record);

    CookStyle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CookStyle record);

    int updateByPrimaryKey(CookStyle record);

    List<CookStyle> selectByParams(Map<String, Object> map);
}