package cn.jy.service;

import cn.jy.dto.ResultMap;
import cn.jy.entity.Broadcast;
import cn.jy.entity.BroadcastEmployee;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/2 11:48
 */
public interface BroadcastService {
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<Broadcast> getBroadcastList(Map<String, Object> params) throws Exception;

    /**
     * find
     * @param broadcastEmployee
     * @return
     * @throws Exception
     */
    BroadcastEmployee findBroadcastEmployee(BroadcastEmployee broadcastEmployee);
    /**
     * 插入数据
     * @param broadcast
     * @return
     */
    ResultMap addBroadcast(Broadcast broadcast) throws Exception;

    /**
     * 插入数据
     * @param broadcastEmployee
     * @return
     */
    ResultMap addBroadcastEmployee(BroadcastEmployee broadcastEmployee) throws Exception;

    /**
     * 批量插入数据
     * @param params
     * @return
     */
    ResultMap addBroadcastsEmployee(Map<String, Object> params) throws Exception;

    /**
     * 更新数据
     * @param broadcast
     * @return
     */
    ResultMap updateBroadcast(Broadcast broadcast);

    /**
     * 获取信息
     * @param id
     * @return
     */
    Broadcast getBroadcastById(Long id);

    /**
     * 获取信息
     * @param id
     * @return
     */
    BroadcastEmployee getBroadcastEmployeeById(Long id);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    ResultMap delBroadcast(Long id);
}
