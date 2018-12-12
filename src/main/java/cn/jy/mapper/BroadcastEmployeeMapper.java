package cn.jy.mapper;

import cn.jy.entity.BroadcastEmployee;
import org.springframework.stereotype.Repository;

@Repository
public interface BroadcastEmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BroadcastEmployee record);

    int insertSelective(BroadcastEmployee record);

    BroadcastEmployee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BroadcastEmployee record);

    int updateByPrimaryKey(BroadcastEmployee record);

    BroadcastEmployee findByParams(BroadcastEmployee record);
}