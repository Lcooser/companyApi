package com.infnet.companyApi.dto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OwnerDtoTest {

    @Test
    void testOwnerDtoCreation() {

        UUID id = UUID.randomUUID();
        String name = "Test Owner";


        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setId(id);
        ownerDto.setName(name);


        assertEquals(id, ownerDto.getId());
        assertEquals(name, ownerDto.getName());
    }
}
