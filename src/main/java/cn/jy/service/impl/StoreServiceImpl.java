package cn.jy.service.impl;

import cn.jy.constent.Constent;
import cn.jy.dto.ResultMap;
import cn.jy.entity.CookStyle;
import cn.jy.entity.Employee;
import cn.jy.entity.Format;
import cn.jy.entity.Store;
import cn.jy.mapper.CookStyleMapper;
import cn.jy.mapper.EmployeeMapper;
import cn.jy.mapper.FormatMapper;
import cn.jy.mapper.StoreMapper;
import cn.jy.service.StoreService;
import cn.jy.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/12/20 13:28
 */
@Service
@Transactional
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreMapper storeMapper;

    @Autowired
    CookStyleMapper cookStyleMapper;

    @Autowired
    FormatMapper formatMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Store> getStoreList(Map<String, Object> params) throws Exception {
        List<Store> stores = storeMapper.selectByParams(params);
        return stores;
    }

    @Override
    public List<CookStyle> getCookStyleList(Map<String, Object> params) throws Exception {
        List<CookStyle> cookStyles = cookStyleMapper.selectByParams(params);
        return cookStyles;
    }

    @Override
    public List<Format> getFormatList(Map<String, Object> params) throws Exception {
        List<Format> formats = formatMapper.selectByParams(params);
        return formats;
    }

    @Override
    public ResultMap addStore(Store store) throws Exception {
        Store check = new Store();
        check.setEnterpriseId(store.getEnterpriseId());
        check.setName(store.getName());
        Store _store = storeMapper.findByParams(check);
        if(null != _store && null != _store.getId()) {
            throw new RuntimeException(Constent.ERROR_STORE_1);
        }
        store.setCreateTime(new Date());
        int dbResult = storeMapper.insertSelective(store);
        if(dbResult <=0 || null == store.getId()){
            throw new RuntimeException(Constent.DB_INSERT_FAILURE);
        }
        Employee input = new Employee();
        input.setLoginCode(store.getLoginCode());
        Employee employee = employeeMapper.findByParams(input);
        if(null != employee && null != employee.getId()) {
            throw new RuntimeException(Constent.ERROR_ENTERPRISE_2);
        }
        input.setEnterpriseId(store.getEnterpriseId());
        input.setStoreId(store.getId());
        input.setName(store.getLoginCode());
        input.setPhone(store.getAdminPhone());
        input.setType(3);
        input.setIsAdmin(1);
        input.setPassword(MD5.md5(store.getPassword()));
        input.setCreateTime(new Date());
        int edbResult = employeeMapper.insertSelective(input);
        if(edbResult <=0 || null == input.getId()){
            throw new RuntimeException(Constent.DB_INSERT_FAILURE);
        }
        Employee updateData = new Employee();
        updateData.setUpdateTime(new Date());
        updateData.setId(input.getId());
        updateData.setCode("E"+input.getId());
        employeeMapper.updateByPrimaryKeySelective(updateData);
        return ResultMap.success(Constent.DB_INSERT_SUCCESS);
    }

    @Override
    public ResultMap updateStore(Store store) {
        Store check = new Store();
        check.setEnterpriseId(store.getEnterpriseId());
        check.setName(store.getName());
        check.setId(store.getId());
        Store _store = storeMapper.findByParams(check);
        if(null != _store && null != _store.getId()) {
            throw new RuntimeException(Constent.ERROR_STORE_1);
        }
        store.setUpdateTime(new Date());
        int dbResult = storeMapper.updateByPrimaryKeySelective(store);
        if(dbResult <=0 || null == store.getId()){
            throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
        }
        return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
    }

    @Override
    public Store getStoreById(Long id) {
        Store store = storeMapper.selectByPrimaryKey(id);
        return store;
    }

    @Override
    public ResultMap delStore(Long id) {
        try {
            int dbResult = storeMapper.delStore(id);
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
