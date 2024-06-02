package com.infnet.companyApi.service;

import com.infnet.companyApi.domain.Owner;
import com.infnet.companyApi.dto.OwnerDto;

import java.util.List;
import java.util.UUID;

public interface OwnerService {

    List<OwnerDto> getAllOwners();
    OwnerDto getOwnerById(UUID id);
    OwnerDto createOwner(OwnerDto owner);
    OwnerDto updateOwner(UUID id, OwnerDto owner);
    void deleteOwner(UUID id);
}

