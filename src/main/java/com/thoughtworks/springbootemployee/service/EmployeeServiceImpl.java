package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl {

    private EmployeeRepository repository;
    private EmployeeMapper employeeMapper = new EmployeeMapper();
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getEmployeeList() throws NoSuchDataException {
        List<Employee> employees = repository.findAll();
        if (employees.isEmpty()) {
            throw new NoSuchDataException();
        }
        return employees;
    }

    public EmployeeResponse getEmployeeById(Integer id) throws NoSuchDataException {
        Employee employee = repository.findById(id).orElse(null);
        if(employee == null) {
            throw new NoSuchDataException();
        }
        return employeeMapper.mapEmployeeResponse(employee);
    }

    public Page<Employee> getEmployeeByPage(int page, int pageSize) throws NoSuchDataException {
        Page<Employee> employees = repository.findAll(PageRequest.of(page, pageSize));
        if(employees.isEmpty()) {
            throw new NoSuchDataException();
        }
        return employees;
    }

    public List<Employee> getEmployeeByGender(String gender) throws NoSuchDataException {
        List<Employee> employees = repository.findByGender(gender);
        if(employees.isEmpty()) {
            throw new NoSuchDataException();
        }
        return employees;
    }

    public EmployeeResponse addEmployee(Employee employee) {
        return employeeMapper.mapEmployeeResponse(repository.save(employee));
    }

    public EmployeeResponse updateEmployeeByID(Integer id, Employee newEmployee) throws NoSuchDataException {
        EmployeeResponse employee = this.getEmployeeById(id);
        BeanUtils.copyProperties(newEmployee, employee);
        return employee;

    }

    public Boolean deleteEmployeeByID(Integer id) throws NoSuchDataException {
        EmployeeResponse employee = this.getEmployeeById(id);
        repository.deleteById(id);
        return true;
    }
}
