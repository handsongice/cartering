package cn.jy.service;

import cn.jy.dto.ResultMap;
import cn.jy.entity.Broadcast;
import cn.jy.entity.Enterprise;
import cn.jy.entity.Message;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/2 11:48
 */
public interface MessageService {
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<Message> getMessageList(Map<String, Object> params) throws Exception;

    /**
     * 插入数据
     * @param message
     * @return
     */
    ResultMap addMessage(Message message) throws Exception;

    /**
     * 更新数据
     * @param message
     * @return
     */
    ResultMap updateMessage(Message message);

    /**
     * 获取信息
     * @param id
     * @return
     */
    Enterprise getMessageById(Long id);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    ResultMap delMessage(Long id);
}
