package com.infnet.companyApi.service;

import com.infnet.companyApi.domain.Client;
import com.infnet.companyApi.dto.ClientDto;
import com.infnet.companyApi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Client client = convertToEntity(clientDto);
        Client savedClient = clientRepository.save(client);
        return convertToDto(savedClient);
    }

    @Override
    public ClientDto updateClient(UUID id, ClientDto clientDto) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        existingClient.setName(clientDto.getName());
        existingClient.setAddress(clientDto.getAddress());
        existingClient.setPhone(clientDto.getPhone());
        existingClient.setEmail(clientDto.getEmail());
        existingClient.setCpf(clientDto.getCpf());
        existingClient.setCnpj(clientDto.getCnpj());

        Client updatedClient = clientRepository.save(existingClient);
        return convertToDto(updatedClient);
    }

    @Override
    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
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
