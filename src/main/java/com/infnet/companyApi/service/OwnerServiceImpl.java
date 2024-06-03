package com.infnet.companyApi.service;

import com.infnet.companyApi.domain.Company;
import com.infnet.companyApi.domain.Owner;
import com.infnet.companyApi.dto.OwnerDto;
import com.infnet.companyApi.repository.CompanyRepository;
import com.infnet.companyApi.repository.OwnerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<OwnerDto> getAllOwners() {
        List<Owner> owners = ownerRepository.findAll();
        return owners.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OwnerDto getOwnerById(UUID id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + id));
        return convertToDto(owner);
    }

    @Override
    public OwnerDto createOwner(OwnerDto ownerDto) {
        Owner owner = convertToEntity(ownerDto);
        Owner savedOwner = ownerRepository.save(owner);
        return convertToDto(savedOwner);
    }

    @Override
    public OwnerDto updateOwner(UUID id, OwnerDto ownerDto) {
        try {
            Owner existingOwner = ownerRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found with id: " + id));
            existingOwner.setName(ownerDto.getName());
            existingOwner.setAddress(ownerDto.getAddress());
            existingOwner.setEmail(ownerDto.getEmail());
            existingOwner.setPhone(ownerDto.getPhone());
            existingOwner.setCpf(ownerDto.getCpf());
            existingOwner.setSalary(ownerDto.getSalary());

            Owner updatedOwner = ownerRepository.save(existingOwner);
            return convertToDto(updatedOwner);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid owner data", e);
        }
    }

    @Override
    public void deleteOwner(UUID id) {
        try {
            ownerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found with id: " + id);
        }
    }

    private OwnerDto convertToDto(Owner owner) {
        OwnerDto dto = new OwnerDto();
        dto.setId(owner.getId());
        dto.setName(owner.getName());
        dto.setCpf(owner.getCpf());
        dto.setEmail(owner.getEmail());
        dto.setAddress(owner.getAddress());
        dto.setPhone(owner.getPhone());
        dto.setSalary(owner.getSalary());
        dto.setGender(owner.getGender());
        dto.setCompanyId(owner.getCompanies() != null && !owner.getCompanies().isEmpty() ? owner.getCompanies().get(0).getId() : null);

        return dto;
    }

    private Owner convertToEntity(OwnerDto dto) {
        Owner owner = new Owner();
        owner.setId(dto.getId());
        owner.setName(dto.getName());
        owner.setCpf(dto.getCpf());
        owner.setEmail(dto.getEmail());
        owner.setAddress(dto.getAddress());
        owner.setPhone(dto.getPhone());
        owner.setSalary(dto.getSalary());
        owner.setGender(dto.getGender());

        if (dto.getCompanyId() != null) {
            Optional<Company> company = companyRepository.findById(dto.getCompanyId());
            if (company.isPresent()) {
                if (owner.getCompanies() == null) {
                    owner.setCompanies(new ArrayList<>());
                }
                owner.getCompanies().add(company.get());
            }
        }

        return owner;
    }
}
