package cn.jy.mapper;

import cn.jy.entity.RoleAction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface RoleActionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleAction record);

    int insertSelective(RoleAction record);

    RoleAction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleAction record);

    int updateByPrimaryKey(RoleAction record);

    RoleAction findByParams(RoleAction record);

    int deleteByParams(RoleAction record);
}