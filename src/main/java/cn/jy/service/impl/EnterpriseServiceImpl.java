package cn.jy.service.impl;

import cn.jy.constent.Constent;
import cn.jy.dto.ResultMap;
import cn.jy.entity.Employee;
import cn.jy.entity.Enterprise;
import cn.jy.mapper.EmployeeMapper;
import cn.jy.mapper.EnterpriseMapper;
import cn.jy.service.EnterpriseService;
import cn.jy.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/2 13:40
 */
@Service
@Transactional
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Enterprise> getEnterpriseList(Map<String, Object> params) throws Exception {
        List<Enterprise> enterprises = enterpriseMapper.selectByParams(params);
        return enterprises;
    }

    @Override
    public ResultMap addEnterprise(Enterprise enterprise) throws Exception {
        //用户名验证
        Enterprise input = new Enterprise();
        input.setName(enterprise.getName());
        Enterprise _enterprise = enterpriseMapper.findByParams(input);
        if(_enterprise != null && _enterprise.getId() != null) {
            throw new RuntimeException(Constent.ERROR_ENTERPRISE_1);
        }
        //税号验证
        input = new Enterprise();
        input.setTaxNum(enterprise.getTaxNum());
        _enterprise = enterpriseMapper.findByParams(input);
        if(_enterprise != null && _enterprise.getId() != null) {
            throw new RuntimeException(Constent.ERROR_ENTERPRISE_2);
        }
        //账号验证
        input = new Enterprise();
        input.setLoginCode(enterprise.getLoginCode());
        _enterprise = enterpriseMapper.findByParams(input);
        if(_enterprise != null && _enterprise.getId() != null) {
            throw new RuntimeException(Constent.ERROR_ENTERPRISE_3);
        }
        //设置创建时间
        enterprise.setCreateTime(new Date());
        int dbResult = enterpriseMapper.insertSelective(enterprise);
        if(dbResult <=0 || null == enterprise.getId() || enterprise.getId() <=0){
            throw new RuntimeException(Constent.DB_INSERT_FAILURE);
        }
        //插入员工表
        Employee _employee = new Employee();
        _employee.setLoginCode(enterprise.getLoginCode());
        if(null != enterprise.getPassword()) {
            _employee.setPassword(MD5.md5(enterprise.getPassword()));
        } else {
            _employee.setPassword(MD5.md5("123456"));
        }
        _employee.setEnterpriseId(enterprise.getId());
        _employee.setType(2);
        _employee.setName(enterprise.getName());
        _employee.setIsAdmin(1);
        _employee.setCreateTime(new Date());
        int edbResult = employeeMapper.insertSelective(_employee);
        if(edbResult <=0){
            throw new RuntimeException(Constent.ERROR_ENTERPRISE_4);
        }
        Employee input_employee = new Employee();
        input_employee.setId(_employee.getId());
        input_employee.setCode("E"+_employee.getId());
        input_employee.setUpdateTime(new Date());
        employeeMapper.updateByPrimaryKeySelective(input_employee);
        return ResultMap.success(Constent.DB_INSERT_SUCCESS);
    }

    @Override
    public ResultMap updateEnterprise(Enterprise enterprise) throws Exception {
        //用户名验证
        Enterprise input = new Enterprise();
        input.setName(enterprise.getName());
        input.setId(enterprise.getId());
        Enterprise _enterprise = enterpriseMapper.findByParams(input);
        if(_enterprise != null && _enterprise.getId() != null) {
            return ResultMap.fail(Constent.ERROR_ENTERPRISE_1);
        }
        //税号验证
        input = new Enterprise();
        input.setTaxNum(enterprise.getTaxNum());
        input.setId(enterprise.getId());
        _enterprise = enterpriseMapper.findByParams(input);
        if(_enterprise != null && _enterprise.getId() != null) {
            return ResultMap.fail(Constent.ERROR_ENTERPRISE_2);
        }
        try {
            //设置创建时间
            enterprise.setUpdateTime(new Date());
            int dbResult = enterpriseMapper.updateByPrimaryKeySelective(enterprise);
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
    public Enterprise getEnterpriseById(Long id) {
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(id);
        return enterprise;
    }

    @Override
    public ResultMap delEnterprise(Long id) {
        try {
            int dbResult = enterpriseMapper.delEnterprise(id);
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
