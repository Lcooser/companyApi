package com.infnet.companyApi.controller;


import com.infnet.companyApi.dto.CompanyDto;
import com.infnet.companyApi.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping
    public List<CompanyDto> getAllCompany() {
        return companyService.getAllCompanies();
    }
    @GetMapping("/{id}")
    public CompanyDto getCompanyById(@PathVariable UUID id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
        return companyService.createCompany(companyDto);
    }

    @PutMapping("/{id}")
    public CompanyDto updateCompany(@PathVariable UUID id, @RequestBody CompanyDto companyDto) {
        return companyService.updateCompany(id, companyDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable UUID id) {
        companyService.deleteCompany(id);
    }

}
