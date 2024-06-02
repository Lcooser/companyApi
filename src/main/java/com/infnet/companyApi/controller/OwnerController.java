package com.infnet.companyApi.controller;


import com.infnet.companyApi.dto.OwnerDto;
import com.infnet.companyApi.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public List<OwnerDto> getAllOwners() {
        return  ownerService.getAllOwners();
    }

    @GetMapping("/{id}")
    public OwnerDto getOwnerById(@PathVariable UUID id) {
        return ownerService.getOwnerById(id);
    }

    @PostMapping
    public OwnerDto createOwner(@RequestBody OwnerDto ownerDto) {
        return ownerService.createOwner(ownerDto);
    }

    @PutMapping("/{id}")
    public OwnerDto updateOwner(@PathVariable UUID id, @RequestBody OwnerDto ownerDto) {
        return ownerService.updateOwner(id, ownerDto);
    }

    @DeleteMapping(("/{id}"))
    public void deleteOwner(@PathVariable UUID id) {
        ownerService.deleteOwner(id);
    }



}
