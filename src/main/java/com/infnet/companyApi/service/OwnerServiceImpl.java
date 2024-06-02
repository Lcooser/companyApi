package com.infnet.companyApi.service;

import com.infnet.companyApi.domain.Owner;
import com.infnet.companyApi.dto.OwnerDto;
import com.infnet.companyApi.repository.OwnerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

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
        Owner existingOwner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + id));
        existingOwner.setName(ownerDto.getName());
        existingOwner.setAddress(ownerDto.getAddress());
        existingOwner.setEmail(ownerDto.getEmail());
        existingOwner.setPhone(ownerDto.getPhone());
        existingOwner.setCpf(ownerDto.getCpf());
        existingOwner.setSalary(ownerDto.getSalary());

        Owner updatedOwner = ownerRepository.save(existingOwner);
        return convertToDto(updatedOwner);
    }

    @Override
    public void deleteOwner(UUID id) {
        ownerRepository.deleteById(id);
    }

    private OwnerDto convertToDto(Owner owner) {
        OwnerDto dto = new OwnerDto();
        dto.setId(owner.getId());
        dto.setName(owner.getName());
        dto.setCpf(owner.getCpf());
        dto.setEmail(owner.getEmail());
        dto.setAddress(owner.getAddress());

        return dto;
    }

    private Owner convertToEntity(OwnerDto dto) {
        Owner owner = new Owner();
        owner.setId(dto.getId());
        owner.setName(dto.getName());
        owner.setCpf(dto.getCpf());
        owner.setEmail(dto.getEmail());
        owner.setAddress(dto.getAddress());

        return owner;
    }
}
