package cn.jy.service;

import cn.jy.dto.ResultMap;
import cn.jy.entity.Broadcast;
import cn.jy.entity.Enterprise;

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
     * 插入数据
     * @param broadcast
     * @return
     */
    ResultMap addBroadcast(Broadcast broadcast) throws Exception;

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
    Enterprise getBroadcastById(Long id);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    ResultMap delBroadcast(Long id);
}
