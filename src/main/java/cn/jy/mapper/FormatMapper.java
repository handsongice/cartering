package cn.jy.mapper;

import cn.jy.entity.Format;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Component
public interface FormatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Format record);

    int insertSelective(Format record);

    Format selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Format record);

    int updateByPrimaryKey(Format record);

    List<Format> selectByParams(Map<String, Object> map);
}