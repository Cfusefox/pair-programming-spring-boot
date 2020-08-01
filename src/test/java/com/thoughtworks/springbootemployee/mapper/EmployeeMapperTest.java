package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeMapperTest {
    private EmployeeMapper employeeMapper;
    @BeforeEach
    void initMapper() {
        employeeMapper = new EmployeeMapper();
    }

    @Test
    void should_return_company_when_map_company_given_company_request() {
        //given
        EmployeeRequest employeeRequest = new EmployeeRequest(1, 18, "female", "York", 1000);

        //when
        Employee employee = employeeMapper.mapEmployee(employeeRequest);

        //then
        assertEquals(employeeRequest.getId(), employee.getId());
        assertEquals(employeeRequest.getAge(), employee.getAge());
        assertEquals(employeeRequest.getName(), employee.getName());
        assertEquals(employeeRequest.getGender(), employee.getGender());
        assertEquals(employeeRequest.getSalary(), employee.getSalary());
    }

    @Test
    void should_return_company_response_when_map_company_response_given_company() {
        //given
        Employee employee = new Employee(1, 18, "female", "York", 1000);

        //when
        EmployeeResponse employeeResponse = employeeMapper.mapEmployeeResponse(employee);

        //then
        assertEquals(employee.getId(), employeeResponse.getId());
        assertEquals(employee.getAge(), employeeResponse.getAge());
        assertEquals(employee.getGender(), employeeResponse.getGender());
        assertEquals(employee.getName(), employeeResponse.getName());
        assertEquals(employee.getSalary(), employeeResponse.getSalary());
    }

}
