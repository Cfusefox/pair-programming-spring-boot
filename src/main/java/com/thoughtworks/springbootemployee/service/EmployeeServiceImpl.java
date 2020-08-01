package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getEmployeeList() throws NoSuchDataException {
        List<Employee> employees = repository.findAll();
        if (employees.isEmpty()) {
            throw new NoSuchDataException();
        }
        return employees;
    }

    @Override
    public Employee getEmployeeById(Integer id) throws NoSuchDataException {
        Employee employee = repository.findById(id).orElse(null);
        if(employee == null) {
            throw new NoSuchDataException();
        }
        return employee;
    }

    @Override
    public Page<Employee> getEmployeeByPage(int page, int pageSize) throws NoSuchDataException {
        Page<Employee> employees = repository.findAll(PageRequest.of(page, pageSize));
        if(employees.isEmpty()) {
            throw new NoSuchDataException();
        }
        return employees;
    }

    @Override
    public List<Employee> getEmployeeByGender(String gender) throws NoSuchDataException {
        List<Employee> employees = repository.findByGender(gender);
        if(employees.isEmpty()) {
            throw new NoSuchDataException();
        }
        return employees;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee updateEmployeeByID(Integer id, Employee newEmployee) throws NoSuchDataException {
        Employee employee = this.getEmployeeById(id);
        BeanUtils.copyProperties(newEmployee, employee);
        return repository.save(employee);

    }

    @Override
    public Boolean deleteEmployeeByID(Integer id) throws NoSuchDataException {
        Employee employee = this.getEmployeeById(id);
        repository.deleteById(id);
        return true;
    }
}
