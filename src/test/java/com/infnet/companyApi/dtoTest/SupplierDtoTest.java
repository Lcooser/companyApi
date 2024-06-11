package com.infnet.companyApi.dto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplierDtoTest {

    @Test
    void testSupplierDtoCreation() {

        UUID id = UUID.randomUUID();
        String name = "Test Supplier";


        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setId(id);
        supplierDto.setName(name);


        assertEquals(id, supplierDto.getId());
        assertEquals(name, supplierDto.getName());
    }
}
