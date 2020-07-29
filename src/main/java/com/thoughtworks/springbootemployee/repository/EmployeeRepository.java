package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    public List<Employee> getAllData() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, 28, "male", "Draymond1", 1000));
        employees.add(new Employee(2, 28, "male", "Draymond2", 100));
        employees.add(new Employee(3, 28, "male", "Draymond3", 10));
        return employees;
    }
}
