package cn.jy.mapper;

import cn.jy.entity.Store;

public interface StoreMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKeyWithBLOBs(Store record);

    int updateByPrimaryKey(Store record);
}