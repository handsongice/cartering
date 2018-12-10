package cn.jy.controller.basic;

import cn.jy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
}
