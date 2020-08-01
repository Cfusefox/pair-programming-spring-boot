package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
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

}
