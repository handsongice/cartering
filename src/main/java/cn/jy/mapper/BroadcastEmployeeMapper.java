package cn.jy.mapper;

import cn.jy.entity.BroadcastEmployee;

public interface BroadcastEmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BroadcastEmployee record);

    int insertSelective(BroadcastEmployee record);

    BroadcastEmployee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BroadcastEmployee record);

    int updateByPrimaryKey(BroadcastEmployee record);
}