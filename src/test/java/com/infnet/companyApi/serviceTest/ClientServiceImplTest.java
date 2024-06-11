package com.infnet.companyApi.serviceTest;

import com.infnet.companyApi.domain.Client;
import com.infnet.companyApi.dto.ClientDto;
import com.infnet.companyApi.repository.ClientRepository;
import com.infnet.companyApi.service.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void testCreateClient() {
        ClientDto clientDto = new ClientDto();
        clientDto.setName("Test Client");
        clientDto.setEmail("test@example.com");

        when(clientRepository.save(any())).thenReturn(new Client());

        ClientDto createdClient = clientService.createClient(clientDto);

        assertNotNull(createdClient);
        assertEquals(clientDto.getName(), createdClient.getName());
        assertEquals(clientDto.getEmail(), createdClient.getEmail());
    }

    @Test
    void testUpdateClient() {
        UUID clientId = UUID.randomUUID();
        ClientDto clientDto = new ClientDto();
        clientDto.setId(clientId);
        clientDto.setName("Updated Client");
        clientDto.setEmail("updated@example.com");

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(new Client()));
        when(clientRepository.save(any())).thenReturn(new Client());

        ClientDto updatedClient = clientService.updateClient(clientId, clientDto);

        assertNotNull(updatedClient);
        assertEquals(clientId, updatedClient.getId());
        assertEquals(clientDto.getName(), updatedClient.getName());
        assertEquals(clientDto.getEmail(), updatedClient.getEmail());
    }

    @Test
    void testDeleteClient() {
        UUID clientId = UUID.randomUUID();

        assertDoesNotThrow(() -> clientService.deleteClient(clientId));
        verify(clientRepository, times(1)).deleteById(clientId);
    }
}
