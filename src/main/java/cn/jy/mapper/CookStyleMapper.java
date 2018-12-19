package cn.jy.mapper;

import cn.jy.entity.CookStyle;

public interface CookStyleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CookStyle record);

    int insertSelective(CookStyle record);

    CookStyle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CookStyle record);

    int updateByPrimaryKey(CookStyle record);
}