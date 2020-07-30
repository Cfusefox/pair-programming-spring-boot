package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void tearDown() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    void should_return_employees_when_find_all_employees_given_nothing() throws Exception {
        //given
        Employee employee = new Employee(1, 28, "male", "Draymond1", 1000);
        employeeRepository.save(employee);

        //when then
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].age").value("28"))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].name").value("Draymond1"))
                .andExpect(jsonPath("$[0].salary").value(1000));

    }

    @Test
    void should_return_certain_employee_when_find_employee_by_id_given_employee_id() throws Exception {
        //given
        Integer id = 1;
        Employee employee = new Employee(1, 28, "male", "Draymond1", 1000);
        Employee savedEmployee = employeeRepository.save(employee);

        //when then
        mockMvc.perform(get("/employees/" + savedEmployee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.age").value(28))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.name").value("Draymond1"))
                .andExpect(jsonPath("$.salary").value(1000));

    }

    @Test
    void should_return_employees_when_page_query_given_page_pag() throws Exception {
        //given
        Employee employeeA = new Employee(1, 28, "male", "Draymond1", 1000);
        Employee employeeB = new Employee(2, 28, "male", "Draymond2", 1020);
        List<Employee> employees = Arrays.asList(employeeA, employeeB);
        Employee employee1 = employeeRepository.save(employeeA);
        Employee employee2 = employeeRepository.save(employeeB);

        //when then
        mockMvc.perform(get("/employees?page=0&pageSize=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").isNumber())
                .andExpect(jsonPath("$.content[0].age").value(28))
                .andExpect(jsonPath("$.content[0].gender").value("male"))
                .andExpect(jsonPath("$.content[0].name").value("Draymond1"))
                .andExpect(jsonPath("$.content[0].salary").value(1000));
    }
}

