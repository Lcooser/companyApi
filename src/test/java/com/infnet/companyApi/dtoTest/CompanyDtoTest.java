package com.infnet.companyApi.dto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyDtoTest {

    @Test
    void testCompanyDtoCreation() {

        UUID id = UUID.randomUUID();
        String name = "Test Company";


        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(id);
        companyDto.setName(name);


        assertEquals(id, companyDto.getId());
        assertEquals(name, companyDto.getName());
    }
}
