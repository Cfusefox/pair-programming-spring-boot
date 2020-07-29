package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private List<Company> initCompanies() {
        List<Company> companies = new ArrayList<>();
        companies.add(
                new Company(1,
                        "OOCL",
                        1,
                        Arrays.asList(
                                new Employee(1, 28, "male", "OOCL1", 1000),
                                new Employee(2, 28, "male", "OOCL2", 1000),
                                new Employee(3, 28, "male", "OOCL3", 1000)
                        )
                )
        );
        companies.add(
                new Company(2,
                        "CargoSmart",
                        1,
                        Arrays.asList(
                                new Employee(1, 28, "male", "CargoSmart1", 1000),
                                new Employee(2, 28, "male", "CargoSmart2", 1000),
                                new Employee(3, 28, "male", "CargoSmart3", 1000)
                        )
                )
        );
        companies.add(
                new Company(3,
                        "KFC",
                        1,
                        Arrays.asList(
                                new Employee(1, 28, "male", "KFC1", 1000),
                                new Employee(2, 28, "male", "KFC2", 1000),
                                new Employee(3, 28, "male", "KFC3", 1000)
                        )
                )
        );
        companies.add(
                new Company(4,
                        "MC",
                        1,
                        Arrays.asList(
                                new Employee(1, 28, "male", "MC1", 1000),
                                new Employee(2, 28, "male", "MC2", 1000),
                                new Employee(3, 28, "male", "MC3", 1000)
                        )
                )
        );
        companies.add(
                new Company(5,
                        "alibaba",
                        1,
                        Arrays.asList(
                                new Employee(1, 28, "male", "alibaba1", 1000),
                                new Employee(2, 28, "male", "alibaba2", 1000),
                                new Employee(3, 28, "male", "alibaba3", 1000)
                        )
                )
        );
        companies.add(
                new Company(6,
                        "Tencent",
                        1,
                        Arrays.asList(
                                new Employee(1, 28, "male", "Tencent1", 1000),
                                new Employee(2, 28, "male", "Tencent2", 1000),
                                new Employee(3, 28, "male", "Tencent3", 1000)
                        )
                )
        );


        return companies;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return initCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompaniesById(@PathVariable int id) {
        return initCompanies().stream().filter(company -> company.getId() == id).findFirst().get();
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable int id) {
        return initCompanies().stream().filter(company -> company.getId() == id).findFirst().get().getEmployees();
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getCompaniesByPage(int page, int pageSize) {
        List<Company> companies = initCompanies();
        int startIndex = (page - 1) * pageSize;
        return companies.subList(startIndex, startIndex + pageSize);
    }

    @PostMapping
    public Company addCompany(@RequestBody Company newCompany) {
        List<Company> companies = initCompanies();
        companies.add(newCompany);
        return companies.stream().filter(company -> company.getId() == newCompany.getId()).findFirst().get();
    }

    @PutMapping("/{id}")
    public Company updateCompanyByID(@PathVariable int id, @RequestBody Company newCompany) {
        List<Company> companies = initCompanies();
        int index = 0;
        for(Company company : companies){
            if(company.getId() == id){
                index = companies.indexOf(company);
            }
        }
        companies.remove(index);
        companies.add(newCompany);

        return companies.stream().filter(company -> company.getId() == id).findFirst().get();
    }

    @DeleteMapping("/{id}")
    public Company deleteAllEmployeesOfTheCompanyByID(@PathVariable int id) {
        List<Company> companies = initCompanies();
        Company thisCompany = companies.stream().filter(company -> company.getId() == id).findFirst().get();
        thisCompany.getEmployees().clear();
//        thisCompany.setEmployees(new ArrayList<Employee>());
        return thisCompany;
    }
}
