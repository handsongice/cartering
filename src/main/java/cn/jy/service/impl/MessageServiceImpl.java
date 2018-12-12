package cn.jy.service.impl;

import cn.jy.dto.ResultMap;
import cn.jy.entity.Enterprise;
import cn.jy.entity.Message;
import cn.jy.mapper.MessageMapper;
import cn.jy.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;


    @Override
    public List<Message> getMessageList(Map<String, Object> params) throws Exception {
        List<Message> messages = messageMapper.selectByParams(params);
        return messages;
    }

    @Override
    public ResultMap addMessage(Message message) throws Exception {
        return null;
    }

    @Override
    public ResultMap updateMessage(Message message) {
        return null;
    }

    @Override
    public Enterprise getMessageById(Long id) {
        return null;
    }

    @Override
    public ResultMap delMessage(Long id) {
        return null;
    }

}
