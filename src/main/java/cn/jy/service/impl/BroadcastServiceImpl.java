package cn.jy.service.impl;

import cn.jy.dto.ResultMap;
import cn.jy.entity.Broadcast;
import cn.jy.entity.BroadcastEmployee;
import cn.jy.entity.Enterprise;
import cn.jy.mapper.BroadcastEmployeeMapper;
import cn.jy.mapper.BroadcastMapper;
import cn.jy.service.BroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BroadcastServiceImpl implements BroadcastService {

    @Autowired
    private BroadcastMapper broadcastMapper;

    @Autowired
    private BroadcastEmployeeMapper broadcastEmployeeMapper;

    @Override
    public List<Broadcast> getBroadcastList(Map<String, Object> params) throws Exception {
        List<Broadcast> broadcasts = broadcastMapper.selectByParams(params);
        for (Broadcast broadcast :broadcasts) {
            BroadcastEmployee input = new BroadcastEmployee();
            input.setEmployeeId(Long.parseLong(params.get("employee_id").toString()));
            input.setBroadcastId(broadcast.getId());
            BroadcastEmployee broadcastEmployee = broadcastEmployeeMapper.findByParams(input);
            if(null != broadcastEmployee && null != broadcastEmployee.getId()) {
                broadcast.setisRead(2);
            }
        }
        return broadcasts;
    }

    @Override
    public ResultMap addBroadcast(Broadcast broadcast) throws Exception {
        return null;
    }

    @Override
    public ResultMap updateBroadcast(Broadcast broadcast) {
        return null;
    }

    @Override
    public Enterprise getBroadcastById(Long id) {
        return null;
    }

    @Override
    public ResultMap delBroadcast(Long id) {
        return null;
    }
}
