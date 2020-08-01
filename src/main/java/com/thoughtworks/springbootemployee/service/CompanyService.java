package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanyList() throws NoSuchDataException;

    CompanyResponse findById(Integer id) throws NoSuchDataException;

    List<Employee> findEmployeesByCompanyId(Integer id) throws NoSuchDataException;

    Page<Company> getCompaniesByPage(int page, int pageSize) throws NoSuchDataException;

    CompanyResponse addCompany(Company company) throws IllegalOperationException;

    CompanyResponse updateCompanyByID(Integer id, Company newCompany) throws IllegalOperationException, NoSuchDataException;

    Boolean deleteCompanyByID(Integer id) throws IllegalOperationException, NoSuchDataException;
}
