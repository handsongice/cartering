package cn.jy.service;

import cn.jy.dto.ResultMap;
import cn.jy.entity.Employee;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/10/26 15:01
 */
public interface EmployeeService {
    /**
     * 员工信息
     * @param username
     * @return
     * @throws Exception
     */
    Employee getEmployee(String username) throws Exception;
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<Employee> getEmployeeList(Map<String, Object> params) throws Exception;

    /**
     * 插入数据
     * @param employee
     * @return
     */
    ResultMap addEmployee(Employee employee) throws Exception;

    /**
     * 更新数据
     * @param employee
     * @return
     */
    ResultMap updateEmployee(Employee employee);

    /**
     * 更新个人信息
     * @param employee
     * @return
     */
    ResultMap updateMyInfo(Employee employee);

    /**
     * 更新密码
     * @param employee
     * @return
     */
    ResultMap updateMyPassword(Employee employee);
    /**
     * 获取信息
     * @param id
     * @return
     */
    Employee getEmployeeById(Long id);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    ResultMap delEmployee(Long id);
}
