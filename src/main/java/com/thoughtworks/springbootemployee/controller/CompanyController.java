package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyServiceImpl companyService;

    private CompanyMapper companyMapper = new CompanyMapper();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyResponse> getAllCompanies() throws NoSuchDataException {
        return companyService.getCompanyList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponse getCompaniesById(@PathVariable int id) throws NoSuchDataException {
        return companyService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/employees")
    public List<EmployeeResponse> getEmployeesByCompanyId(@PathVariable int id) throws NoSuchDataException {
        return companyService.findEmployeesByCompanyId(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    @ResponseStatus(HttpStatus.OK)
    public Page<Company> getCompaniesByPage(int page, int pageSize) throws NoSuchDataException {
        return companyService.getCompaniesByPage(page, pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse addCompany(@RequestBody CompanyRequest newCompany) throws IllegalOperationException {
        return companyService.addCompany(companyMapper.mapCompany(newCompany));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponse updateCompanyByID(@PathVariable int id, @RequestBody CompanyRequest newCompany) throws IllegalOperationException, NoSuchDataException {
        return companyService.updateCompanyByID(id,companyMapper.mapCompany(newCompany));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllEmployeesOfTheCompanyByID(@PathVariable int id) throws IllegalOperationException, NoSuchDataException {
        companyService.deleteCompanyByID(id);
    }
}
