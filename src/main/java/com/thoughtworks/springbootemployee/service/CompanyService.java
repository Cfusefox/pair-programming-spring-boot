package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanyList() throws NoSuchDataException;

    Company findById(Integer id);

    List<Employee> findEmployeesByCompanyId(Integer id) throws NoSuchDataException;

    Page<Company> getCompaniesByPage(int page, int pageSize) throws NoSuchDataException;

    Company addCompany(Company company) throws IllegalOperationException;

    Company updateCompanyByID(Integer id, Company newCompany) throws IllegalOperationException;

    Boolean deleteCompanyByID(Integer id) throws IllegalOperationException;
}
