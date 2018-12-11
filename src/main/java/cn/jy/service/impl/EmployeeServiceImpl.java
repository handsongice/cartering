package cn.jy.service.impl;

import cn.jy.constent.Constent;
import cn.jy.dto.ResultMap;
import cn.jy.entity.Employee;
import cn.jy.mapper.EmployeeMapper;
import cn.jy.service.EmployeeService;
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
        return null;
    }

    @Override
    public ResultMap updateEmployee(Employee employee) {
        return null;
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
    public ResultMap updateMyPassword(Employee employee) {
        return null;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    @Override
    public ResultMap delEmployee(Long id) {
        return null;
    }
}
