package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository repository;

    public CompanyServiceImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Company> getCompanyList() throws NoSuchDataException {
        List<Company> companies = repository.findAll();
        if (companies.isEmpty()) {
            throw new NoSuchDataException();
        }
        return companies;
    }

    @Override
    public Company findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> findEmployeesByCompanyId(Integer id) throws NoSuchDataException {
        List<Employee> employees = findById(id).getEmployees();
        if(employees == null || employees.isEmpty()) {
            throw new NoSuchDataException();
        }
        return employees;
    }

    @Override
    public Page<Company> getCompaniesByPage(int page, int pageSize) throws NoSuchDataException {
        Page<Company> companies = repository.findAll(PageRequest.of(page, pageSize));
        if(companies.isEmpty()) {
            throw new NoSuchDataException();
        }
        return companies;
    }

    @Override
    public Company addCompany(Company company) throws IllegalOperationException {
        Company addedCompany = repository.save(company);
        if(addedCompany.getId() == 0) {
            throw new IllegalOperationException();
        }
        return addedCompany;
    }

    @Override
    public Company updateCompanyByID(Integer id, Company newCompany) throws IllegalOperationException {
        Company company = this.findById(id);
        if (company != null) {
            company.setEmployees(newCompany.getEmployees());
            company.setCompanyName(newCompany.getCompanyName());
            company.setEmployeeNumber(newCompany.getEmployeeNumber());
            return repository.save(company);
        } else {
            throw new IllegalOperationException();
        }
    }

    @Override
    public Boolean deleteCompanyByID(Integer id) throws IllegalOperationException {
        Company company = this.findById(id);
        if (company != null) {
            repository.deleteById(id);
            return true;
        } else {
            throw new IllegalOperationException();
        }
    }
}
