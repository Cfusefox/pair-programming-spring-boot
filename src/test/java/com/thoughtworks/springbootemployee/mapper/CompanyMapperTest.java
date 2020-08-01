package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CompanyMapperTest {

    @Test
    void should_return_company_when_map_company_given_company_request() {
        //given
        CompanyMapper companyMapper = new CompanyMapper();
        CompanyRequest companyRequest = new CompanyRequest(1, "OOCL", 0, new ArrayList<>());

        //when
        Company company = companyMapper.mapCompany(companyRequest);

        //then
        assertEquals(companyRequest.getId(), company.getId());
        assertEquals(companyRequest.getCompanyName(), company.getCompanyName());
        assertEquals(companyRequest.getEmployeeNumber(), company.getEmployeeNumber());
        assertEquals(companyRequest.getEmployees(), company.getEmployees());
    }

    @Test
    void should_return_companyResponse_when_map_company_response_given_company() {
        //given
        CompanyMapper companyMapper = new CompanyMapper();
        Company company = new Company(1, "OOCL", 0, new ArrayList<>());

        //when
        CompanyResponse companyResponse = companyMapper.mapCompanyResponse(company);

        //then
        assertEquals(company.getId(), companyResponse.getId());
        assertEquals(company.getCompanyName(), companyResponse.getCompanyName());
        assertEquals(company.getEmployeeNumber(), companyResponse.getEmployeeNumber());
        assertEquals(company.getEmployees(), companyResponse.getEmployees());
    }
}
