package cn.jy.service.impl;

import cn.jy.constent.Constent;
import cn.jy.dto.ResultMap;
import cn.jy.entity.Action;
import cn.jy.entity.Employee;
import cn.jy.entity.RoleAction;
import cn.jy.mapper.ActionMapper;
import cn.jy.mapper.RoleActionMapper;
import cn.jy.service.ActionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ActionServiceImpl implements ActionService {
    @Autowired
    private ActionMapper actionMapper;

    @Autowired
    private RoleActionMapper roleActionMapper;

    @Override
    public List<Action> leftMenus(Employee employee) throws Exception {
        HashMap<String, Object> first = new HashMap<String, Object>();

        first.put("type",employee.getType());
        first.put("parentId",0);
        first.put("isMenu",1);
        first.put("display",1);
        List<Action> action0 = null;
        if(1 != employee.getIsAdmin()) {
            first.put("employeeId",employee.getId());
            action0 = actionMapper.selectRoleByParams(first);
        } else {
            action0 = actionMapper.selectByParams(first);
        }
        if(null == action0 || action0.size()<=0) {
            return null;
        }
        for (Action a0:action0 ) {
            HashMap<String, Object> second = new HashMap<String, Object>();
            second.put("type",employee.getType());
            second.put("parentId",a0.getId());
            second.put("isMenu",1);
            second.put("display",1);
            List<Action> action1 = null;
            if(1 != employee.getIsAdmin()) {
                second.put("employeeId",employee.getId());
                action1 = actionMapper.selectRoleByParams(second);
            } else {
                action1 = actionMapper.selectByParams(second);
            }
            for (Action a1:action1 ) {
                HashMap<String, Object> three = new HashMap<String, Object>();
                three.put("type",employee.getType());
                three.put("parentId",a1.getId());
                three.put("isMenu",1);
                three.put("display",1);
                List<Action> action2 = null;
                if(1 != employee.getIsAdmin()) {
                    three.put("employeeId",employee.getId());
                    action2 = actionMapper.selectRoleByParams(three);
                } else {
                    action2 = actionMapper.selectByParams(three);
                }
                if(null != action2 && action2.size()>0) {
                    a1.setHas_children(1);
                    a1.setChildren(action2);
                }
            }
            if(null != action1 && action1.size()>0) {
                a0.setHas_children(1);
                a0.setChildren(action1);
            }
        }
        return action0;
    }

    @Override
    public List<Action> getActionList(Map<String, Object> params) throws Exception {
        List<Action> actions = actionMapper.selectByParams(params);
        if(null != params.get("role_id") && !StringUtils.isEmpty(params.get("role_id").toString())) {
            for (Action action:actions) {
                RoleAction input = new RoleAction();
                input.setActionId(action.getId());
                input.setRoleId(Long.parseLong(params.get("role_id").toString()));
                RoleAction roleAction = roleActionMapper.findByParams(input);
                if(null != roleAction && null != roleAction.getId()) {
                    action.setChecked(true);
                }
            }
        }
        return actions;
    }

    @Override
    public ResultMap insertAction(Action action) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //设置创建时间
            action.setCreateTime(df.format(new Date()));
            Long maxSort = actionMapper.maxSort(action.getParentId());
            if(null != maxSort) {
                action.setSort(maxSort.longValue()+1);
            } else {
                action.setSort(new Long(1));
            }
            String maxTreeCode = actionMapper.maxTreeCode(action.getParentId());
            if(!StringUtils.isEmpty(maxTreeCode)) {
                action.setTreeCode(String.valueOf(Integer.parseInt(maxTreeCode)+1));
            } else {
                if(action.getParentId() == 0) {
                    action.setTreeCode("10001");
                }
                HashMap<String, Object> first = new HashMap<String, Object>();
                first.put("id",action.getParentId());
                List<Action> action0 = actionMapper.selectByParams(first);
                if(null != action0 && action0.size() > 0) {
                    action.setTreeCode(action0.get(0).getTreeCode()+"10001");
                }
            }
            int dbResult = actionMapper.insert(action);
            if(dbResult >0){
                return ResultMap.success(Constent.DB_INSERT_SUCCESS);
            }else{
                return ResultMap.fail(Constent.DB_INSERT_FAILURE);
            }
        }catch (Exception e){
            // 工作单号 字段唯一 错误判断
            int index =e.getMessage().indexOf("Duplicate");
            if(index >= 0){
                return ResultMap.fail(Constent.DB_UNIQUE_GZDH_FAILURE);
            }else {
                return ResultMap.fail(Constent.DB_INSERT_FAILURE);
            }
        }
    }

    @Override
    public ResultMap updateAction(Action action) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //设置创建时间
            action.setUpdateTime(df.format(new Date()));
            int dbResult = actionMapper.updateByPrimaryKey(action);
            if(dbResult >0){
                return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
            }else{
                return ResultMap.fail(Constent.DB_UPDATE_FAILURE);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultMap.fail(Constent.DB_UPDATE_FAILURE);
        }
    }

    @Override
    public Action getActionById(Long id) {
        Action action = actionMapper.selectByPrimaryKey(id);
        return action;
    }

    @Override
    public ResultMap delAction(Long id) {
        try {
            int dbResult = actionMapper.delAction(id);
            if(dbResult >0){
                return ResultMap.success(Constent.DB_DELETE_SUCCESS);
            }else{
                return ResultMap.fail(Constent.DB_DELETE_FAILURE);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultMap.fail(Constent.DB_DELETE_FAILURE);
        }
    }
}
