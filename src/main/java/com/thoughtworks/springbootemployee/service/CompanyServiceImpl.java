package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl {
    private CompanyRepository repository;
    private CompanyMapper companyMapper = new CompanyMapper();
    private EmployeeMapper employeeMapper = new EmployeeMapper();

    public CompanyServiceImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<CompanyResponse> getCompanyList() throws NoSuchDataException {
        List<Company> companies = repository.findAll();
        if (companies.isEmpty()) {
            throw new NoSuchDataException();
        }
        return companies.stream().map(company -> companyMapper.mapCompanyResponse(company)).collect(Collectors.toList());
    }

    public CompanyResponse findById(Integer id) throws NoSuchDataException {
        Company company = repository.findById(id).orElse(null);
        if (company == null) {
            throw new NoSuchDataException();
        }
        return companyMapper.mapCompanyResponse(company);
    }

    public List<EmployeeResponse> findEmployeesByCompanyId(Integer id) throws NoSuchDataException {
        List<Employee> employees = findById(id).getEmployees();
        if(employees == null || employees.isEmpty()) {
            throw new NoSuchDataException();
        }
        return employees.stream().map(employee -> employeeMapper.mapEmployeeResponse(employee)).collect(Collectors.toList());
    }

    public Page<Company> getCompaniesByPage(int page, int pageSize) throws NoSuchDataException {
        Page<Company> companies = repository.findAll(PageRequest.of(page, pageSize));
        if(companies.isEmpty()) {
            throw new NoSuchDataException();
        }
        return companies;
    }

    public CompanyResponse addCompany(Company company) throws IllegalOperationException {
        Company addedCompany = repository.save(company);
        if(addedCompany.getId() == 0) {
            throw new IllegalOperationException();
        }
        return companyMapper.mapCompanyResponse(addedCompany);
    }

    public CompanyResponse updateCompanyByID(Integer id, Company newCompany) throws IllegalOperationException, NoSuchDataException {
        if(id != newCompany.getId()) {
            throw new IllegalOperationException();
        }
        CompanyResponse company = this.findById(id);
        if (company != null) {
            company.setEmployees(newCompany.getEmployees());
            company.setCompanyName(newCompany.getCompanyName());
            company.setEmployeeNumber(newCompany.getEmployeeNumber());
            return company;
        } else {
            throw new IllegalOperationException();
        }
    }

    public Boolean deleteCompanyByID(Integer id) throws IllegalOperationException, NoSuchDataException {
        CompanyResponse company = this.findById(id);
        if (company != null) {
            company.setEmployees(new ArrayList<>());
            repository.deleteById(id);
            return true;
        } else {
            throw new IllegalOperationException();
        }
    }
}
