package com.infnet.companyApi.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.companyApi.controller.ClientController;
import com.infnet.companyApi.dto.ClientDto;
import com.infnet.companyApi.service.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClientControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private final MockMvc mockMvc;

    public ClientControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    void shouldCreateClientSuccessfully() throws Exception {
        ClientDto clientDto = createClientDto();
        when(clientService.createClient(any(ClientDto.class))).thenReturn(clientDto);

        mockMvc.perform(post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldFailToCreateClientWithInvalidData() throws Exception {
        ClientDto invalidClientDto = createClientDto();
        invalidClientDto.setName("");

        mockMvc.perform(post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidClientDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdateClientSuccessfully() throws Exception {
        ClientDto clientDto = createClientDto();
        UUID clientId = clientDto.getId();
        when(clientService.updateClient(clientId, clientDto)).thenReturn(clientDto);

        mockMvc.perform(put("/client/{id}", clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientDto)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteClientSuccessfully() throws Exception {
        UUID clientId = UUID.randomUUID();

        mockMvc.perform(delete("/client/{id}", clientId))
                .andExpect(status().isNoContent());
    }

    private ClientDto createClientDto() {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(UUID.randomUUID());
        clientDto.setName("Luiz Coser");
        clientDto.setCnpj("12345678901234");
        clientDto.setCpf("12345678901");
        clientDto.setEmail("luiz.coser@example.com");
        clientDto.setPhone("1234567890");
        clientDto.setAddress("123 São Paulo");
        return clientDto;
    }
}
