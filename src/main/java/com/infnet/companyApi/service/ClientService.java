package com.infnet.companyApi.service;

import com.infnet.companyApi.dto.ClientDto;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    List<ClientDto> getClients();
    ClientDto getClientById(UUID id);
    ClientDto createClient(ClientDto clientDto);
    ClientDto updateClient(UUID id,ClientDto clientDto);
    void deleteClient(UUID id);

}
