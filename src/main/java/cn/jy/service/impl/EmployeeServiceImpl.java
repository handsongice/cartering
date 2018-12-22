package cn.jy.service.impl;

import cn.jy.constent.Constent;
import cn.jy.dto.ResultMap;
import cn.jy.entity.Employee;
import cn.jy.mapper.EmployeeMapper;
import cn.jy.service.EmployeeService;
import cn.jy.util.MD5;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee getEmployee(String username) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("username",username);
        List<Employee> employees = employeeMapper.selectByParams(params);
        if(null != employees && employees.size() > 0) {
            return  employees.get(0);
        }
        return null;
    }

    @Override
    public List<Employee> getEmployeeList(Map<String, Object> params) throws Exception {
        List<Employee> employees = employeeMapper.selectByParams(params);
        return employees;
    }

    @Override
    public ResultMap addEmployee(Employee employee) throws Exception {
        Employee input = new Employee();
        input.setLoginCode(employee.getLoginCode());
        Employee _employee = employeeMapper.findByParams(input);
        if(null != _employee && null != _employee.getId()) {
            throw new RuntimeException(Constent.ERROR_ENTERPRISE_2);
        }
        employee.setPassword(MD5.md5(employee.getPassword()));
        employee.setCreateTime(new Date());
        int edbResult = employeeMapper.insertSelective(employee);
        if(edbResult <=0 || null == employee.getId()){
            throw new RuntimeException(Constent.DB_INSERT_FAILURE);
        }
        Employee updateData = new Employee();
        updateData.setUpdateTime(new Date());
        updateData.setId(employee.getId());
        updateData.setCode("E"+employee.getId());
        employeeMapper.updateByPrimaryKeySelective(updateData);
        return ResultMap.success(Constent.DB_INSERT_SUCCESS);
    }

    @Override
    public ResultMap updateEmployee(Employee employee) throws Exception {
        Employee input = new Employee();
        input.setLoginCode(employee.getLoginCode());
        input.setId(employee.getId());
        Employee _employee = employeeMapper.findByParams(input);
        if(null != _employee && null != _employee.getId()) {
            throw new RuntimeException(Constent.ERROR_ENTERPRISE_2);
        }
        employee.setUpdateTime(new Date());
        if(StringUtil.isNotEmpty(employee.getPassword())) {
            employee.setPassword(MD5.md5(employee.getPassword()));
        } else {
            employee.setPassword(null);
        }
        int dbResult = employeeMapper.updateByPrimaryKeySelective(employee);
        if(dbResult <=0){
            throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
        }
        return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
    }

    @Override
    public ResultMap updateMyInfo(Employee employee) {
        employee.setUpdateTime(new Date());
        //设置创建时间
        int dbResult = employeeMapper.updateByPrimaryKeySelective(employee);
        if(dbResult <=0){
            throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
        }
        return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
    }

    @Override
    public ResultMap updateMyPassword(Map<String, Object> params) {
        Employee _employee = employeeMapper.selectByPrimaryKey(Long.parseLong(params.get("id").toString()));
        if(!_employee.getPassword().equals(MD5.md5(params.get("oldPassword").toString()))) {
            throw new RuntimeException(Constent.SUCCESS_EMPLOYEE_1);
        }
        Employee input = new Employee();
        input.setId(Long.parseLong(params.get("id").toString()));
        input.setPassword(MD5.md5(params.get("password").toString()));
        input.setUpdateTime(new Date());
        //设置创建时间
        int dbResult = employeeMapper.updateByPrimaryKeySelective(input);
        if(dbResult <=0){
            throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
        }
        return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    @Override
    public ResultMap delEmployee(Long id) {
        try {
            int dbResult = employeeMapper.delEmployee(id);
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
