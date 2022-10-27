package com.czb.ssm.controller;

import com.czb.ssm.mappers.EmployeeMapper;
import com.czb.ssm.pojo.Employee;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class Show {
    @Autowired
    private EmployeeMapper employeeMapper;

    @RequestMapping(value = "employee", method = RequestMethod.GET)
    public String getAllEmployee(Model model) {
        List<Employee> employees = employeeMapper.selectByExample(null);
        model.addAttribute("employees", employees);
        return "employees";
    }

    @RequestMapping(value = "/employee/page/{pageNum}", method = RequestMethod.GET)
    public String getEmployeePage(@PathVariable("pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 15);
        List<Employee> employees = employeeMapper.selectByExample(null);
        PageInfo<Employee> page = new PageInfo<Employee>(employees, 5);
        model.addAttribute("employees", page);
        return "employees";
    }
}
