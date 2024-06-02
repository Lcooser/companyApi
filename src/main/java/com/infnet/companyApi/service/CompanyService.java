package com.infnet.companyApi.service;

import com.infnet.companyApi.domain.Company;
import com.infnet.companyApi.dto.CompanyDto;

import java.util.List;
import java.util.UUID;

public interface CompanyService {

    List<CompanyDto> getAllCompanies();
    CompanyDto getCompanyById(UUID id);
    CompanyDto createCompany(CompanyDto companyDto);
    CompanyDto updateCompany(UUID id, CompanyDto companyDto);
    void deleteCompany(UUID id);

}
