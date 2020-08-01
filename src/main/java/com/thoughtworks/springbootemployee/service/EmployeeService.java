package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployeeList() throws NoSuchDataException;

    Employee getEmployeeById(Integer id) throws NoSuchDataException;

    Page<Employee> getEmployeeByPage(int page, int pageSize) throws NoSuchDataException;

    List<Employee> getEmployeeByGender(String gender) throws NoSuchDataException;

    Employee addEmployee(Employee employee);

    Employee updateEmployeeByID(Integer id, Employee newEmployee) throws NoSuchDataException;

    Boolean deleteEmployeeByID(Integer id) throws NoSuchDataException;
}
