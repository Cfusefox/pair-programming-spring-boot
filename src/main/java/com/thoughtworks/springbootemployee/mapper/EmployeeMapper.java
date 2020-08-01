package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.BeanUtils;

public class EmployeeMapper {
    public Employee mapEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setId(employeeRequest.getId());
        employee.setName(employeeRequest.getName());
        employee.setGender(employeeRequest.getGender());
        employee.setAge(employeeRequest.getAge());
        employee.setSalary(employeeRequest.getSalary());
        return employee;
    }

    public EmployeeResponse mapEmployeeResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employee, employeeResponse);
        return employeeResponse;
    }
}
