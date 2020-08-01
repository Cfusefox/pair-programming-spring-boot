package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private EmployeeMapper employeeMapper = new EmployeeMapper();

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    private List<Employee> initEmployeeList() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, 28, "male", "Draymond", 1000));
        employees.add(new Employee(2, 38, "female", "Shao", 1000));
        employees.add(new Employee(3, 38, "female", "Shao", 1000));
        employees.add(new Employee(4, 38, "female", "Shao", 1000));
        employees.add(new Employee(5, 38, "female", "Shao", 1000));
        employees.add(new Employee(6, 38, "female", "Shao", 1000));
        return employees;
    }

    @GetMapping
    public List<Employee> getEmployeeList() throws NoSuchDataException {
        return employeeService.getEmployeeList();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeByID(@PathVariable int id) throws NoSuchDataException {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<Employee> getEmployeeListByPage(int page, int pageSize) throws NoSuchDataException {
        return employeeService.getEmployeeByPage(page, pageSize);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getEmployeeByGender(String gender) throws NoSuchDataException {
        return employeeService.getEmployeeByGender(gender);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest newEmployee) {
        return employeeService.addEmployee(employeeMapper.mapEmployee(newEmployee));
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployeeByID(@PathVariable int id, @RequestBody EmployeeRequest newEmployee) throws NoSuchDataException {
        return employeeService.updateEmployeeByID(id, employeeMapper.mapEmployee(newEmployee));
    }

    @DeleteMapping("/{id}")
    public Boolean deleteEmployeeByID(@PathVariable int id) throws NoSuchDataException {
        return employeeService.deleteEmployeeByID(id);
    }
}
