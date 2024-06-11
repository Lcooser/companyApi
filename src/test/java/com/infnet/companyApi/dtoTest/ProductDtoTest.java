package com.infnet.companyApi.dto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductDtoTest {

    @Test
    void testProductDtoCreation() {

        UUID id = UUID.randomUUID();
        String name = "Test Product";


        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setName(name);


        assertEquals(id, productDto.getId());
        assertEquals(name, productDto.getName());
    }
}
