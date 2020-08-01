package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    private EmployeeRepository repository = mock(EmployeeRepository.class);
    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl(repository);

    @Test
    void should_return_employees_when_get() throws NoSuchDataException {
        //given
        List<Employee> mockedEmployees = new ArrayList<>();
        mockedEmployees.add(new Employee(1, 28, "male", "Draymond1", 1000));
        mockedEmployees.add(new Employee(2, 28, "male", "Draymond2", 100));
        mockedEmployees.add(new Employee(3, 28, "male", "Draymond3", 10));
        mockedEmployees.add(new Employee(4, 28, "male", "Draymond4", 40));
        mockedEmployees.add(new Employee(5, 28, "female", "Draymond5", 30));
        mockedEmployees.add(new Employee(6, 28, "male", "Draymond6", 20));
        when(repository.findAll()).thenReturn(mockedEmployees);
        //when
        List<Employee> employees = employeeService.getEmployeeList();

        //then
        Assertions.assertEquals(6, employees.size());
    }

    @Test
    void should_return_employee_when_get_given_employeeId() throws NoSuchDataException {
        //given
        Integer id = 1;
        when(repository.findById(id)).thenReturn(java.util.Optional.of(new Employee(1, 28, "male", "Draymond1", 1000)));
        //when
        Employee employee = employeeService.getEmployeeById(id);
        //then
        Assertions.assertEquals(id, employee.getId());
    }

    @Test
    void should_return_employees_when_query_by_page_given_page_and_page_size() throws NoSuchDataException {
        //given
        int page = 1;
        int pageSize = 5;
        int firstEmployeeIdInPage1 = 1;
        List<Employee> mockedEmployees = new ArrayList<>();
        mockedEmployees.add(new Employee(1, 28, "male", "Draymond1", 1000));
        mockedEmployees.add(new Employee(2, 28, "male", "Draymond2", 100));
        mockedEmployees.add(new Employee(3, 28, "male", "Draymond3", 10));
        mockedEmployees.add(new Employee(4, 28, "male", "Draymond4", 40));
        mockedEmployees.add(new Employee(5, 28, "female", "Draymond5", 30));
        when(repository.findAll(PageRequest.of(page, pageSize))).thenReturn(new PageImpl<>(mockedEmployees));
        //when
        List<Employee> employees = employeeService.getEmployeeByPage(page, pageSize).getContent();
        //then
        Assertions.assertEquals(pageSize, employees.size());
        Assertions.assertEquals(firstEmployeeIdInPage1, employees.get(0).getId());
    }

    @Test
    void should_return_employees_when_query_by_gender_given_gender() throws NoSuchDataException {
        //given
        String gender = "male";
        List<Employee> mockedEmployees = new ArrayList<>();
        mockedEmployees.add(new Employee(1, 28, "male", "Draymond1", 1000));
        mockedEmployees.add(new Employee(2, 28, "male", "Draymond2", 100));
        mockedEmployees.add(new Employee(3, 28, "male", "Draymond3", 10));
        mockedEmployees.add(new Employee(4, 28, "male", "Draymond4", 40));
        when(repository.findByGender(gender)).thenReturn(mockedEmployees);
        //when
        List<Employee> employees = employeeService.getEmployeeByGender(gender);
        //then
        Assertions.assertEquals(4, employees.size());
    }

    @Test
    void should_return_employee_when_add_employees_given_employees() {
        //given
        Employee employee = new Employee(6, 28, "male", "Draymond6", 20);
        when(repository.save(employee)).thenReturn(employee);
        //when
        Employee returnValue = employeeService.addEmployee(employee);
        //then
        Assertions.assertEquals(employee.getId(), returnValue.getId());
    }

    @Test
    void should_return_updated_employee_when_update_employee_given_employee() throws NoSuchDataException {
        //given
        Employee employee = new Employee(6, 28, "male", "Draymond6", 20);
        when(repository.save(employee)).thenReturn(employee);
        given(repository.findById(6)).willReturn(java.util.Optional.of(employee));
        //when
        Employee returnValue = employeeService.updateEmployeeByID(6, employee);
        //then
        Assertions.assertEquals("male", returnValue.getGender());
    }

    @Test
    void should_return_deleted_employee_when_delete_employee_given_employee_id() throws NoSuchDataException {
        //given
        EmployeeRepository mockEmployeeReposition = mock(EmployeeRepository.class);
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(mockEmployeeReposition);
        given(mockEmployeeReposition.findById(1)).willReturn(java.util.Optional.of(new Employee(1, 28, "male", "dray", 2222)));
        Integer id = 1;
        //when
        employeeService.deleteEmployeeByID(id);
        //then
        Mockito.verify(mockEmployeeReposition).deleteById(1);
    }
}
