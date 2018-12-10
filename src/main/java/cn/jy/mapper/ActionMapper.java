package cn.jy.mapper;

import cn.jy.entity.Action;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ActionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Action record);

    int insertSelective(Action record);

    Action selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Action record);

    int updateByPrimaryKey(Action record);

    List<Action> selectByParams(Map<String, Object> map);

    List<Action> selectRoleByParams(Map<String, Object> map);

    Long maxSort(Long parentId);

    String maxTreeCode(Long parentId);

    int delAction(Long id);
}