package com.infnet.companyApi.service;

import com.infnet.companyApi.domain.Company;
import com.infnet.companyApi.dto.CompanyDto;
import com.infnet.companyApi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<CompanyDto> getAllCompanies(){
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDto getCompanyById(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
        return convertToDto(company);
    }

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = convertToEntity(companyDto);
        Company savedCompany = companyRepository.save(company);
        return convertToDto(savedCompany);
    }

    @Override
    public CompanyDto updateCompany(UUID id, CompanyDto companyDto) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
        existingCompany.setName(companyDto.getName());
        existingCompany.setCnpj(companyDto.getCnpj());
        existingCompany.setEmail(companyDto.getEmail());
        existingCompany.setPhone(companyDto.getPhone());
        existingCompany.setAddress(companyDto.getAddress());
        existingCompany.setActive(companyDto.isActive());

        Company updatedCompany = companyRepository.save(existingCompany);
        return convertToDto(updatedCompany);
    }

    @Override
    public void deleteCompany(UUID id) {
        companyRepository.deleteById(id);
    }

    private CompanyDto convertToDto(Company company) {
        CompanyDto dto = new CompanyDto();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setCnpj(company.getCnpj());
        dto.setEmail(company.getEmail());
        dto.setPhone(company.getPhone());
        dto.setAddress(company.getAddress());
        dto.setActive(company.isActive());

        return dto;
    }

    private Company convertToEntity(CompanyDto dto) {
        Company company = new Company();
        company.setId(dto.getId());
        company.setName(dto.getName());
        company.setCnpj(dto.getCnpj());
        company.setEmail(dto.getEmail());
        company.setPhone(dto.getPhone());
        company.setAddress(dto.getAddress());
        company.setActive(dto.isActive());

        return company;
    }

}
