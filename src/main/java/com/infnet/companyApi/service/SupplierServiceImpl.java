package com.infnet.companyApi.service;

import com.infnet.companyApi.domain.Supplier;
import com.infnet.companyApi.dto.SupplierDto;
import com.infnet.companyApi.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<SupplierDto> getSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierDto getSupplierById(UUID id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
        return convertToDto(supplier);
    }

    @Override
    public SupplierDto createSupplier(SupplierDto supplierDto) {
        Supplier supplier = convertToEntity(supplierDto);
        Supplier savedSupplier = supplierRepository.save(supplier);
        return convertToDto(savedSupplier);
    }

    @Override
    public SupplierDto updateSupplier(UUID id, SupplierDto supplierDto) {
        Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
        existingSupplier.setName(supplierDto.getName());
        existingSupplier.setAddress(supplierDto.getAddress());
        existingSupplier.setPhone(supplierDto.getPhone());
        existingSupplier.setEmail(supplierDto.getEmail());
        existingSupplier.setStartOfContract(supplierDto.getStartOfContract());
        existingSupplier.setEndOfContract(supplierDto.getEndOfContract());
        existingSupplier.setCategory(supplierDto.getCategory());
        existingSupplier.setCnpj(String.valueOf(supplierDto.getCnpj()));

        Supplier updatedSupplier = supplierRepository.save(existingSupplier);
        return convertToDto(updatedSupplier);
    }

    @Override
    public void deleteSupplier(UUID id) {
        supplierRepository.deleteById(id);
    }

    private SupplierDto convertToDto(Supplier supplier) {
        SupplierDto dto = new SupplierDto();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setCnpj(supplier.getCnpj());
        dto.setEmail(supplier.getEmail());
        dto.setAddress(supplier.getAddress());
        dto.setPhone(supplier.getPhone());
        dto.setStartOfContract(supplier.getStartOfContract());
        dto.setEndOfContract(supplier.getEndOfContract());
        dto.setCategory(supplier.getCategory());

        return dto;
    }

    private Supplier convertToEntity(SupplierDto dto) {
        Supplier supplier = new Supplier();
        supplier.setId(dto.getId());
        supplier.setName(dto.getName());
        supplier.setCnpj(dto.getCnpj());
        supplier.setEmail(dto.getEmail());
        supplier.setAddress(dto.getAddress());
        supplier.setPhone(dto.getPhone());
        supplier.setStartOfContract(dto.getStartOfContract());
        supplier.setEndOfContract(dto.getEndOfContract());
        supplier.setCategory(dto.getCategory());

        return supplier;
    }
}
