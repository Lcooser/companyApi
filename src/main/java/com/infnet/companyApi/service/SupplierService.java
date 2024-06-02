package com.infnet.companyApi.service;

import com.infnet.companyApi.domain.Supplier;
import com.infnet.companyApi.dto.SupplierDto;

import java.util.List;
import java.util.UUID;

public interface SupplierService {

    List<SupplierDto> getSuppliers();
    SupplierDto getSupplier(UUID id);
    SupplierDto createSupplier(SupplierDto supplierDto);
    SupplierDto updateSupplier(UUID id, SupplierDto supplierDto);
    void deleteSupplier(UUID id);

}
