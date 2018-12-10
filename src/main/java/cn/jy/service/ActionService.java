package cn.jy.service;

import cn.jy.dto.ResultMap;
import cn.jy.entity.Action;
import cn.jy.entity.Employee;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/10/29 15:19
 */
public interface ActionService {
    /**
     * 左侧菜单
     * @param employee
     * @return
     * @throws Exception
     */
    List<Action> leftMenus(Employee employee) throws Exception;

    /**
     * 菜单列表
     * @param params
     * @return
     * @throws Exception
     */
    List<Action> getActionList(Map<String, Object> params) throws Exception;

    /**
     * 插入菜单数据
     * @param action
     * @return
     */
    ResultMap insertAction(Action action);

    /**
     * 更新菜单数据
     * @param action
     * @return
     */
    ResultMap updateAction(Action action);

    /**
     * 获取菜单
     * @param id
     * @return
     */
    Action getActionById(Long id);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    ResultMap delAction(Long id);
}
