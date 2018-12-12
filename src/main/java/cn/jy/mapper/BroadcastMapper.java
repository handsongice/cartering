package cn.jy.mapper;

import cn.jy.entity.Broadcast;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BroadcastMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Broadcast record);

    int insertSelective(Broadcast record);

    Broadcast selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Broadcast record);

    int updateByPrimaryKey(Broadcast record);

    List<Broadcast> selectByParams(Map<String, Object> map);

    int delBroadcast(Long id);
}