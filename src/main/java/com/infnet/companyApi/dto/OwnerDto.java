package com.infnet.companyApi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter

public class OwnerDto {

    private UUID id;

    private String name;
    private String cpf;
    private String email;
    private String phone;
    private String address;
    private String gender;
    private float salary;

}
