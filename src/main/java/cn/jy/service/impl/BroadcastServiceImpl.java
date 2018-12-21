package cn.jy.service.impl;

import cn.jy.constent.Constent;
import cn.jy.dto.ResultMap;
import cn.jy.entity.Broadcast;
import cn.jy.entity.BroadcastEmployee;
import cn.jy.entity.Enterprise;
import cn.jy.mapper.BroadcastEmployeeMapper;
import cn.jy.mapper.BroadcastMapper;
import cn.jy.service.BroadcastService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionException;

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
                broadcast.setIsRead(2);
            }
        }
        return broadcasts;
    }

    @Override
    public BroadcastEmployee findBroadcastEmployee(BroadcastEmployee input) {
        BroadcastEmployee broadcastEmployee = broadcastEmployeeMapper.findByParams(input);
        return broadcastEmployee;
    }

    @Override
    public ResultMap addBroadcast(Broadcast broadcast) throws Exception {
        return null;
    }

    @Override
    public ResultMap addBroadcastEmployee(BroadcastEmployee broadcastEmployee) throws Exception {
        int adbResult = broadcastEmployeeMapper.insertSelective(broadcastEmployee);
        if(adbResult <=0){
            throw new RuntimeException(Constent.ERROR_BROADCAST_1);
        }
        return ResultMap.success(Constent.SUCCESS_BROADCAST_1);
    }

    @Override
    public ResultMap addBroadcastsEmployee(Map<String, Object> params) throws Exception {
        JSONArray nodes = JSONArray.parseArray(params.get("nodes").toString());
        if(null != nodes) {
            for (int i=0;i<nodes.size();i++) {
                BroadcastEmployee input = new BroadcastEmployee();
                input.setBroadcastId(Long.parseLong(nodes.getJSONObject(i).get("id").toString()));
                input.setEmployeeId(Long.parseLong(params.get("employee_id").toString()));
                BroadcastEmployee broadcastEmployee = broadcastEmployeeMapper.findByParams(input);
                if(null == broadcastEmployee || null == broadcastEmployee.getId()) {
                    int adbResult = broadcastEmployeeMapper.insertSelective(input);
                    if(adbResult <=0){
                        throw new RuntimeException(Constent.ERROR_BROADCAST_1);
                    }
                }
            }
        }
        return ResultMap.success(Constent.SUCCESS_BROADCAST_1);
    }

    @Override
    public ResultMap updateBroadcast(Broadcast broadcast) {
        return null;
    }

    @Override
    public Broadcast getBroadcastById(Long id) {
        return broadcastMapper.selectByPrimaryKey(id);
    }

    @Override
    public BroadcastEmployee getBroadcastEmployeeById(Long id) {
        return broadcastEmployeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultMap delBroadcast(Long id) {
        return null;
    }
}
