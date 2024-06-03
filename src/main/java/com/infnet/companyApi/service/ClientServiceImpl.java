package com.infnet.companyApi.service;

import com.infnet.companyApi.domain.Client;
import com.infnet.companyApi.dto.ClientDto;
import com.infnet.companyApi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<ClientDto> getClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto getClientById(UUID id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        return convertToDto(client);
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        try {
            Client client = convertToEntity(clientDto);
            Client savedClient = clientRepository.save(client);
            return convertToDto(savedClient);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Invalid customer data", e);
        }
    }

    @Override
    public ClientDto updateClient(UUID id, ClientDto clientDto) {
        try {
            Client existingClient = clientRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found with ID: " + id));
            existingClient.setName(clientDto.getName());
            existingClient.setAddress(clientDto.getAddress());
            existingClient.setPhone(clientDto.getPhone());
            existingClient.setEmail(clientDto.getEmail());
            existingClient.setCpf(clientDto.getCpf());
            existingClient.setCnpj(clientDto.getCnpj());

            Client updatedClient = clientRepository.save(existingClient);
            return convertToDto(updatedClient);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid customer data", e);
        }
    }

    @Override
    public void deleteClient(UUID id) {
        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found with ID: " + id);
        }
    }

    private ClientDto convertToDto(Client client) {
        ClientDto dto = new ClientDto();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setCnpj(client.getCnpj());
        dto.setCpf(client.getCpf());
        dto.setEmail(client.getEmail());
        dto.setPhone(client.getPhone());
        dto.setAddress(client.getAddress());

        return dto;
    }

    private Client convertToEntity(ClientDto dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setName(dto.getName());
        client.setCnpj(dto.getCnpj());
        client.setCpf(dto.getCpf());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client.setAddress(dto.getAddress());


        return client;
    }
}
