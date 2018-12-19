package cn.jy.mapper;

import cn.jy.entity.Format;

public interface FormatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Format record);

    int insertSelective(Format record);

    Format selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Format record);

    int updateByPrimaryKey(Format record);
}