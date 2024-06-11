package com.infnet.companyApi.dto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientDtoTest {

    @Test
    void testClientDtoCreation() {

        UUID id = UUID.randomUUID();
        String name = "Test Client";


        ClientDto clientDto = new ClientDto();
        clientDto.setId(id);
        clientDto.setName(name);


        assertEquals(name, clientDto.getName());
    }
}

